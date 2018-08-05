# ICBC-IBM-Contest

![Build:pass](https://img.shields.io/badge/build-pass-green.svg)
![Version:1.0](https://img.shields.io/badge/Version-1.0-brightgreen.svg)
![License:MIT](https://img.shields.io/badge/LICENSE-MIT-blue.svg)
![Spring:4.3.18.RELEASE](https://img.shields.io/badge/Spring-4.3.18.RELEASE-brightgreen.svg)
![Fastjson:1.2.47](https://img.shields.io/badge/fastjson-1.2.47-brightgreen.svg)
![Vue:2.5.16](https://img.shields.io/badge/Vue-2.5.16-brightgreen.svg)
![Vue-resource:1.5.1](https://img.shields.io/badge/Vue--resource-1.5.1-brightgreen.svg)
![Vue-router3.0.1](https://img.shields.io/badge/Vue--router-3.0.1-brightgreen.svg)
![Bootstrap:3.3.7](https://img.shields.io/badge/Bootstrap-3.3.7-brightgreen.svg)
![JQuery:3.3.1](https://img.shields.io/badge/JQuery-3.3.1-brightgreen.svg)

## 项目简介
**该项目旨在使用一种简单的方式为残障人士快速建立金融通道，使他们有机会使用更加方便快捷的服务。** 但同时该应用也适用于普通大众。
> [特别感谢 ICBC 为该项目提供的API开放接口服务](https://open.icbc.com.cn/icbc/apip/service.html)。

## 技术栈
**该项目为2018 ICBC - IBM全国大学生金融科技创新大赛使用，具体技术细节及描述如下：**
1. 使用Java语言，SpringMVC框架作为后端语言及工具，接入ICBC开放API服务提供前端支持。
2. 使用Bootstrap框架构建前端页面样式。
3. 使用Vue、Vue-resource、Vue-router框架与后端服务交互。
4. 使用maven对整个项目进行管理和构建。
5. 使用tomcat为服务器容器对应用进行部署。

## 项目源码获取、打包及运行发布
```
git clone git@github.com:MonkeyAndDog/ICBCIBMContest.git
cd ICBCIBMContest
mvn clean package
```
得到.war文件包之后放置于Tomcat的webapps目录下，重启tomcat。然后访问 `http://localhost:8080/ICBC-IBM-Contest-1.0/`
即可。

## 完成功能
- [x] 生成二维码

## 效果演示
[戳这里👈](http://118.31.62.78:8080/icbc/)

### 二维码扫码演示：
![](https://github.com/MonkeyAndDog/ICBCIBMContest/blob/master/screenshots/qr.png)

## 部分页面截屏
### 主页
![](https://github.com/MonkeyAndDog/ICBCIBMContest/blob/master/screenshots/home.jpg)

### 应用列表
![](https://github.com/MonkeyAndDog/ICBCIBMContest/blob/master/screenshots/applicationlist.jpg)

### 二维码生成输入组框
![](https://github.com/MonkeyAndDog/ICBCIBMContest/blob/master/screenshots/qr-pay-input.jpg)

### 二维码生成结果
![](https://github.com/MonkeyAndDog/ICBCIBMContest/blob/master/screenshots/qr-pay.jpg)

### 关于页
![](https://github.com/MonkeyAndDog/ICBCIBMContest/blob/master/screenshots/about.jpg)

## 项目目录说明
```text
.
├── lib                                 //icbc开放API所需SDK
├── doc                                 //JavaDoc生成的API文档
├── screenshots                         //项目demo截图
├── src                                 //项目源代码
│   └── main
│       ├── java                        //后端服务代码
│       │   └── ICBCIBMContest  
│       │       ├── config              //Spring配置类
│       │       ├── constant            //常量类
│       │       ├── controller          //控制器类
│       │       ├── model               //数据模型类
│       │       ├── services            //服务类
│       │       └── util                //工具类
│       ├── resources                   //配置文件目录
│       │   └── application.properties  //应用配置文件
│       ├── test                        //测试类目录
│       ├── webapp                      //web应用相关页面及配置目录
│       ├── css                         //前端样式表目录
│       ├── fonts                       //字体目录
│       ├── img                         //图片目录
│       ├── js                          //Javascript目录
│       ├── WEB-INF                     //Web服务器、Spring配置目录及网页元信息目录
│       └── index.html                  //站点入口文件
├── target                              //编译产生目录
├── .gitignore                          //定义git忽略文件规则
├── _config.yml                         //readme文档样式
├── LICENSE                             //协议目录
├── note.md                             //开发笔记*
├── pom.xml                             //maven项目描述文件
└── README.md                           //自述文件
```

## LICENSE
[MIT](https://github.com/MonkeyAndDog/ICBCIBMContest/blob/master/LICENSE)
```text
MIT License

Copyright (c) 2018 Xiaozhong

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```