<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl"><fmt:message key="nav.admin"/>&gt; </span>
		<span class="child-name fl"><fmt:message key="memberDetail.heading"/></span>
	</div>
</div>
<div class="panel">
	<div class="panel-title">
		<h3>基本信息</h3>
		<span class="up-down-btn up-icon" title="收起"></span>
	</div>
	<div class="panel-content">
		<form action="${pageContext.request.contextPath }/admin/member/save" method="post" id="submitForm">
			<input type="hidden" id="id" name="id" value="${member.id}"/> 
			<input type="hidden" id="version" name="version" value="${member.version }" />
			<div class="contain-box">
				<label class="c-lable">createDate：</label>	
				<input type="text" id="createDate" name="createDate" class="c-input" value="${member.createDate }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">createdBy：</label>	
				<input type="text" id="createdBy" name="createdBy" class="c-input" value="${member.createdBy }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">updateDate：</label>	
				<input type="text" id="updateDate" name="updateDate" class="c-input" value="${member.updateDate }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">updatedBy：</label>	
				<input type="text" id="updatedBy" name="updatedBy" class="c-input" value="${member.updatedBy }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">version：</label>	
				<input type="text" id="version" name="version" class="c-input" value="${member.version }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">basicInfoId：</label>	
				<input type="text" id="basicInfoId" name="basicInfoId" class="c-input" value="${member.basicInfoId }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">collectTotal：</label>	
				<input type="text" id="collectTotal" name="collectTotal" class="c-input" value="${member.collectTotal }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">followTotal：</label>	
				<input type="text" id="followTotal" name="followTotal" class="c-input" value="${member.followTotal }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">mobile：</label>	
				<input type="text" id="mobile" name="mobile" class="c-input" value="${member.mobile }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">password：</label>	
				<input type="text" id="password" name="password" class="c-input" value="${member.password }" />
			</div>
			<div class="contain-box">
				<label class="c-lable">status：</label>	
				<input type="text" id="status" name="status" class="c-input" value="${member.status }" />
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
					url: __ctxPath+"/member/save",
					data: param,
					dataType:"json",
					success: function(result){
						if(result.success){
							$("#id").val(result.member.id);
							$("#version").val(result.member.version);
							layer.msg("保存成功", {icon: 6, time:2000}, function(){
								window.location = __ctxPath+"/member/index";
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