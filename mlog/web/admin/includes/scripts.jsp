<%--
@author Gao Youbo (http://www.mspring.org)
@since 2011-12-06
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ss" uri="/struts-tags"%>

<ss:if test="hasActionMessages()">
	<ss:iterator value="actionMessages">
		<script type="text/javascript">
			var timer;
			$.dialog({
			    content: '<ss:property escape="false"/>',
			    init: function () {
			        var that = this, i = 3;
			        var fn = function () {
			            that.title(i + '秒后关闭');
			            !i && that.close();
			            i--;
			        };
			        timer = setInterval(fn, 1000);
			        fn();
			    },
			    close: function () {
			        clearInterval(timer);
			    },
			    max: false,
			    min: false
			});
		</script>
	</ss:iterator>
</ss:if>

<ss:if test="hasActionErrors()">
	<ss:iterator value="actionErrors">
		<script type="text/javascript">
			$.dialog({
				drag: true,
				lock:true,
			    content: '<ss:property escape="false"/>',
			    icon: 'error.gif'
			});
		</script>
	</ss:iterator>
</ss:if>

<ss:if test="hasScripts()">
	<ss:iterator value="scripts">
		<script type="text/javascript">
			<ss:property escape="false" />
		</script>
	</ss:iterator>
</ss:if>