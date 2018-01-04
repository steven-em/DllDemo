<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<style type="text/css">
<!--
<!--

-->
.exhibitionContain>div{
  float: left;
  margin: 5px;
  border: 1px solid #dcdcdc;
  position: relative;
}
.exhibitionContain img{
  width: 100px;
  height: 100px;
}
.exhibitionContain .banner{
  position: absolute;
  bottom: 0px;
  height: 20px;
  background: #cec9c9;
  width: 100%;
}
.exhibitionContain .banner i{
 display: block;
 float: left;
 height: 20px;
 cursor: pointer;
 margin-right: 10px;
}
-->
</style>

<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl">登记人员管理&gt; </span>
		<span class="child-name fl">新增人员</span>
	</div>
</div>
<div class="panel">
	<div class="panel-title">
		<h3>基本信息</h3>
		<span class="up-down-btn up-icon" title="收起"></span>
	</div>
	<div class="panel-content">
		<form action="${pageContext.request.contextPath }/admin/registeredMen/save" method="post" id="submitForm">
			<input type="hidden" id="id" name="id" value="${registeredMen.id}"/> 
			<input type="hidden" id="version" name="version" value="${registeredMen.version }" />
			<%-- <div class="contain-box">
				<label class="c-lable"><span style="color: red;">*</span>姓名：</label>	
				<input type="text" id="name" name="name" class="c-input" value="${registeredMen.name }" onblur="requiredValid(this);" />
			</div>
			<div class="contain-box">
				<label class="c-lable"><span style="color: red;">*</span>身份ID：</label>	
				<input type="text" id="identity" name="identity" class="c-input" value="${registeredMen.identity }" onblur="requiredValid(this);" />
			</div> --%>
			<div>
				<div class="panel-title">
					<h3>上传图片</h3>
					<div class="click-btn" style="position: relative; width: 100px;height: 26px;top: 6px;">
							<a href="javascript:void(0);"><i class="add-icon">上传</i></a>
							<input alt="上传图片" id="exhibitionBtn" type="file" name="image" style="width: 60px; height: 26px; position: absolute; opacity:0; cursor: pointer; left: 0px;top: 0px;z-index: 10;">
					</div>
					<span style="color: red;position: relative;top: 5px; margin-left: 20px;">最多只能上传3张图片</span>
				</div>
				<div style="overflow: hidden;" class="exhibitionContain">
				       <c:if test="${not empty registeredMen.image1 }">
						    <div data-url="${registeredMen.image1 }">
							    <img src="${imagePath }${registeredMen.image1}">
							     <div class="banner">
							       <i class="left-icon" onclick="prevExhibition(this);"></i>
							       <i class="delete-icon" onclick="delExhibition(this);"></i>
							       <i class="right-icon" onclick="nextExhibition(this);"></i>
							    </div>
						  	</div>
					  	</c:if>
					  	<c:if test="${not empty registeredMen.image2 }">
						    <div data-url="${registeredMen.image2 }">
							    <img src="${imagePath }${registeredMen.image2}">
							     <div class="banner">
							       <i class="left-icon" onclick="prevExhibition(this);"></i>
							       <i class="delete-icon" onclick="delExhibition(this);"></i>
							       <i class="right-icon" onclick="nextExhibition(this);"></i>
							    </div>
						  	</div>
					  	</c:if>
					  	<c:if test="${not empty registeredMen.image3 }">
						    <div data-url="${registeredMen.image3 }">
							    <img src="${imagePath }${registeredMen.image3}">
							     <div class="banner">
							       <i class="left-icon" onclick="prevExhibition(this);"></i>
							       <i class="delete-icon" onclick="delExhibition(this);"></i>
							       <i class="right-icon" onclick="nextExhibition(this);"></i>
							    </div>
						  	</div>
					  	</c:if>
				</div>
			</div>
		</form>
		<!-- <div class="panel-footer">
			<span class="save-btn bor-radius" id="saveBtn">保存</span>
		</div> -->
	</div>	
</div>

<script type="text/javascript" src="${ctxPath }/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function(){
		$(document).on("click","#saveBtn",function(){
			$("#submitForm :input").trigger("blur");
			if(!$(".validError").is(":visible")){
				if($(".exhibitionContain").children("div").length  == 0){
					layer.msg("至少上传1张图片！", {icon: 3});
					return false;
				}
				var param = $("#submitForm").serializeArray();
				$(".exhibitionContain").children("div").each(function(index,item){
					param.push({
						name : "image"+(index + 1),
						value : $(item).attr("data-url")
					});
				});
				var index = layer.load();
				$.ajax({
					type:"POST",
					url: __ctxPath+"/registeredMen/save",
					data: param,
					dataType:"json",
					success: function(result){
						if(result.success){
							$("#id").val(result.registeredMen.id);
							$("#version").val(result.registeredMen.version);
							layer.msg("保存成功", {icon: 6, time:2000}, function(){
								window.location = __ctxPath+"/registeredMen/index";
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
		//上传图片的代码
		$("#exhibitionBtn").change(function(){
			if($(".exhibitionContain").children("div").length  == 3){
				layer.msg("最多只能上传3张图片！", {icon: 3});
				return false;
			}
			$.ajaxFileUpload({
				url : __ctxPath+'/upd/base64',
				secureuri : false,
				fileElement : $('#exhibitionBtn'),
				dataType : 'json',
				type: 'POST',
				success : function(data) {
					layer.closeAll('loading');
	                if(data["checkFlag"] != 'yes'){
	                	if(data["checkFlag"]=="errorExt"){
	                         layer.msg("图片格式不正确，请选择jpg、gif、bmp格式的图片！", {icon: 3});
	                     }else if(data["checkFlag"]=="errorPoint"){
	                         layer.msg("图片文件名中有特殊字符，请去掉特殊字符！", {icon: 3});
	                     }else if(data["checkFlag"]=="errorSize"){
	                         layer.msg("图片太大，请选择30M以内图片！", {icon: 3});
	                     } else {
	                     	layer.msg("图片上传错误！", {icon: 3});
	                     }
	                }else{
	                	var html = '<div data-url="'+data.url+'">' +
									    '<img src="data:image/png;base64,'+data.data+'">' +
									     '<div class="banner">' +
									       '<i class="left-icon" onclick="prevExhibition(this);"></i>' +
									       '<i class="delete-icon" onclick="delExhibition(this);"></i>' +
									       '<i class="right-icon" onclick="nextExhibition(this);"></i>' +
									     '</div>' + 
								    '</div>' ;
	                	$(".exhibitionContain").html(html);
	                }
				}
			});
		});
		
	});
	
	function delExhibition(obj){
		$(obj).parent().parent().remove();
	}
	
	function prevExhibition(obj){
		var $target = $(obj).parent().parent();
		if($target.index() == 0){
			return;
		}
		$target.after($target.prev());
	}
	
	function nextExhibition(obj){
		var $target = $(obj).parent().parent();
		if($target.is(":last")){
			return;
		}
		$target.before($target.next());
	}
</script>