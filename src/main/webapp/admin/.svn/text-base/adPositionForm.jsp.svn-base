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
		<form action="${pageContext.request.contextPath }/admin/adPosition/save" method="post" id="submitForm">
			<input type="hidden" id="id" name="id" value="${adPosition.id}"/> 
			<input type="hidden" id="version" name="version" value="${adPosition.version }" />
			<div class="contain-box">
			<div class="contain-box">
				<label class="c-lable">广告位：</label>	
				<input type="text" id="title" name="title" class="c-input" value="${adPosition.title }" />
			</div>
				<label class="c-lable">位置编码：</label>	
				<input type="text" id="positionCode" name="positionCode" class="c-input" value="${adPosition.positionCode }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">状态：</label>	
				<input type="radio" name="status" id="status0" value="0" ${adPosition.status == 0 ? 'checked="checked"':'' }/><label for="status0" style="cursor: pointer;">禁用</label>
				<input type="radio" name="status" id="status1" value="1" ${adPosition.status == 1 ? 'checked="checked"':'' }/><label for="status1" style="cursor: pointer;">激活</label>
			</div>
			<div class="contain-box">
				<label class="c-lable">广告位宽高：</label>	
				<input type="text" id="width" name="width" class="c-input" value="${adPosition.width }" style="width: 62px;"/> -
				<input type="text" id="height" name="height" class="c-input" value="${adPosition.height }" style="width: 62px;"/> px
			</div>
		</form>
		<div class="panel-footer">
			<span class="save-btn bor-radius" id="saveBtn">保存</span>
		</div>
	</div>	
</div>

<script type="text/javascript">
	$(function(){
		$(document).on("click","#saveBtn",function(){
			$("#submitForm :input").trigger("blur");
			if(!$(".validError").is(":visible")){
				var param = $("#submitForm").serializeArray();
				var index = layer.load();
				$.ajax({
					type:"POST",
					url: __ctxPath+"/adPosition/save",
					data: param,
					dataType:"json",
					success: function(result){
						if(result.success){
							$("#id").val(result.adPosition.id);
							$("#version").val(result.adPosition.version);
							layer.msg("保存成功", {icon: 6, time:2000}, function(){
								window.location = __ctxPath+"/adPosition/index";
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
</script>