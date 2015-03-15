# 说明文档 #

M-LOG是一个遵循[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)协议的开源软件。 M-LOG是一个用Java搭建的博客程序。该程序使用JDK1.5+、Spring MVC、Hibernate、Freemarker、Lucene搭建。

应用功能：

  1. 支持mateweblog离线写作协议
  1. 支持RSS2.0、Atom订阅功能
  1. 支持相册功能
  1. 支持动态换肤，并提供多款皮肤以供选择（目前皮肤正在不断增加中）
  1. 多语言支持
  1. 文章的全文检索
  1. 基于分类和Tag的文章分类功能
  1. 支持友情链接，以及友情链接的分类功能
  1. SEO优化功能
  1. 评论回复及邮件提醒
  1. 可配置文章固定永久链接和SEO优化
  1. 自由配置博客信息

# 用户手册 #

## 安装帮助 ##

### 标准Servlet版本 ###

正在完善...

### BAE版本 ###

  1. 下载源码：官网下载最新版本的安装包[M-Spring](http://www.mspring.org)
  1. 获取数据库名称：登录bae，依次进入管理中心>我的云服务>云数据库，记录下数据库名称（如果没有数据库，则创建一个）
  1. 数据库用户名、密码：登录bae，依次进入管理中心>我的云服务>服务管理，记录下Access Key和Secure Key，其中Access Key就是数据库用户名，Secure Key就是数据库密码，如果没有密钥那么就点击“创建密钥对”按钮创建。
  1. 修改项目数据库配置：在前面几步中，我们在bae平台找到了数据库的名称、用户名、密码，下面就开始修改我们程序的数据库配置了。使用winrar打开mlog-bae.war文件，找到WEB-INF\classes\applicationContext.xml文件，并打开。参照自己的bae配置修改下图标红的地方，然后将修改的文件替换mlog-bae.war中的文件。
  1. 创建bae项目：在修改好数据库配置之后，登录bae，进入管理中心创建一个项目，如图：
  1. 创建版本：创建项目后进入项目的版本管理，创建版本，输入版本号，选择“上传Java war包”上传我们的mlog-bae.war，然后点击保存按钮。 如图：
  1. 然后等待bae上项目发布成功之后浏览我们的项目，填写相关信息即可初始化博客。
  1. 然后成为一个M-Loger！

# 使用帮助 #

## 离线写作配置 ##
M-LOG支持metaweblog协议的离线写作接口。我们推荐的离线写作客户端为Windows live writer。

Windows live writer配置步骤：