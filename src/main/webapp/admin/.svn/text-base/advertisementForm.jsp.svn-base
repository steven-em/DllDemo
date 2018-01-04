<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl">广告管理&gt; </span>
		<span class="child-name fl">广告详情</span>
	</div>
</div>
<div class="panel">
	<div class="panel-title">
		<h3>基本信息</h3>
		<span class="up-down-btn up-icon" title="收起"></span>
	</div>
	<div class="panel-content">
		<form action="${pageContext.request.contextPath }/admin/advertisement/save" method="post" id="submitForm">
			<input type="hidden" id="id" name="id" value="${advertisement.id}"/> 
			<input type="hidden" id="version" name="version" value="${advertisement.version }" />
			<div style="width: 49%; float: left;">
				<div class="contain-box">
					<label class="c-lable"><span class="red">*</span>标题：</label>	
					<input type="text" id="title" onblur="requiredValid(this);" name="title" class="c-input" value="${advertisement.title }" style="width: 200px;"/>
				</div>
				<div class="contain-box">
					<label class="c-lable"><span class="red">*</span>广告位置：</label>	
					<%-- <input type="text" id="positionId" name="positionId" class="c-input" value="${advertisement.positionId }" /> --%>
					<select id="positionId" name="positionId" onblur="requiredValid(this);" >
						<option value="">--请选择广告位置--</option>
						<c:forEach items="${adPositionList }" var="adPosition">
							<option value="${adPosition.id }" ${adPosition.id == advertisement.positionId ?'selected="selected"':'' }>${adPosition.title }</option>
						</c:forEach>
					</select>
				</div>
				<div class="contain-box">
					<label class="c-lable"><span class="red">*</span>状态：</label>	
					<input type="radio" name="status" id="status0" value="0" ${advertisement.status == 0 ? 'checked="checked"':'' }/><label for="status0" style="cursor: pointer;">禁用</label>
					<input type="radio" name="status" id="status1" value="1" ${advertisement.status != 0 ? 'checked="checked"':'' }/><label for="status1" style="cursor: pointer;">激活</label>
				</div>
				<div class="contain-box">
					<label class="c-lable">描述：</label>	
					<%-- <input type="text" id="shortDesc" name="shortDesc" class="c-input" value="${advertisement.shortDesc }" style="width: 200px; height: 100px;"/> --%>
					<textarea id="shortDesc" name="shortDesc" rows="5" cols="31">${advertisement.shortDesc }</textarea>
				</div>
			</div>
			
			<div style="width: 49%; float: right;">
				<div class="contain-box">
					<label class="c-lable">跳转地址：</label>	
					<input type="text" id="redirectUrl" name="redirectUrl" class="c-input" value="${advertisement.redirectUrl }" style="width: 300px;"/>
				</div>
				<div class="contain-box">
					<label class="c-lable">图片：</label>	
					<div class="c-snedimage" align="center" style="margin: 5px;">
						<input name="image" alt="上传图片" id="iamgeBtn" type="file" style="width: 140px; height: 120px; position: absolute; opacity:0; cursor: pointer;">
						<input type="hidden" id="imageUrl" name="imageUrl" class="c-input" value="${advertisement.imageUrl }">
		                <img id="imagePreview" src="${imagePath}${advertisement.imageUrl }" onerror="this.src='${ctxPath }/images/no_photo.jpg'" width="140px" height="120px">
	               	</div>
				</div>
				<%-- <div class="contain-box">
					<label class="c-lable">视频地址：</label>	
					<input type="text" id="videoUrl" name="videoUrl" class="c-input" value="${advertisement.videoUrl }" />
					<input name="file" alt="上传图片" id="fileBtn" type="file" style="cursor: pointer;"/>
					<span onclick="previewVideo();" style="cursor: pointer;">预览</span>
				</div> --%>
				<div class="contain-box">
					<label class="c-lable">排序：</label>	
					<input type="text" id="sortOrder" name="sortOrder" class="c-input" value="${advertisement.sortOrder }" />
				</div>
				
			</div>
			
		</form>
		<div class="panel-footer">
			<span class="save-btn bor-radius" id="saveBtn">保存</span>
		</div>
	</div>	
</div>
<div class="layer_notice" style="display: none;">
	<video src="" autoplay="autoplay" id="previewObj" width="100%" height="100%"></video>
</div>
<script type="text/javascript" src="${ctxPath }/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxPath }/js/uploadfile.js"></script>

<script type="text/javascript">
	$(function(){
		$(document).on("click","#saveBtn",function(){
			$("#submitForm :input").trigger("blur");
			if(!$(".validError").is(":visible")){
				var param = $("#submitForm").serializeArray();
				var index = layer.load();
				$.ajax({
					type:"POST",
					url: __ctxPath+"/advertisement/save",
					data: param,
					dataType:"json",
					success: function(result){
						if(result.success){
							$("#id").val(result.advertisement.id);
							$("#version").val(result.advertisement.version);
							layer.msg("保存成功", {icon: 6, time:2000}, function(){
								window.location = __ctxPath+"/advertisement/index";
							});
						} else {
							layer.msg("发生错误，操作失败！", {icon: 3});
						}
						layer.close(index);
					},
					error: function(xhr, status, error){
						layer.close(index);
						layer.msg("通讯发生错误，请重新尝试或刷新", {icon: 3});
					}
				});
			}
		});
	});
	$("#fileBtn").change(function() {
		uploadVideo($('#fileBtn'), $('#videoUrl'), $('#preview'));
    });
	$("#iamgeBtn").change(function() {
        uploadfile($('#iamgeBtn'), $('#imageUrl'), $('#imagePreview'));
    });
	function previewVideo() {
		var video = $("#videoUrl").val();
		if (video == "") {
			layer.msg("请上传视频", {
				icon : 3
			});
		} else {
			layer.open({
				type : 1,
				shade : false,
				title : false, //不显示标题
				area : [ '600px', '300px' ], //宽高
				content : $('.layer_notice'), //捕获的元素
				btn : false,
				success : function() {
					$("#previewObj").attr("src", __imagePath + video);
				}
			});

		}
	}
</script>
	