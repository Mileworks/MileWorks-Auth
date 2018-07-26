# MileWorks-Auth - 基于OAuth2 \ JWT 认证授权模块  
研究现有基于Spring Cloud框架研究一段时间之后，其中整个框架的系统认证、授权模块是其中一大核心功功能模块。在前后端分离之后，这个功能在整个系统中是可以单独剥离，并且可以独立运行。决定了整个系统资源使用、API调用的权限使用等等问题。

整理这套东西前期是研究了很多对应文章和代码之后总结的一套，并且是可以复用到任何微服务功能中去。

前期研究有代表性文章：
- [https://www.renren.io/guide](https://www.renren.io/guide)
- [https://segmentfault.com/a/1190000009164779](https://segmentfault.com/a/1190000009164779)
-  [http://blueskykong.com/2017/10/19/security1/](http://blueskykong.com/2017/10/19/security1/)

主流现在包括Java 、Go 语言中涉及Token相关的问题都会于JWT相关联处理。针对JWT相关的教程参考如下：  
-  [https://www.jdon.com/artichect/json-web-tokens.html](https://www.jdon.com/artichect/json-web-tokens.html)

大致流程如下：
![avatar](https://user-images.githubusercontent.com/1064859/42557268-943551c8-8520-11e8-9c38-e1c518cd6ed3.jpg)

其中在整个微服务中的定位：
![avatar](https://user-images.githubusercontent.com/1064859/42986178-32b3d7cc-8c27-11e8-9dd8-d216fc1bb962.png)


### 现在针对现有工程进行说明解释：
- 系统结构如下：
```
MileWorks-Auth
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─mileworks
│  │  │          └─authsys
│  │  │              ├─config : Oauth相关配置
│  │  │              ├─controller : API 入口
│  │  │              ├─exception
│  │  │              ├─model
│  │  │              │  └─audit
│  │  │              ├─payload : 数据传输相关
│  │  │              ├─repository
│  │  │              ├─security : JWT 和 Oauth相关业务等处理
│  │  │              ├─service
│  │  │              └─util 
│  │  └─resources : 资源配置相关
│  │      ├─db
│  │      └─static
│  │          └─static
│  │              ├─css
│  │              ├─js
│  │              └─media
│  └─test
│      └─java
│          └─com
│              └─mileworks
│                  └─authsys

```

### 针对每块核心功能做详细介绍:  

**Note: 现有功能预览**
- 构建API 通过用户名、邮箱、密码去注册新用户。
- 构建API 通过用户名、邮箱、密码去登陆。通过`校验`用户验证信息，API能生成`JWT授权`信息,并且返回值中返回相应token。
  客户端下次会将登陆成功返回的jwt token保存到本地，在每次请求访问受保护资源的时候在认证头部中`Authorization header`中带上`JWT token`信息。
- 配置 Spring security 中资源限制：
  * API中的login、signup 、静态资源（图片、js脚本、css）能被任何人访问。
  * API中的创建poll、vote等等 ，必须能被针对不同用户授权访问。
- 配置Spring security 中401 授权错误处理（当访问受保护资源但是请求时候没有JWT token）。
- 配置基于角色的认证资源服务器，比如：
  * 只有用户角色是ADMIN 能请求创建poll接口
  * 只有用户角色是USER 能投票