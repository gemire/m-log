<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <title>M-SNS</title>
    <link media="all" href="${template_url}/style/alice.css" rel="stylesheet">
    <link media="all" href="${template_url}/style/common.css" rel="stylesheet">
    </head>

    <body>
    <div class="topbar">
    	<div class="grid-990 topbar-wrap fn-clear">
    		<p class="toplink">
        		<a href="http://mobile.alipay.com/index.htm" class="mobile" target="_blank" seed="toplink-mobile" smartracker="on"><i class="icon-mobile"></i>M-SNS</a>
        	</p>
        	<ul class="topmenu">
          		<li class="topmenu-item topmenu-item-first"><a href="https://auth.alipay.com/login/index.htm" target="_blank" title="登录" seed="topmenuItem-link" smartracker="on">登录</a><b class="split">-</b><a href="https://memberprod.alipay.com/account/reg/index.htm" target="_blank" title="注册" seed="topmenuItem-linkT1" smartracker="on">注册</a></li>
          		<li class="topmenu-item topmenu-item-dropdown" id="J-topmenu-dropdown">
          			<a href="https://lab.alipay.com/user/i.htm" target="_blank" seed="JTopmenuDropdown-link" smartracker="on">我的空间<i class="icon-dropdown"></i></a>
          			<ul class="ui-list">
            			<li class="ui-list-item"><a href="https://lab.alipay.com/user/depositDelegateController.htm" target="_blank">充值</a></li>
            			<li class="ui-list-item"><a href="https://lab.alipay.com/consume/record/index.htm" target="_blank">交易记录</a></li>
            			<li class="ui-list-item"><a href="https://shenghuo.alipay.com/transfer/index.htm" target="_blank">转账</a></li>
          			</ul>
          		</li>
          		<li class="topmenu-item topmenu-item-last"><a href="http://help.alipay.com/lab/index.htm" target="_blank" title="帮助中心" seed="topmenuItem-linkT2" smartracker="on">帮助中心</a></li>
        	</ul>
      	</div>
    </div>
    <div class="wrapper">
    	<div class="ui-grid-row">
            <div class="ui-grid-25">
                <h1>M-LOG会员中心</h1>
            </div>
        </div>
        <#include "nav.ftl"/>