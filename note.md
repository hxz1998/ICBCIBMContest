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