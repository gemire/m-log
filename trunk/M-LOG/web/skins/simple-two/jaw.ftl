<#include "header.ftl" />
<#import "include/fun.ftl" as fun>
		<link rel="stylesheet" type="text/css" href="${template_url}/style/jaw.css">
		<script type="text/javascript" src="${template_url}/script/jaw.js"></script>
		<script type="text/javascript" src="${base}/script/jquery.form.js"></script>
		<script type="text/javascript" src="${template_url}/script/easytemplate.js"></script>
		<div class="row-fluid">
			<#-- <#include "sidebar.ftl" /> -->
			<div class="span12">
				<div class="row-fluid">
					<form id="jaw-form" action="${base}/jaw/add" method="POST">
						<div class="jaw-input-container">
							<input type="hidden" name="content" id="content" />
							<div id="jaw-input" class="jaw-input" contenteditable="true"></div>
						</div>
						<style type="text/css">
						#layer {
						  display: none;
						  position: absolute;
						  z-index: 9999;
						  margin: 5px 0 0 0;
						}
						
						#layer-popup .layer-popup-title {
						  padding: 3px 5px;
						  margin: 0 0 10px 0;
						  font-weight: bold;
						}
						
						.layer-popup-title {
						  line-height: 30px;
						  border-bottom: 1px solid #EEE;
						  font-size: 14px;
						}
						
						#layer-popup .layer-popup-title a {
						  float: right;
						}
						
						#layer .l {
						  margin: 0 0 10px 0;
						}
						
						#layer-popup-arrow {
						  background-image: url('${base}/images/emtip/up_arrow.gif');
						  background-repeat: no-repeat;
						  padding-top: 5px;
						}
						
						#layer-popup {
						  width: 392px;
						  border: 2px solid #CCC;
						  background: #F8F7F7;
						  padding: 5px;
						}
						
						#layer-popup a.emotion_img {
						  float: left;
						  width: 24px;
						  height: 24px;
						  background-image: url('http://city.oschina.net/js/ke/plugins/emoticons/qq.gif');
						  background-repeat: no-repeat;
						  margin: 0 2px 4px 0;
						  border: 1px solid #F6F6F6;
						}
						
						#layer-popup a.emotion_img:hover {
						  border: 1px solid #5CC26F;
						}
						</style>
						<div id="layer" style="display: none;">
					    	<div id="layer-popup-arrow" class="tweet_emotion">
					    		<div id="layer-popup">
									<div id="TweetEmotions">
				                		<div class="layer-popup-title">
				                			<a href="javascript:;" onclick="$('#layer').hide();return false;">关闭</a>插入表情
				                		</div>
                	                	<a href="javascript:osc.group.insert_image(0);" class="emotion_img" style="background-position: -0px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(1);" class="emotion_img" style="background-position: -24px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(2);" class="emotion_img" style="background-position: -48px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(3);" class="emotion_img" style="background-position: -72px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(4);" class="emotion_img" style="background-position: -96px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(5);" class="emotion_img" style="background-position: -120px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(6);" class="emotion_img" style="background-position: -144px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(7);" class="emotion_img" style="background-position: -168px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(8);" class="emotion_img" style="background-position: -192px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(9);" class="emotion_img" style="background-position: -216px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(10);" class="emotion_img" style="background-position: -240px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(11);" class="emotion_img" style="background-position: -264px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(12);" class="emotion_img" style="background-position: -288px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(13);" class="emotion_img" style="background-position: -312px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(14);" class="emotion_img" style="background-position: -336px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(15);" class="emotion_img" style="background-position: -360px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(16);" class="emotion_img" style="background-position: -384px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(17);" class="emotion_img" style="background-position: -408px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(18);" class="emotion_img" style="background-position: -432px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(19);" class="emotion_img" style="background-position: -456px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(20);" class="emotion_img" style="background-position: -480px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(21);" class="emotion_img" style="background-position: -504px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(22);" class="emotion_img" style="background-position: -528px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(23);" class="emotion_img" style="background-position: -552px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(24);" class="emotion_img" style="background-position: -576px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(25);" class="emotion_img" style="background-position: -600px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(26);" class="emotion_img" style="background-position: -624px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(27);" class="emotion_img" style="background-position: -648px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(28);" class="emotion_img" style="background-position: -672px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(29);" class="emotion_img" style="background-position: -696px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(30);" class="emotion_img" style="background-position: -720px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(31);" class="emotion_img" style="background-position: -744px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(32);" class="emotion_img" style="background-position: -768px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(33);" class="emotion_img" style="background-position: -792px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(34);" class="emotion_img" style="background-position: -816px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(35);" class="emotion_img" style="background-position: -840px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(36);" class="emotion_img" style="background-position: -864px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(37);" class="emotion_img" style="background-position: -888px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(38);" class="emotion_img" style="background-position: -912px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(39);" class="emotion_img" style="background-position: -936px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(40);" class="emotion_img" style="background-position: -960px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(41);" class="emotion_img" style="background-position: -984px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(42);" class="emotion_img" style="background-position: -1008px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(43);" class="emotion_img" style="background-position: -1032px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(44);" class="emotion_img" style="background-position: -1056px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(45);" class="emotion_img" style="background-position: -1080px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(46);" class="emotion_img" style="background-position: -1104px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(47);" class="emotion_img" style="background-position: -1128px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(48);" class="emotion_img" style="background-position: -1152px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(49);" class="emotion_img" style="background-position: -1176px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(50);" class="emotion_img" style="background-position: -1200px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(51);" class="emotion_img" style="background-position: -1224px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(52);" class="emotion_img" style="background-position: -1248px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(53);" class="emotion_img" style="background-position: -1272px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(54);" class="emotion_img" style="background-position: -1296px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(55);" class="emotion_img" style="background-position: -1320px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(56);" class="emotion_img" style="background-position: -1344px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(57);" class="emotion_img" style="background-position: -1368px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(58);" class="emotion_img" style="background-position: -1392px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(59);" class="emotion_img" style="background-position: -1416px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(60);" class="emotion_img" style="background-position: -1440px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(61);" class="emotion_img" style="background-position: -1464px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(62);" class="emotion_img" style="background-position: -1488px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(63);" class="emotion_img" style="background-position: -1512px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(64);" class="emotion_img" style="background-position: -1536px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(65);" class="emotion_img" style="background-position: -1560px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(66);" class="emotion_img" style="background-position: -1584px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(67);" class="emotion_img" style="background-position: -1608px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(68);" class="emotion_img" style="background-position: -1632px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(69);" class="emotion_img" style="background-position: -1656px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(70);" class="emotion_img" style="background-position: -1680px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(71);" class="emotion_img" style="background-position: -1704px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(72);" class="emotion_img" style="background-position: -1728px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(73);" class="emotion_img" style="background-position: -1752px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(74);" class="emotion_img" style="background-position: -1776px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(75);" class="emotion_img" style="background-position: -1800px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(76);" class="emotion_img" style="background-position: -1824px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(77);" class="emotion_img" style="background-position: -1848px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(78);" class="emotion_img" style="background-position: -1872px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(79);" class="emotion_img" style="background-position: -1896px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(80);" class="emotion_img" style="background-position: -1920px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(81);" class="emotion_img" style="background-position: -1944px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(82);" class="emotion_img" style="background-position: -1968px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(83);" class="emotion_img" style="background-position: -1992px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(84);" class="emotion_img" style="background-position: -2016px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(85);" class="emotion_img" style="background-position: -2040px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(86);" class="emotion_img" style="background-position: -2064px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(87);" class="emotion_img" style="background-position: -2088px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(88);" class="emotion_img" style="background-position: -2112px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(89);" class="emotion_img" style="background-position: -2136px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(90);" class="emotion_img" style="background-position: -2160px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(91);" class="emotion_img" style="background-position: -2184px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(92);" class="emotion_img" style="background-position: -2208px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(93);" class="emotion_img" style="background-position: -2232px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(94);" class="emotion_img" style="background-position: -2256px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(95);" class="emotion_img" style="background-position: -2280px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(96);" class="emotion_img" style="background-position: -2304px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(97);" class="emotion_img" style="background-position: -2328px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(98);" class="emotion_img" style="background-position: -2352px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(99);" class="emotion_img" style="background-position: -2376px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(100);" class="emotion_img" style="background-position: -2400px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(101);" class="emotion_img" style="background-position: -2424px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(102);" class="emotion_img" style="background-position: -2448px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(103);" class="emotion_img" style="background-position: -2472px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(104);" class="emotion_img" style="background-position: -2496px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(105);" class="emotion_img" style="background-position: -2520px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(106);" class="emotion_img" style="background-position: -2544px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(107);" class="emotion_img" style="background-position: -2568px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(108);" class="emotion_img" style="background-position: -2592px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(109);" class="emotion_img" style="background-position: -2616px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(110);" class="emotion_img" style="background-position: -2640px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(111);" class="emotion_img" style="background-position: -2664px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(112);" class="emotion_img" style="background-position: -2688px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(113);" class="emotion_img" style="background-position: -2712px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(114);" class="emotion_img" style="background-position: -2736px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(115);" class="emotion_img" style="background-position: -2760px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(116);" class="emotion_img" style="background-position: -2784px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(117);" class="emotion_img" style="background-position: -2808px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(118);" class="emotion_img" style="background-position: -2832px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(119);" class="emotion_img" style="background-position: -2856px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(120);" class="emotion_img" style="background-position: -2880px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(121);" class="emotion_img" style="background-position: -2904px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(122);" class="emotion_img" style="background-position: -2928px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(123);" class="emotion_img" style="background-position: -2952px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(124);" class="emotion_img" style="background-position: -2976px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(125);" class="emotion_img" style="background-position: -3000px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(126);" class="emotion_img" style="background-position: -3024px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(127);" class="emotion_img" style="background-position: -3048px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(128);" class="emotion_img" style="background-position: -3072px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(129);" class="emotion_img" style="background-position: -3096px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(130);" class="emotion_img" style="background-position: -3120px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(131);" class="emotion_img" style="background-position: -3144px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(132);" class="emotion_img" style="background-position: -3168px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(133);" class="emotion_img" style="background-position: -3192px 0px;"></a>
                	                	<a href="javascript:osc.group.insert_image(134);" class="emotion_img" style="background-position: -3216px 0px;"></a>
            	    				</div>
					    			<div class="clearfix"></div>
					    		</div>
					    	</div>
					    </div>
						<div>
							<div class="pull-left">
								<i class="icon-camera" id="icon-camera"></i>
							</div>
							<div class="pull-right" style="padding-right:15px; padding-top:10px;">
								<input type="button" class="btn" onclick="mlog.jaw.add();" value="发表" id="btn-postjaw" />
							</div>
						</div>
					</form>
				</div>
				<div class="row-fluid">
					<ul class="jaws" id="jaws">
					</ul>
				</div>
			</div>
		</div>
<#include "footer.ftl" />