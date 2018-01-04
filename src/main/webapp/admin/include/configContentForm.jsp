<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<script type="text/javascript">
window.UMEDITOR_HOME_URL = '${ctxPath }/js/plugn/umeditor1_2_2-utf8-jsp/';
</script>
<script type="text/javascript" src="${ctxPath }/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxPath }/js/uploadfile.js"></script>
<link type="text/css" rel="stylesheet" href="${ctxPath }/js/plugn/umeditor1_2_2-utf8-jsp/themes/default/css/umeditor.css">
<script type="text/javascript" charset="utf-8" src="${ctxPath }/js/plugn/umeditor1_2_2-utf8-jsp/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxPath }/js/plugn/umeditor1_2_2-utf8-jsp/umeditor.js"></script>
<script type="text/javascript" src="${ctxPath }/js/plugn/umeditor1_2_2-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${ctxPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxPath}/js/uploadfile.js"></script>
<style>
<!--
.c-lable{width: 120px;}
-->
</style>
<div class="panel">
	<div class="panel-content">
		<form action="${pageContext.request.contextPath }/admin/office/save" method="post" id="submitForm">
			<c:forEach items="${configs }" var="config">
				<input type="hidden" name="id" value="${config.id}">
				<input type="hidden" name="type_${config.id}" value="${config.type}">
				<c:choose>
					<c:when test="${config.type==1}">
						<div class="contain-box">
							<label class="c-lable">${config.name}：</label> 
							<input type="text" id="content_${config.id}" name="content_${config.id}" class="c-input" value="${config.content}" onblur="numberValid(this);" />
							<c:if test="${not empty config.remark }">
								<i class="info-icon tip-layer" data-text="${config.remark }">&nbsp;</i>
							</c:if>
						</div>
					</c:when>
					<c:when test="${config.type==2}">
						<div class="contain-box">
							<label class="c-lable">${config.name}：</label> 
							<input type="text" id="content_${config.id}" name="content_${config.id}" class="c-input" value="${config.content}" onblur="doubleValid(this);" />
							<c:if test="${not empty config.remark }">
								<i class="info-icon tip-layer" data-text="${config.remark }">&nbsp;</i>
							</c:if>
						</div>
					</c:when>
					<c:when test="${config.type==3}">
						<div class="contain-box">
							<label class="c-lable">${config.name}：</label> 
							<input type="text" id="content_${config.id}" name="content_${config.id}" class="c-input" value="${config.content}" />
							<c:if test="${not empty config.remark }">
								<i class="info-icon tip-layer" data-text="${config.remark }">&nbsp;</i>
							</c:if>
						</div>
					</c:when>
					<c:when test="${config.type==4}">
						<div class="contain-box">
							<label class="c-lable">${config.name}：</label> 
							<input type="text" id="content_${config.id}" name="content_${config.id}" readonly="readonly" class="c-input" value="${config.content}" onclick="laydate({istime: false, format: 'YYYY-MM-DD'})" />
							<c:if test="${not empty config.remark }">
								<i class="info-icon tip-layer" data-text="${config.remark }">&nbsp;</i>
							</c:if>
						</div>
					</c:when>
					<c:when test="${config.type==5}">
						<!-- 富文本框 -->
						<div class="panel-title">
							<h3>${config.name}</h3>
							<span class="up-down-btn up-icon" title="收起"></span>
						</div>
						<div class="panel-content">
							<div style="margin: 10px;">
								<script type="text/plain" id="content_${config.id}" name="content_${config.id}" style="width: 95%; height: 200px;">${config.content}</script>
							</div>
						</div>
						<script type="text/javascript">
							UM.getEditor('content_${config.id}', {
								allowDivTransToP : false,
								autoHeigth : true,
								autoWidth : true
							})
						</script>
					</c:when>
					<c:when test="${config.type==6}">
					   <div class="contain-box">
							<label class="c-lable">${config.name}：</label> 
							<div class="c-snedimage" align="center" style="margin: 5px;">
								<input alt="上传文件" id="mainPicfileBtn${config.id}" name="file" type="file" style="width: 50px; height: 40px; position: absolute; opacity:0; cursor: pointer;">
				                <img id="mainPicPreview" src="${ctxPath }/images/no_photo.jpg" onerror="this.src='${ctxPath }/images/no_photo.jpg'" width="50px" height="40px">
				                <input type="text" id="content_${config.id}" name="content_${config.id}" class="c-input" value="${config.content}">
	               			</div>
							<c:if test="${not empty config.remark }">
								<i class="info-icon tip-layer" data-text="${config.remark }">&nbsp;</i>
							</c:if>
						</div>
						<script type="text/javascript">
						$(function(){
							$("#mainPicfileBtn${config.id}").change(function() {
								$.ajaxFileUpload(
						                {
						                    url: '${ctxPath}/upd/excel', //用于文件上传的服务器端请求地址
						                    secureuri: false, //一般设置为false
						                    type:'POST',
						                    fileElement: $('#mainPicfileBtn${config.id}'), //文件上传空间的id属性  <input type="file" id="file" name="file" />
						                    dataType: 'json', //返回值类型 一般设置为json
						                    success: function (data, status)  //服务器成功响应处理函数
						                    {
						                    	layer.closeAll('loading');
						                    	if(data.url){
						                    		$("#content_${config.id}").val(data.url);
						                    	}else if(data.checkFlag =='errorExt'){
						                    		layer.msg("请上传“xls、xlsx格式的文件", {icon: 3});
						                    	}else{
						                    		layer.msg("上传错误", {icon: 3});
						                    	}
						                    },
						                    error: function (data, status, e)//服务器响应失败处理函数
						                    {
						                    	layer.closeAll('loading');
						                    	
						                    	layer.msg("通讯发生错误，请重新尝试或刷新", {icon: 3});
						                    }
						                });
						    });
						});
						</script>
					</c:when>
					<c:when test="${config.type==7}">
						<!-- 富文本框 -->
						<div class="panel-title">
							<h3>${config.name}</h3>
							<span class="up-down-btn up-icon" title="收起"></span>
						</div>
						<div class="panel-content">
							<div style="margin: 10px;">
								<script type="text/plain" id="content_${config.id}" name="content_${config.id}" style="width: 95%; height: 200px;">${config.content}</script>
							</div>
						</div>
						<script type="text/javascript">
							UM.getEditor('content_${config.id}', {
								allowDivTransToP : false,
								autoHeigth : true,
								autoWidth : true
							})
						</script>
					</c:when>
					<c:when test="${config.type==8}">
						<div class="contain-box">
							<label class="c-lable">${config.name}：</label> 
							<input type="text" id="content_${config.id}" onblur="checkUrl(this);" name="content_${config.id}" class="c-input" value="${config.content}" />
							<c:if test="${not empty config.remark }">
								<i class="info-icon tip-layer" data-text="${config.remark }">&nbsp;</i>
							</c:if>
						</div>
					</c:when>
					<c:when test="${config.type==9}">
						<div class="contain-box">
							<label class="c-lable">${config.name}：</label> 
							<input type="text" id="content_${config.id}" name="content_${config.id}" class="c-input" value="${config.content}" />
							<c:if test="${not empty config.remark }">
								<i class="info-icon tip-layer" data-text="${config.remark }">&nbsp;</i>
							</c:if>
						</div>
					</c:when>
					<c:when test="${config.type==10}">
						<div class="contain-box">
							<label class="c-lable">${config.name}：</label> 
							<input type="hidden" id="content_${config.id}" name="content_${config.id}" class="c-input" value="${config.content}" />
							<c:set var="beforeUrl" value="${empty config.content ? ctxPath : imagePath }" />
							<c:set var="picValue" value="${empty config.content ? '/images/no_photo.jpg' : config.content }" />
			                <img class="_imagePreview" data-id="${config.id}" style="cursor: pointer;" id="imagePreview_${config.id}" src="${beforeUrl}${picValue}" onerror="this.src='${ctxPath }/images/no_photo.jpg'" width="140px" height="120px">
						</div>
					</c:when>
					<c:when test="${config.type==11}">
						<div class="contain-box">
							<label class="c-lable">${config.name}：</label> 
							<textarea rows="5" cols="60"  id="content_${config.id}" name="content_${config.id}">${config.content}</textarea>
						</div>
					</c:when>
				</c:choose>
			</c:forEach>
		</form>
		<div class="panel-footer">
			<span class="save-btn bor-radius" id="saveBtn1">保存</span>
		</div>
	</div>
