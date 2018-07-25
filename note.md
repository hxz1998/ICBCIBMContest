开发笔记
---
1. 遇到本地jar包和maven仓库包混合依赖时，出现找不到符号之类的错误。
    * 解决方法：在相对pom.xml同级的目录下创建lib目录，然后在pom.xml中加入插件：
        ```xml
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.1</version>
            <configuration>
                <source>1.7</source>
                <target>1.7</target>
                <encoding>UTF-8</encoding>
                <compilerArguments>
                    <!-- 对应jar放置在项目中的位置-->
                    <extdirs>lib</extdirs>
                </compilerArguments>
            </configuration>
        </plugin>
        ```
2. 问题1的遗留问题：编译通过但是不会打包到可执行jar文件中或者war包中
    * 解决方法：在pom.xml中添加:
    1. 打包至jar中
        ```xml
        <resources>
            <resource>
                <directory>lib</directory>
                <targetPath>BOOT-INF/lib/</targetPath>
                <includes>
                    <include>**/*.jar</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <targetPath>BOOT-INF/classes/</targetPath>
            </resource>
        </resources>
        ```
    2. 打包至war中,添加依赖插件
        ```xml
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-dependency-plugin</artifactId>
            <version>2.10</version>
            <executions>
                <execution>
                    <id>copy-dependencies</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>copy-dependencies</goal>
                    </goals>
                    <configuration>
                        <outputDirectory>${project.build.directory}/${project.build.finalName}/WEB-INF/lib</outputDirectory>
                        <includeScope>system</includeScope>
                    </configuration>
                </execution>
            </executions>
        </plugin>
        ```
3. SpringMVC无法访问首页
    * 解决方法
    1. 首先配置视图解析器
        ```xml
        <!--视图解析器-->
        <bean  class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="prefix" value="/"/>
            <property name="suffix" value=".html"/>
            <property name="contentType" value="text/html"/>
        </bean>
        ```
    2. 使用tomcat来处理静态资源
        > 注意：需要将其放在Spring的拦截器前
        ```xml
        <!--使用tomcat来处理静态资源-->
        <web-app>
            <servlet-mapping>
                <servlet-name>default</servlet-name>
                <url-pattern>*.css</url-pattern>
            </servlet-mapping>
            <servlet-mapping>
                <servlet-name>default</servlet-name>
                <url-pattern>*.js</url-pattern>
            </servlet-mapping>
            <servlet-mapping>
                <servlet-name>default</servlet-name>
                <url-pattern>*.png</url-pattern>
            </servlet-mapping>
            <servlet-mapping>
                <servlet-name>default</servlet-name>
                <url-pattern>*.jpg</url-pattern>
            </servlet-mapping>
            <servlet-mapping>
                <servlet-name>default</servlet-name>
                <url-pattern>*.html</url-pattern>
            </servlet-mapping>
        </web-app>
        ```
4. 使用 `vue-resource.js` 插件无法找到方法
    * 解决方法：在 `vue.js` 之后引入 `vue-resource.js` 
5. 使用 `vue-resource.js` 插件的 `post` 方法无法向服务端传递参数
    * 解决方法：因为报文头的格式不同，所以需要设置为表单类的报文头，在脚本的初始化阶段设置 `Vue` 的属性
        ```javascript
        Vue.http.options.emulateJSON = true;
        ```
6. 使用 `vue-resource.js` 插件请求的报文头无CORS
    * 解决方法：在 `post` 方法中加入选项
        ```
        post:function() {
            this.$http.post('http://localhost:8080/demo/qr/getQrCode', {
                'merId':this.merId,
                'storeCode':this.storeCode,
                'outTradeNo':this.outTradeNo,
                'orderAmt':this.orderAmt,
                'tradeDate':this.tradeDate,
                'tradeTime':this.tradeTime,
                'payExpire':this.payExpire,
                'tporderCreateIp':this.tporderCreateIp,
                'notifyFlag':this.notifyFlagInput
            }, {
                'headers':{
                    'Access-Control-Allow-Origin':'*'
                }
            }).then(function(response) {
                this.imageSrc = response.bodyText;
            }, function (response) {
                console.log(response)
            });
        }
        ```
7. 前后端综合解决跨域问题
    * 解决方法：后端加入 `'Access-Control-Allow-Origin':'*'` 相关的配置，前端加入报问头（问题6解决）
    * 完整解决示例：
        * 服务端：
        ```java
        package ICBCIBMContest.controller;
        
        import ICBCIBMContest.model.impl.SimpleQrRequestParam;
        import ICBCIBMContest.services.QrGeneratorService;
        import ICBCIBMContest.util.PropertiesFactory;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;
        
        import javax.annotation.Resource;
        
        @Controller
        @RequestMapping("/qr")
        public class QrRCodeGenerateController {
        
            private QrGeneratorService qrGeneratorService;
            private PropertiesFactory propertiesFactory;
        
            @CrossOrigin("http://localhost:63343")
            @RequestMapping(value = "/getQrCode", produces = "application/json;charset=utf-8")
            @ResponseBody
            public String getQrCode(@ModelAttribute SimpleQrRequestParam param) {
                System.out.println(param);
                return propertiesFactory.getPropertyValue("QR_GENERATOR_URL") + qrGeneratorService.getQrCode(param);
            }
            @Resource
            public void setQrGeneratorService(QrGeneratorService qrGeneratorService) {
                this.qrGeneratorService = qrGeneratorService;
            }
            @Resource
            public void setPropertiesFactory(PropertiesFactory propertiesFactory) {
                this.propertiesFactory = propertiesFactory;
            }
        }

        ```
        * 客户端：
        ```javascript
        Vue.http.options.emulateJSON = true;
        Vue.http.options.crossOrigin = true;
        var qr_generator = new Vue({
            el:'#qr_form',
            data:{
                merId: '',
                storeCode: '',
                outTradeNo: '',
                orderAmt: '',
                tradeDate: '',
                tradeTime: '',
                payExpire: '',
                tporderCreateIp: '',
                notifyFlagInput: '',
                imageSrc:''
            },
            methods:{
                post:function() {
                    this.$http.post('http://localhost:8080/demo/qr/getQrCode', {
                        'merId':this.merId,
                        'storeCode':this.storeCode,
                        'outTradeNo':this.outTradeNo,
                        'orderAmt':this.orderAmt,
                        'tradeDate':this.tradeDate,
                        'tradeTime':this.tradeTime,
                        'payExpire':this.payExpire,
                        'tporderCreateIp':this.tporderCreateIp,
                        'notifyFlag':this.notifyFlagInput
                    }, {
                        'headers':{
                            'Access-Control-Allow-Origin':'*'
                        }
                    }).then(function(response) {
                        this.imageSrc = response.bodyText;
                    }, function (response) {
                        console.log(response)
                    });
                }
            }
        });
        ```
8. 使用 `javascript` 从字符串转化json对象和访问json对象的属性
    * 使用 `JSON.parse(str)` 可以转化成为json对象
    * 访问json对象的属性时直接用 `.` 来访问
9. 使用 `Pace.js` 监控加载进度时无法监控 `vue.js` 的post请求
    * 解决方法：重新启动下 `Pace.js`，即调用 `Pace.restart();` 方法。    
       