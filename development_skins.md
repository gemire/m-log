![http://m-log.googlecode.com/svn/wiki/images/logo.png](http://m-log.googlecode.com/svn/wiki/images/logo.png)


M-LOG皮肤是以Freemarker开发的，所以开发皮肤之前必须了解Freemarker相关知识，如果你不是很了解Freemarker和M-LOG的架构，我们推荐在您开发皮肤时，参照已有皮肤开发。

## 皮肤目录结构 ##
M-LOG皮肤保存在skins目录下，皮肤目录中包含的文件及文件夹结构如下：
| **文件/文件夹名**     |**类型** 			|**描述** |
|:----------------------------|:-------------|:----------|
| images              | 文件夹			| 该文件夹用于存放该皮肤所用到的相关图片      |
| script              | 文件夹			| 该文件夹用于存放该皮肤所用到的JavaScript文件 |
| style               | 文件夹			| 该文件夹用户存放该皮肤所用到的样式表文件      |
| skin.properties     | properties文件		| 该文件为皮肤的信息描述文件，文件描述信息配置项如下：\\ name：皮肤的名称、author：作者名称、url：皮肤的发布页或者作者的相关网址、email：作者的邮箱、description：皮肤描述信息      |
| preview.png         | png图片			| 该文件为皮肤的预览缩略图，目前只支持png格式图片，建议大小：宽280，高160      |
| index.ftl           | ftl文件			| 该文件为皮肤模板的首页，也是默认的文章列表页      |
| header.ftl          | ftl文件			| 该文件为皮肤的header部分      |
| footer.ftl          | ftl文件			| 该文件为皮肤的footer部分      |
| single.ftl          | ftl文件			| 该文件为文章页      |
| post-token.ftl      | ftl文件			| 在文章加密之后，访问加密文章需要输入相册密码，该文件即为文章密码输入页 |
| sidebar.ftl         | ftl文件			| 该文件为皮肤的侧边栏      |
| comment.ftl         | ftl文件			| 该文件为文章详细页面的评论部分（包括评论列表和评论输入框）      |
| search.ftl          | ftl文件			| 该文件为文章的搜索页面和搜索结果显示页面      |
| album.ftl           | ftl文件			| 该文件为相册页面 |
| photo-list.ftl      | ftl文件			| 该文件为图片列表页 |
| photo-view.ftl      | ftl文件			| 该文件为图片查看页 |
| album-token.ftl     | ftl文件			| 在相册加密之后，访问加密相册需要输入相册密码，该文件即为相册密码输入页 |

## 皮肤模板变量 ##
| **变量名**				| **类型**						| **作用域**			| **描述** |
|:-----------------|:----------------|:----------------|:-----------|
|base					|java.lang.String			|全局				| 当前应用路径|
|template\_url			|java.lang.String			|全局				| 当前皮肤路径|
|blogname			|java.lang.String			|全局				| 博客名字 |
|description			|java.lang.String			|全局				| 博客描述 |
|keyword			|java.lang.String			|全局				| 博客关键字 |
|blogurl			|java.lang.String			|全局				| 博客URL |
|blogsubname			|java.lang.String			|全局				| 博客二级标题 |
|notice			|java.lang.String			|全局				| 公告 |
|skin			|java.lang.String			|全局				| 当前皮肤名称 |
|copyright			|java.lang.String			|全局				| 版权信息 |
|postPage			|org.mspring.platform.persistence.support.Page			|index.ftl、search.ftl				| 文章分页信息，以及当前页数据 |
|post			|org.mspring.mlog.entity.Post			|single.ftl				| 文章对象 |
|author			|java.lang.String			|comment.ftl				| 评论作者 |
|email			|java.lang.String			|comment.ftl				| 评论作者邮箱 |
|url			|java.lang.String			|comment.ftl				| 评论作者主页 |
|postId			|java.lang.Long			|comment.ftl				| 评论对应的文章编号 |
|comments			|java.util.List			|comment.ftl				| 文章对应的评论列表 |
|comment			|org.mspring.mlog.entity.Comment			|comment.ftl				| 评论对象 |


## 皮肤模板函数 ##
|**函数名**					|**作用域**										|**描述**|
|:-----------------|:----------------------|:---------|
|list\_post					|index.ftl,search.ftl							|迭代文章 |
|list\_post\_catalog			|index.ftl,search.ftl,single.ftl				|迭代文章对应的分类  |
|list\_post\_tag				|index.ftl,search.ftl,single.ftl				|迭代文章对应的tag  |
|post\_id					|index.ftl,search.ftl,single.ftl				|文章编号  |
|post\_title					|index.ftl,search.ftl,single.ftl				|文章标题  |
|post\_summary				|index.ftl,search.ftl,single.ftl				|文章描述  |
|post\_content				|index.ftl,search.ftl,single.ftl				|文章内容  |
|post\_url					|index.ftl,search.ftl,single.ftl				|文章url  |
|post\_time					|index.ftl,search.ftl,single.ftl				|文章发表时间  |
|post\_author\_alias			|index.ftl,search.ftl,single.ftl				|文章作者昵称  |
|post\_author\_name			|index.ftl,search.ftl,single.ftl				|文章作者用户名  |
|post\_author\_email			|index.ftl,search.ftl,single.ftl				|文章作者邮箱  |
|post\_comment\_count			|index.ftl,search.ftl,single.ftl				|文章评论数量  |
|post\_view\_count			|index.ftl,search.ftl,single.ftl				|文章点击量  |
|catalog\_name				|index.ftl,search.ftl,single.ftl				|分类名称  |
|catalog\_url				|index.ftl,search.ftl,single.ftl				|文类url  |
|tag\_name					|index.ftl,search.ftl,single.ftl				|tag名称  |
|tag\_url					|index.ftl,search.ftl,single.ftl				|tagurl  |
|list\_comment				|index.ftl,search.ftl,single.ftl				|迭代评论列表  |
|comment\_id					|comment.ftl									|评论编号  |
|comment\_time				|comment.ftl  									|评论时间  |
|comment\_author				|comment.ftl  									|评论作者  |
|comment\_author\_url			|comment.ftl  									|评论作者主页  |
|comment\_content			|comment.ftl  									|评论内容  |
|comment\_gravatar			|comment.ftl  									|评论作者gravatar图像地址  |
|stat\_post\_count			|全局  											|文章总数量  |
|stat\_comment\_count			|全局  											|评论总数量  |
|stat\_click\_count			|全局  											|博客点击量  |
|mlog\_title                             |header.ftl                                   |事实上该函数是全局函数,但是我们一般将其用在header.ftl中,该函数用于设置当前页面的标题          |



## 皮肤系统widget ##
widget调用方式：<@widget.placeholder path="路径" cache=true />，path为必须参数，需要传递一个相对路径；cache为非必须参数，该参数标明是否缓存widget，默认为true，缓存时间为5秒
|**名称**				|**路径**						|**描述、示例**|
|:-------------|:---------------|:------------------|
| 分类目录			| /widget/listCatalog		| <@widget.placeholder path="/widget/listCatalog" cache=true />      |
| 最近发布文章		| /widget/recentPost		| <@widget.placeholder path="/widget/recentPost?num=20" />，可以使用url传递参数num，参数num表示显示多少条最新文章，默认为20条     |
| 最多浏览文章		| /widget/mostViewPost		| <@widget.placeholder path="/widget/mostViewPost?num=20" />，可以使用url传递参数num，参数num表示显示多少篇文章，默认为20条     |
| 最新评论			| /widget/recentComment		| <@widget.placeholder path="/widget/recentComment?num=20" />，可以使用url传递参数num，参数num表示显示多少条评论，默认为20条     |
| 链接				| /widget/links				| <@widget.placeholder path="/widget/links" />    |
| 导航菜单			| /widget/menus				| <@widget.placeholder path="/widget/menus" />     |