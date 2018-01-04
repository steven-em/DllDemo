<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/admin/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getServletPath();
	path = path.substring(0, path.lastIndexOf("."));
	request.setAttribute("currPath", path);
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<title><decorator:title />智能人脸识别-管理系统</title>
		<link rel="shortcut icon" href="${ctxPath }/images/logo.ico" type="image/x-icon" />
		<%@ include file="./include/admin/meta.jspf"%>
		<%@ include file="./include/admin/styles.jspf"%>
		<script type="text/javascript" src="${ctxPath }/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/common.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/validation.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/plugn/layer-v2.0/layer/layer.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/plugn/layer-v2.0/layer/extend/layer.ext.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/plugn/laydate/laydate.js"></script>
		<decorator:head />
		<script>
 			$(function(){
				var height = $(window).height()-78;
				var width = 1019;
 				$(".main-right").css({width: width+"px", height: height+"px"});
 			});
			window.onresize=function(){  
               	var height = $(window).height()-60;
 				$(".main-right").css({height: height+"px"});
            }
 			
 			$.ajaxSetup({
		  		cache:false,
			   	success:function(data){
			   		if(data!=null && data!=undefined && data.indexOf("loginForm")!=-1){
			   			layer.msg("登录超时了，重新登录", {icon: 3}, function(){
							window.location = __ctxPath+"/login.jsp";
			   			});
					}
			   		if(data!=null && data!=undefined && data.indexOf("__wkd_no_permission__")!=-1){
			   			layer.msg("没有此功能的访问权限", {icon: 2});
					}
			   	},
			   	error:function(xhr){
			   		layer.closeAll();
			   		var data = xhr.responseText;
			   		if(data!=null && data!=undefined && data.indexOf("__wkd_no_permission__")!=-1){
			   			layer.msg("没有此功能的访问权限", {icon: 2});
					} else {
				   		layer.msg("发生错误，请刷新重试~", {icon: 2});
					}
			   	}
			});
 			
 			// 按回车触发搜索
 			$(document).keydown(function(event){
 				if(event.keyCode==13){  
					if(jQuery.isFunction(search)){  
						$("#pageNum").val(1);
						search();   
	 				} 
 		        	return false;
 		         }
 			});
 			
		</script>
		<style type="">
			.ui-autocomplete {
			    max-height: 200px;
			    overflow-y: auto;
			    /* prevent horizontal scrollbar */
			    overflow-x: hidden;
			  }
			  /* IE 6 doesn't support max-height
			   * we use height instead, but this forces the menu to always be this tall
			   */
			  * html .ui-autocomplete {
			    height: 200px;
			  }
		</style>
	</head>

	<body style="background-color:#fff">
		<div class="header" align="center">
			<%@ include file="include/admin/header.jspf" %>
		</div>
		<div class="wrapper">
			<div class="wrapper-main" style="margin-bottom: 10px;">
				<!-- 标准企业版 start-->
				<div class="main-left fl">
					<div id="hideLeft" style="left: 168px; position: absolute !important; top: 80px; z-index: 1000; cursor: pointer;" s="1">
						<img src="${ctxPath }/images/right.png" title="收起菜单"/>
					</div>
					<%@ include file="include/admin/sideMenu.jspf" %>
				</div>
				<div class="main-right fl" style="overflow: auto;">
					<%-- ========== 提示信息开始 ========= --%>
					<%@ include file="include/admin/messages.jspf"%>
					<%-- ========== 提示信息结束 ========= --%>
					<decorator:body />
				</div>
			</div>
		</div>
		<%-- ========== 界面插件引入开始 ========= --%>
		<%@ include file="./include/jsCssLib/pluginLib.jspf"%>
		<%-- ========== 界面插件引入结束 ========= --%>
	</body>
</html>
