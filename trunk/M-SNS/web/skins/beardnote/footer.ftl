
    <!-- footer -->
    <footer class="footer">
      <p>
        @2012 <a href="{$global.url}" title="{$global.description|nohtml}}"> {$global.name}</a> |  
        Powered By <a href="http://diandian.com" target="_blank" title="点点,让博客从此简单!">点点网</a> | 
        Designed by <a href="http://beardnote.com/" target="_blank" title="胡子笔记的主人!">大鹏</a>
      </p>
    </footer>
    <!-- / footer -->
  </div>
  <!-- /#container -->

  <!--backtop-->
  <div data-widget="backtop"></div>
  <!-- / backtop-->



  <!-- 引入js -->
  <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
  <script src="${template_url}/js/html5.js"></script>
  <![endif]-->
  <!-- jquery js -->
  <script type="text/javascript" src="${template_url}/js/jquery-1.7-latest.js"></script>
  <!-- bootstrap js -->
  <script type="text/javascript" src="${template_url}/js/bootstrap.min.js"></script>
  <!-- 图片等比例缩放-->
  <script type="text/javascript" src="${template_url}/js/loadimage.js"></script>
  <!-- 返回顶部 -->
  <script src="${template_url}/js/backtop.js" charset="utf-8"></script>
  <!-- 名言 -->
  <script type="text/javascript" src="http://pmeapp.duapp.com/static/mingyan.js"></script>

  <script type="text/javascript">
    jQuery(document).ready(function($) {

      // tooltip
      $('#navbar a').tooltip({
        placement:"bottom"
      });

      $('#container a').tooltip({
        placement:"top"
      });


      // 标题动态加载
      $('.post-title h4 a').click(function(e){
           e.preventDefault();
          var htm='正在努力的加载中...',i=9,
            t=$(this).html(htm).unbind('click');
          (function ct(){
            i<0?(i=9,t.html(htm),ct()):(t[0].innerHTML+='.',i--,setTimeout(ct,200));
          })();
          window.location=this.href;//opera fixed
      });

      //鼠标移动，图片高亮
      $('.post-title img').hover(
        function() {$(this).fadeTo("fast", 0.5);},
        function() {$(this).fadeTo("fast", 1.1);
      });


      //图片等比例缩放
      $(".post-thumbnail img").LoadImage({
        scaling : true,
        width : 170,
        height : 130,
        loadpic:"http://x.libdd.com/farm1/6424fa/c106a7b5/default.jpg"
      });
    
      if($.browser.msie && parseInt($.browser.version, 10) === 6) {
        $("body").css({"padding-top":"0"});
        $("#ad1 img").css({"height":"240px","width":"250px"});
        $("#breadcrumb").css({"margin-top":"15px"});
        $("#sidebar").css({"margin-top":"15px"});
        $(".pagination ul li").css({"float":"left","margin-left":"10px"});
        $(".ds-recent-comments").attr({ "data-show-avatars": "0"});
      }

      $('img').hover(
        function() {$(this).fadeTo("fast", 0.5);},
        function() {$(this).fadeTo("fast", 1.1);
      });
    
    
    });
  </script>


</body>
</html>