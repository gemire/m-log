<!DOCTYPE HTML>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="{$meta.description|nohtml}">
  <meta name="keywords" content="{$meta.keywords|nohtml}">
  <link href="{$meta.rss}" rel="alternate" title="订阅{$global.name|nohtml}" type="application/rss+xml">
  <link rel="apple-touch-icon" href="{$meta.mobile_icon}">
  <!-- 引入bootstrap CSS -->
  <link href="${template_url}/css/metro-bootstrap.css" rel="stylesheet">
  <link href="${template_url}/css/bootstrap-responsive.css" rel="stylesheet">
  <link href="${template_url}/css/bootstrap-responsive.css" rel="stylesheet">
  <title>title</title>

  <style type="text/css">
	  *{
		  font-family:"Microsoft Yahei","微软雅黑";
	  }
    body {
      background: #fafafa;
    }
    .bg{
      background:url(${template_url}/image/grayjean.png);
    }
    #navbar{
      font-size: 12px;
		  /*opacity: 0.8;*/
    }
    #sidebar{
    }
    #sidebar ul{
      list-style: none;
    } 
    #sidebar ul li{
      margin-top:5px;
    } 
    .container{
    }
    .post-title{
    }
    .post-title h4{
      text-shadow: 1px 1px 0 #ddd;
      font-weight:bold;
    }
    .post-thumbnail{
      margin-right: 15px;
      width:170px;
      height:130px;
    }
    .post-summary{
    }
    .post-copyright{
    }
    .post-author{
      margin-right: 10px;
    }

    .post-author-desc{
    }
    .content {
    }
    .post-content p{
      text-indent:2em;
      line-height:24px;
    }
    .category a{
      color: red;
    }
    .post{
    }
    .read-more{
      display: display;
    }
    .thumbnail img{
      width: 160px;
      height: 120px;
    }

    /* 友情连接 */
    #links ui li{
      width:50%;
    }

    /*圆角阴影*/
    .box {
      padding: 20px;
      background: #fcfcfc;
      margin-bottom: 20px;
      box-shadow: rgba(1,1,1);
      box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25);
      -webkit-box-shadow: rgba(1,1,1);
      -khtml-box-shadow: rgba(1,1,1,1) 0 1px 1px;
      /*
      box-shadow: rgba(200,200,20);
      box-shadow: 0 5px 10px rgba(0, 0, 0, 0.25);
      -webkit-box-shadow: rgba(200,200,20);
      -khtml-box-shadow: rgba(200,200,200,1) 0 2px 18px;
    border-radius: 5px;
      -webkit-border-radius: 5px;
      -moz-border-radius: 5px;
      */;
    }
    /*返回顶部*/
    .to-top:active,.to-top:hover{
      filter:alpha(opacity=75);
      -moz-opacity:0.75;
      opacity:0.75;
    }
    .to-top,.to-top:link,.to-top:visited{
      position:fixed;
      text-indent:-9999px;
      display:block;
      width:54px;
      height:54px;
      bottom:187px;
      outline:none;
      background:url(${template_url}/image/top.png) no-repeat 0 0;
      filter:alpha(opacity=100);
      -moz-opacity:1;
      opacity:1;
      _display: none;
    }
    /*固定位置*/
    .fixed{
      position:fixed;
      float:right;
    }
    .da_roll{
      position:fixed;
      top:0;
      z-index:300;
      width:300px;
    } 
    /* 自定义icons */
    [class^="cus-"],[class*=" cus-"] {
      display: inline-block;
      width: 15px;
      height: 15px;
      *margin-right: .3em;
      line-height: 15px;
      vertical-align: text-top;
      background-image: url("http://x.libdd.com/farm1/6424fa/38d3b3c2/cus-icons.png");
      background-position: 15px 15px;
      background-repeat: no-repeat;
    }
    [class^="cus-"]:last-child,[class*=" cus-"]:last-child {
      *margin-left: 0;
    }
    .cus-icon-sina{
      width:15px;
      height:15px;
      background-position:-2px -3px;
    }
    .cus-icon-qq{
      width:15px;
      height:15px;
      background-position:-23px -3px;
    }
    .cus-icon-rss{
      width:15px;
      height:15px;
      background-position:-42px -3px;
    }
    /*自定义下载链接图标*/
    a[href$=".zip"],a[href$=".7z"],a[href$=".rar"]{
      background:url("http://x.libdd.com/farm1/6424fa/822d834d/rar.gif") no-repeat left center;
      padding-left:19px;
    }

    a[href^="http://pan.baidu.com/"]{
      background:url("http://x.libdd.com/farm1/6424fa/cf0cb595/baidu.gif") no-repeat left center;
      padding-left:13px;
      padding-right:13px;
    }
    /*解决多说头像在IE下失效的问题*/
    .ds-recent-comments img{
      width: 32px;
      height: 32px;
    }
    /*底部公告栏*/
    #notice{
      position:fixed;
      bottom:0;
      background:#000;
      width:100%;
      height:23px;
      line-height:23px;
      z-index:9999;
      opacity:.60;
      filter:alpha(opacity=60);
      _bottom:auto;
      _width:100%;
      _position:absolute;
      _top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0)));
    }
    #notice a{
      color:#fff;
      font-size:13px;
      letter-spacing:2px;
    }
    #notice .close,#feed a{
      float:right;
      margin:0 10px 0 0;
      padding:0 10px 0 10px;
    }
    #notice .bulletin{
      float:left;
      height:23px;
      color:#fff;
      margin:0 0 0 20px;
      background:url("http://x.libdd.com/farm1/2893f5/141f8c04/bulletin.gif") no-repeat;
      min-height:23px;
      overflow:hidden;
    }
    #notice .bulletin li{
      height:23px;
      padding-left:25px;
    }
    #notice ul,#notice li{
      margin:0px;
      padding:0px;
      list-style:none;
    }

    /* 自定义 SNS icons */
    [class^="sns-"],[class*=" sns-"] {
      display: inline-block;
      width: 24;
      height: 24x;
      *margin-right: .3em;
      line-height: 24x;
      vertical-align: text-top;
      background-image: url("http://x.libdd.com/farm1/6424fa/afb95747/light-social.png");
      background-position: 24x 24x;
      background-repeat: no-repeat;
    }
    [class^="sns-"]:last-child,[class*=" sns-"]:last-child {
      *margin-left: 0;
    }
    .sns-icon-sina {
      width:24px;
      height:24px;
      background-position:0px 0px;
    }
    a:hover .sns-icon-sina{
      width:24px;
      height:24px;
      background-position:-24px 0px;
    }
    .sns-icon-qq {
      width:24px;
      height:24px;
      background-position:0px -24px;
    }
    a:hover .sns-icon-qq{
      width:24px;
      height:24px;
      background-position:-24px -24px;
    }
    .sns-icon-inbox {
      width:24px;
      height:24px;
      background-position:0px -144px;
    }
    a:hover .sns-icon-inbox{
      width:24px;
      height:24px;
      background-position:-24px -144px;
    }
    .sns-icon-rss {
      width:24px;
      height:24px;
      background-position:0px -120px;
    }
    a:hover .sns-icon-rss{
      width:24px;
      height:24px;
      background-position:-24px -120px;
    }
    #nav-top-right{
        margin-top:-55px;
    }
    
    #nav-top-right>li,#nav-top-right > li > a {
      padding:0;
    }
    #nav-top-left > li > a{
      padding:8px 0 0 0;
    } 
    #nav-top-left > li > a {
      margin-top:5px;
      font-size:14px;
    } 

    .post-meta{
      padding-top:3px;
    }

    #breadcrumb{
      margin-top:20px;
    }

    .breadcrumb{
      background: #fff;
    }
    .topnav{
      height:35px;
    }

    /*评论数*/
    .post-comment-nums{
      display:none;
    }

    #form-search-query{
        margin-top:8px;
    }
    
    #nav-top-left a:hover{
        color:#fff;
    }
    
    a {
        color:#000;
    }
    #sidebar a{
     color:#000;
    }

    #logo{
        padding-top:10px;
        color:#fff;
        font-size:24px;
    }
    #banner{
    	height: 400px;
		background:  url({$_banner背景图}) center top no-repeat;
    }
    #banner1{
    	height: 55px;
    }
