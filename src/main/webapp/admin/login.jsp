<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/admin/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>智能人脸识别-后台登录</title>
		<link rel="stylesheet" type="text/css" href="${ctxPath }/css/login.css">
	</head>
	<body>
		<div class="htmleaf-container">
			<div class="wrapper">
				<div class="container">
					<h1>智能人脸识别后台管理系统</h1>
					<form id="loginForm" action="${ctxPath }/login" method="post" onsubmit="return validationForm();">
						<input type="text" id="username" name="username" placeholder="Username"> 
						<input type="password" id="passwordS" name="usernameS" placeholder="Password">
						<input type="hidden" id="password" name="password">
						<button type="submit" id="login-button">Login</button>
					</form>
					<div style="font-size: 20px; color: red;" id="errorDiv">
						<c:choose>
							<c:when test="${param['msg'] eq '1'}">未发现此用户</c:when>
							<c:when test="${param['msg'] eq '2'}">密码错误</c:when>
							<c:when test="${param['msg'] eq '3'}">您被管理员禁用了</c:when>
						</c:choose>
					</div>
				</div>
				<ul class="bg-bubbles">
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>
		<script type="text/javascript" src="${ctxPath }/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="${ctxPath }/js/md5.js"></script>
		<script>
			$(function(){
				$("#loginForm :input").click(function(){
					$("#errorDiv").html("");
				});
			});
			$('#login-button').click(function(event) {
				event.preventDefault();
				$('#loginForm').fadeOut(500);
				$('.wrapper').addClass('form-success');
				$('#loginForm').submit();
			});
			
			function validationForm(){
				var userName = $("#username").val();
				if(userName == ""){
					return false;
				}
				var password = $("#passwordS").val();
				if(password == ""){
					return false;
				} else {
					password=hex_md5(password);
					$("#password").val(password);
				}
				return true;
			}
		</script>
	</body>
</html>
