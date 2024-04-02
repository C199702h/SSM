# SSM
Java配置类整合SSM框架
基本功能：

框架升级：SSM
密码升级：明文存储/md5存储 -> 加盐处理
用户登录状态持久化升级：session内存 -> 持久化到Redis/MySQL
功能升级：分页功能
使用拦截器升级用户登录验证

项目技术栈
前端：HTML + CSS + JS + Jquery
后端: Spring MVC + Spring Boot + Mybatis + Redis
数据库: MySQL
开发环境：Windows 10 、IDEA
项目构建工具: maven

实体层（model）
实体层就直接实现了，就两个表对应的实体类

控制器层（controller）
控制层，具体内容等功能实现的时候再来完善

服务层（service）
服务层，具体内容之后完善

持久层（mapper）
持久层，具体内容之后完善

工具层（common）
工具层，主要是一些统一处理（返回的数据类型，异常等等）