#ds-reset .ds-avatar img{

    width:54px;height:54px; /*设置图像的长和宽，这里要根据自己的评论框情况更改*/

    border-radius: 27px;/*设置图像圆角效果,在这里我直接设置了超过width/2的像素，即为圆形了*/

    -webkit-border-radius: 27px;/*圆角效果：兼容webkit浏览器*/

    -moz-border-radius:27px;

    box-shadow: inset 0 -1px 0 #3333sf;/*设置图像阴影效果*/

    -webkit-box-shadow: inset 0 -1px 0 #3333sf;

    -webkit-transition: 0.4s;

    -webkit-transition: -webkit-transform 0.4s ease-out;

    transition: transform 0.4s ease-out;/*变化时间设置为0.4秒(变化动作即为下面的图像旋转360读）*/

    -moz-transition: -moz-transform 0.4s ease-out;

}

#ds-reset .ds-avatar img:hover{/*设置鼠标悬浮在头像时的CSS样式*/

    box-shadow: 0 0 10px #fff; rgba(255,255,255,.6), inset 0 0 20px rgba(255,255,255,1);

    -webkit-box-shadow: 0 0 10px #fff; rgba(255,255,255,.6), inset 0 0 20px rgba(255,255,255,1);

    transform: rotateZ(360deg);/*图像旋转360度*/

    -webkit-transform: rotateZ(360deg);

    -moz-transform: rotateZ(360deg);

}



