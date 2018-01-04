<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl">账号管理&gt; </span>
		<span class="child-name fl">账号增加</span>
	</div>
</div>
<div class="panel">
	<div class="panel-title">
		<h3>基本信息</h3>
		<span class="up-down-btn up-icon" title="收起"></span>
	</div>
	<div class="panel-content">
		<form action="${pageContext.request.contextPath }/admin/apiAccount/save" method="post" id="submitForm">
			<input type="hidden" id="id" name="id" value="${apiAccount.id}"/> 
			<input type="hidden" id="version" name="version" value="${apiAccount.version }" />
			<div class="contain-box">
				<label class="c-lable"><span style="color: red;">*</span>账号：</label>	
				<input type="text" id="appId" style="width: 250px;" name="appId" class="c-input" value="${apiAccount.appId }" onblur="requiredValid(this);" />
			</div>
			<div class="contain-box">
				<label class="c-lable"><span style="color: red;">*</span>秘钥：</label>	
				<input type="text" style="width: 250px;"  id="appkey" name="appkey" class="c-input" readonly="readonly" value="${apiAccount.appkey }" onblur="requiredValid(this);"/>
				<span class="save-btn bor-radius" style="padding: 4px 10px;margin-left: 3px;margin-top: 5px;" onclick="generatorKey(2);">生成apiKey</span>
			</div>
			<div class="contain-box">
				<label class="c-lable"><span style="color: red;">*</span>状态：</label>	
				<select style="width: 162px;" id="status" name="status" onblur="requiredValid(this);">
				  <option value="1" <c:if test="${empty apiAccount.status || apiAccount.status == 1 }">selected="selected"</c:if>>启用</option>
				  <option value="0" <c:if test="${apiAccount.status == 0 }">selected="selected"</c:if>>停用</option>
				</select>
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
					url: __ctxPath+"/apiAccount/overSave",
					data: param,
					dataType:"json",
					success: function(result){
						if(result.success){
							layer.msg("保存成功", {icon: 6, time:2000}, function(){
								window.location = __ctxPath+"/apiAccount/index";
							});
						} else {
							layer.msg("操作失败："+ result.msg, {icon: 3});
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
	
	function generatorKey(obj){
		var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 32; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23];
        var uuid = s.join("");
		$("#appkey").val(uuid);
	}
</script>