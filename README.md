## 一、背景

前后端分离已经成为互联网项目开发标准，它会为以后的大型分布式架构打下基础。[SpringBoot](https://projects.spring.io/spring-boot/)使编码配置部署都变得简单，越来越多的互联网公司已经选择SpringBoot作为微服务的入门级微框架。

[Mybatis-Plus](https://github.com/baomidou/mybatis-plus)是一个 [Mybatis](http://www.mybatis.org/mybatis-3/) 的增强工具，有代码生成器，并且提供了类似hibernate的单表CRUD操作，又保留了mybatis的特性支持定制化 SQL。

[Apache Shiro](https://shiro.apache.org/)是一款强大易用的Java安全框架，Java官方推荐使用Shiro，它比Spring Security更简单易用，Spring自己的系列Spring side4也把权限扩建换成Shiro了。

现在API越来越流行，如何安全保护这些API？ [JSON Web Tokens](https://jwt.io/)(JWT)能提供基于JSON格式的安全认证。JWT可以跨不同语言，自带身份信息，并且非常容易传递。

## 二、项目特性

1.自定义@Log注解自动记录日志到数据库。

2.自定义@Pass注解接口不用进行认证身份。

3.使用JSONObject统一获取body请求参数，减少实体类的数量。完成自定义@ValidationParam注解验证请求参数是否为空。

      ![](https://oscimg.oschina.net/oscnet/3aef52625a42756283ab5a5aeaa4d800a2c.jpg)

4.使用bcrypt算法加密密码，著名代码托管网站Github和美国军方防火墙同样采用此算法，靠bcrypt算法会成功保住密码强度不算很高的大部分账户。

5.搭配Shiro注解配置权限，高度灵活，提供按钮级别的权限控制，后端接口只验证权限，不看角色。用自定义@CurrentUser注解获取当前登录用户，Controlle层统一异常处理：

     ![](https://static.oschina.net/uploads/space/2018/0512/234950_u2kv_3577599.png)

6.用SpringAOP切面编程进行声明式事务，过滤请求参数，防止XSS攻击。

7.使用POST请求登录返回token和权限信息，保证请求无状态，返回实体如果属性为空不显示。

    ![](https://oscimg.oschina.net/oscnet/65315a182f2e70eca0a6e71147f9cfaced4.jpg)

8.完成微信/微博/QQ第三方登录功能,WebSocket实时消息推送,短信登录注册等功能.

## 三、程序逻辑

1.填写用户名密码用POST请求访问/login接口，返回token令牌等信息，失败则直接返回身份错误信息。

2.在之后需要验证身份的请求的Headers中添加Authorization和登录时返回的token令牌。

3.服务端进行token认证，失败身份错误信息。

4.用JWT做认证（登录），Shiro做授权。

## 四、运行项目


-   通过git下载源码，本项目基于JDK1.8
    
-   采用Maven项目管理，模块化，导入IDE时直接选定liugh-parent的pom导入
    
-   创建数据库liugh，数据库编码为UTF-8，执行liugh.sql文件，初始化数据
    
-   修改application-dev.properties，更新MySQL账号和密码
    
-   Eclipse、IDEA运行SpringbootApplication.java，则可启动项目。或在liugh-parent目录下运行命令mvn clean package，然后在liugh-web/target目录下运行java -jar liugh-web.jar命令

    
-   访问登录接口：localhost:8081/api/v1/login
    
-   账号密码：13888888888/123456(管理员)
    
-   获取token访问其他接口

-   注意!访问的接口url统一会加上/api/v1
    

运行截图：

![](https://oscimg.oschina.net/oscnet/6c45f5bfa57fa868b4fb700011f943e65b0.jpg)

彩蛋：项目注释完整，并且自定义了启动图案~


第一次做自己的项目，经验不足，如果大家有什么好的意见或批评，请务必issue一下。