/*我是谁*/
.alignright {
float:right;
}
img {
border:0;
}
img.avatar {
float:left;
padding-right:5px;
}
p img {
max-width:100%;
padding:0;
}
img.alignleft{
margin: 3px 10px 2px 0;
}
img.alignright{ 
margin: 3px 0 2px 10px;
}
img.alignleft, img.alignright{
padding: 4px;
border: 1px solid #eee;
display: inline;
}



	</style>  
</head>
<body class="bg">
  <!-- #navbar -->
    <div class="navbar navbar-fixed-top navbar-inverse" id="navbar">
      <div class="navbar-inner topnav">
        <div class="container">
            <a class="pull-left brand " id="logo" href="{$global.url}" title="{$global.description}">{$global.name}</a>
            <div class="nav-collapse collapse">
                <ul class="nav" id="nav-top-left">
                  <li>
                    <a href="/" title="返回首页">首页</a>
                  </li>
                  <li>
                    <a href="{$page.url}" title="{$page.title}">{$page.title}</a>
                  </li>
                  <li>
                    <a href="{$global.archive_url}" title="存档">存档</a>
                  </li>
                </ul>
                <form class="" action="/">
                  <input id="form-search-query" type="text" name="q" class="search-query" placeholder="搜索一下,发现惊喜!">
                </form>
                <ul class="nav pull-right" id="nav-top-right">
                    <li>
                        <a href="{$inbox.url}" title="留言 : 有什么口水,可以放在这里的,不会别人看见的.">
                            <i class="sns-icon-inbox"></i>
                        </a>
                    </li>
                    <li>
                        <a href="{$_新浪微博地址}" title="新浪微博 : 经常活跃在新浪微博上面,能看到更多专业内容!">
                            <i class="sns-icon-sina"></i>
                        </a>
                    </li>
                    <li>
                        <a href="{$_腾讯微博地址}" title="腾讯微博 : 作为一个熟人社交的网络空间,了解生活的点点滴滴!">
                            <i class="sns-icon-qq"></i>
                        </a>
                    </li>
                    <li>
                        <a href="{$meta.rss}" title="RSS订阅 : 通过RSS订阅,您不会漏掉文章的阅读的!">
                            <i class="sns-icon-rss"></i>
                        </a>   
                    </li>
                </ul>
            </div>
        </div>
      </div>
    </div>

   
  <!-- /#navbar -->
<div id="banner1"></div>
	
	
  <!-- #breadcrumb -->
  <div class="container">
    <div class="row">
      <div class="span12 post-nav" id="breadcrumb">
        <ul class="breadcrumb">
	        <li>
	          您所在的位置: 
	        </li>
	        <li>
	          <a href="{$global.url}" title="{$global.name|nohtml}">首页</a> <span class="divider">/</span>
	        </li>
			<li>{$global.name}-{$global.description}</li>
	        <li class="pull-right" id="mingyan"></li>
        </ul>
      </div>
    </div>
  </div>
  <!-- / #breadcrumb -->

