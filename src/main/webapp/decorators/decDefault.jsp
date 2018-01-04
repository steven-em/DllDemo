<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getServletPath();
	path = path.substring(0, path.lastIndexOf("."));
	if(path.contains("/company") || path.contains("/product")){
		path = "company";
	}
	request.setAttribute("currPath", path);
%>
<html lang="en">
	<head>
		<%@ page contentType="text/html; charset=UTF-8"%>
		<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
		<title>捕获人脸-<decorator:title /></title>
		<link rel="shortcut icon" href="${ctxPath }/images/logo.ico" type="image/x-icon" />
		<%@ include file="./include/wap/meta.jspf"%>
		<meta property="qc:admins" content="2201435667677231674756375" />
		<meta property="wb:webmaster" content="8f78ca019f894953" />
		<meta name="baidu-site-verification" content="MD5G2tEcTH" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<script type="text/javascript" src="${ctxPath }/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/plugn/layer-v2.0/layer/layer.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/common.js"></script>
		<script type="text/javascript" src="${ctxPath}/js/swiper.min.js"></script>
		<!--[if IE]><script type="text/javascript" src="${ctxPath }/js/html5.js"></script><![endif]-->
	    <!--[if lt IE 9]>
	    <script src="${ctxPath }/js/html5shiv.min.js"></script>
	    <script src="${ctxPath }/js/respond.min.js"></script>
	    <![endif]-->
		<decorator:head />
	</head>
		<decorator:body/>
	</body>
</html>
