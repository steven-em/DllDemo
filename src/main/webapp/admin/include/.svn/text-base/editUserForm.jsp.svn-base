<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<form action="${pageContext.request.contextPath }/user/save" method="post" id="userEditForm">
	<input type="hidden" id="id" name="id" value="${systemUser.id }">
	<input type="hidden" id="version" name="version" value="${systemUser.version }">
	<input type="hidden" id="username" name="username" value="${systemUser.username }">
	<input type="hidden" id="status" name="status" value="${systemUser.status }">
	<input type="hidden" id="roleId" name="roleId" value="${systemUser.roleId }">
	<input type="hidden" id="stores" name="stores" value="${systemUser.stores }">
	<input type="hidden" id="isSmsReceive" name="isSmsReceive" value="${systemUser.isSmsReceive }">
	<input type="hidden" id="setPwd" name="setPwd" value="true">
	<div class="panel-content">
		<div class="contain-box">
			<label class="c-lable"><span class="red">*</span>昵称：</label>
			<input type="text" id="nickname" name="nickname" class="c-input" value="${systemUser.nickname }" onblur="requiredValid(this);" style="width: 150px;">
			<i class="password-icon" title="修改密码" onclick="passwodEdit();">&nbsp;</i>
		</div>
		<div class="contain-box" style="display: none;" id="passwordDiv">
			<label class="c-lable"><span class="red">*</span>密码：</label>
			<input type="text" id="passwordS" name="passwordS" class="c-input" value="" style="width: 150px;">
			<input type="hidden" id="password" name="password" value="${systemUser.password }" onblur="requiredValid(this);"/>
		</div>
		<div class="contain-box">
			<label class="c-lable"><span class="red">*</span>手机号码：</label>
			<input type="text" id="mobile" name="mobile" class="c-input" value="${systemUser.mobile }" onblur="requiredValid(this);" style="width: 150px;">
		</div>
		<div class="contain-box">
			<label class="c-lable" style="vertical-align: top;"><span class="red">*</span>头像：</label>	
			<div class="c-snedimage" align="center">
				<input name="image" alt="上传图片" id="fileBtn" type="file" style="width: 100px; height: 100px; position: absolute; opacity:0; cursor: pointer;"/>
				<input type="hidden" id="headurl" name="headurl" class="c-input" value="${systemUser.headurl }" onblur="requiredValid(this);"/>
                <img id="preview" src="${imagePath }${systemUser.headurl}" onerror="this.src='${ctxPath }/images/no_photo.jpg'" width="100px" height="100px"/>
               </div>
		</div>
	</div>
</form>
<script type="text/javascript" src="${ctxPath }/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxPath }/js/uploadfile.js"></script>
<script type="text/javascript" src="${ctxPath }/js/md5.js"></script>
<script type="text/javascript">
	$(function(){
		$("#fileBtn").change(function() {
	        uploadfile($('#fileBtn'), $('#headurl'), $('#preview'));
	    });
	});
	function passwodEdit(){
		if($("#passwordDiv").is(":hidden")){
			$("#passwordDiv").show();
		} else {
			$("#passwordDiv").hide();
		}
	}
</script>