</div>
<div style="display: none">
<input name="image" alt="上传图片" id="iamgeBtn" type="file" style="width: 140px; height: 120px; position: absolute; opacity:0; cursor: pointer;">
</div>
<script type="text/javascript" src="${ctxPath }/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxPath }/js/uploadfile.js"></script>
<script type="text/javascript">
	$(function() {
		$(".tip-layer").mouseover(function() {
			layer.tips($(this).attr("data-text"), $(this), {
				tips : [ 2, '#3595CC' ],
				time : -1
			});
		}).mouseout(function() {
			layer.closeAll();
		});
		//保存操作
		$("#saveBtn1").click(function() {
			$("#submitForm :input").trigger("blur");
			if (!$(".validError").is(":visible")) {
				var param = $("#submitForm").serializeArray();
				var index = layer.load();
				$.ajax({
					type : "POST",
					url : __ctxPath + "/systemConfig/saveBatch",
					data : param,
					dataType : "json",
					success : function(result) {
						if (result.code == 1) {
							layer.msg("保存成功", {icon : 6, time : 2000 }, function() {
								$(".search-name-box li.cur").trigger("")
							});
						} else {
							layer.msg("发生错误，操作失败！", {icon : 3});
						}
						layer.close(index);
					},
					error : function(xhr, status, error) {
						layer.close(index);
						layer.msg("通讯发生错误，请重新尝试或刷新", { icon : 3 });
					}
				});
			}
		});
		var _urlElement = "";
		var _imageElement = "";
		$("._imagePreview").on("click",function(){
			var _id = $(this).attr("data-id");
			_urlElement = "content_"+_id;
			_imageElement = "imagePreview_"+_id;
			$("#iamgeBtn").click();
		});
		
		$("#iamgeBtn").change(function() {
	        uploadfile($('#iamgeBtn'), $('#'+_urlElement), $('#'+_imageElement));
	    });
	});
</script>

