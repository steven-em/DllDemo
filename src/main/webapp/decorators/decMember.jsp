<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/web/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html lang="en">
	<head>
		<title><decorator:title />Workingdom-个人中心</title>
		<link rel="shortcut icon" href="${ctxPath }/images/logo.ico" type="image/x-icon" />
		<%@ include file="./include/web/meta.jspf"%>
		<%@ include file="./include/web/styles.jspf"%>
		<script type="text/javascript" src="${ctxPath }/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript">
			if (navigator.userAgent.match(/AppleWebKit.*Mobile.*/) || navigator.userAgent.indexOf('iPhone') != -1 || navigator.userAgent.indexOf('iPod') != -1) {
				window.location = __imagePath+"/wap/login.jsp"; 
			}
		</script>
		<script type="text/javascript" src="${ctxPath }/js/plugn/layer-v2.0/layer/layer.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/common.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/validation.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/plugn/layer-v2.0/layer/layer.js"></script>
		<decorator:head />
	</head>
	<body  class="body-bg"><!-- decMember.jsp -->
		<%-- -----------头部：start------------- --%>
	  	<%@ include file="include/web/header.jspf" %>
	 	<%-- -----------头部：end------------- --%>
		<!-- ---------------------会员中心装饰器文件----------------------- -->
		<div class="container backstage">
		<%@ include file="include/web/memberMenu.jspf" %>
		<%@ include file="include/web/memberHeader.jspf" %>
		</div>
		<div class="container">
		<%-- -----------尾部：start------------- --%>
		<%@ include file="./include/web/version.jspf"%>
		<%-- -----------尾部：end------------- --%>
		</div>
	</body>

<script>
	$(function(){
		var mydate = new Date();
		$(".data").html(mydate.getDate()+"<span>日</span>");
		show_time();
		setInterval(function() { show_time(); }, 10000);
	});
    $(".backstage-right .text-name").on("click",function(){
        var className=$(this).attr("class");
        if(className=="pull-right text-name"){
            $(this).attr("class","pull-right text-name onclick");
            $(".backstage-right .text-name .user-info").attr("class","user-info");
        }else{
            $(this).attr("class","pull-right text-name");
            $(".backstage-right .text-name .user-info").attr("class","user-info hidden");
        }
    });
    function show_time(){
		var now = new Date()
	    var nowH = now.getHours();
	    var nowMi = now.getMinutes();
	    if(nowMi < 10){
	    	nowMi = "0"+nowMi;
	    }
		now = now.toLocaleString();
		var _p ="<p>PM</p>";
	    if(now.indexOf("上午") > 0){
	    	_p ="<p>AM</p>";
	    }
	    $('.time').html(_p+nowH+":"+nowMi);
	}
</script>
</html>