<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl">系统管理 &gt; </span><span
			class="child-name fl">用户列表</span>
	</div>
</div>

<div class="panel">
	<div class="panel-title">
		<h3>基本信息</h3>
		<span class="up-down-btn up-icon" title="收起"></span>
	</div>
	<div class="panel-content">
		<form action="${ctxPath }/user/save" method="post" id="userForm">
			<input type="hidden" id="id" name="id" value="${systemUser.id }">
			<input type="hidden" id="version" name="version" value="${systemUser.version }">
			<input type="hidden" id="stores" name="stores">
			<div style="float: left; width: 45%;">
				<div class="contain-box">
					<label class="c-lable"><span class="red">*</span>用户名：</label>
					<input type="text" id="username" name="username" class="c-input" value="${systemUser.username }" onblur="requiredValid(this);">
				</div>
				<c:if test="${empty systemUser.id }">
				<div class="contain-box">
					<label class="c-lable"><span class="red">*</span>密码：</label>
					<input type="text" id="passwordS" name="passwordS" class="c-input" value="" onblur="requiredValid(this);">
					<input type="hidden" id="password" name="password" value="${systemUser.password }"/>
				</div>
				</c:if>
				<div class="contain-box">
					<label class="c-lable"><span class="red">*</span>昵称：</label>
					<input type="text" id="nickname" name="nickname" class="c-input" value="${systemUser.nickname }" onblur="requiredValid(this);">
				</div>
				<div class="contain-box">
					<label class="c-lable"><span class="red">*</span>手机：</label>
					<input type="text" id="mobile" name="mobile" class="c-input" value="${systemUser.mobile }" onblur="requiredValid(this);telephoneValid(this)">
				</div>
				<div class="contain-box">
					<label class="c-lable">激活：</label>
					<input type="checkbox" id="status" name="status" ${systemUser.status ? 'checked="checked"' : '' }  style="width: 16px; height: 16px; cursor: pointer;">
				</div>
				<%-- <div class="contain-box">
					<label class="c-lable">接收短信：</label>
					<input type="checkbox" id="isSmsReceive" name="isSmsReceive" ${systemUser.isSmsReceive ? 'checked="checked"' : '' }  style="width: 16px; height: 16px; cursor: pointer;">
					<i class="info-icon tip-layer" data-text="设置是否接收管辖范围的短信">&nbsp;</i>
				</div> --%>
				<div class="contain-box" style="position: relative;">
					<label class="c-lable"><span class="red">*</span>角色：</label>
					<input type="text" id="roleName"  class="c-input" value="${systemUser.role.rolename }" readonly="readonly" style="cursor: pointer;">
					<input type="hidden" value="${systemUser.role.id}" id="roleId" name="roleId" onblur="requiredValid(this);">
					<div class="panel-content" id="panel-content-tree" style="position: absolute;left: 86px;top:24px;background: #fff;display: none;z-index: 100;border:1px solid #d0d0d0;width:162px;">
		      			<ul id="roleTree" class="ztree"></ul>
	        		</div>	
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
			<!-- <div class="panel" style="float: left; width: 480px; margin-left: 30px;">
				<div class="panel-title">
					<h3>地区列表</h3>
					<span class="up-down-btn up-icon" title="收起"></span>
				</div>
				<div class="panel-content">
					<ul id="storeAreaTree" class="ztree" style="width: 220px;"></ul>
				</div>	
			</div> -->
		</form>
		<div class="panel-footer">
			<span class="save-btn bor-radius" id="saveBtn">保存</span>
		</div>
	</div>	
</div>
<script type="text/javascript" src="${ctxPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxPath}/js/uploadfile.js"></script>
<script type="text/javascript" src="${ctxPath}/js/md5.js"></script>
<link rel="stylesheet" href="${ctxPath }/js/plugn/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctxPath }/js/plugn/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctxPath }/js/plugn/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
var zTree = null;
 
	
var settingRole = {
		view: {
			showIcon: false,
			showIcon: false,
			selectedMulti: false
		},
		async: {
			enable: true,
			url: __ctxPath+"/role/getJsonRole",
			autoParam: ["id"],
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess,
			onClick  : function(event,treeId,treeNode){
				$("#roleName").val(treeNode.name);
				$("#roleId").val(treeNode.id);
				$("#panel-content-tree").hide();
			}
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
	function menuFilter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].id = childNodes[i].diy.id;
			if(!parentNode){
				childNodes[i].pId = childNodes[i].diy.pId; 
			}
		}
		return childNodes;
	}
	function menuBeforeClick(treeId, treeNode, clickFlag) {
		if(treeId == '-1') return false;
		return true;
	}
	
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		var treeObj = $.fn.zTree.getZTreeObj("roleTree");
		var id = $("#roleId").val();
		var node = treeObj.getNodeByParam("id", id);  
        if(node !=null){
            treeObj.selectNode(node, true);  
			if(id == ""){
				$("#parentFull").html(node.name);
			}
        } 
	};

	$(function(){
		$(document).on("click","#saveBtn",function(){
			$(this).attr("id","saveBtn1");
			$("#userForm :input").trigger("blur");
			if(!$(".validError").is(":visible")){
				if("${systemUser.id}" == ""){
					var password = $("#passwordS").val();
					if(password == ""){
						return false;
					} else {
						password=hex_md5(password);
						$("#password").val(password);
					}
				}
				/* var store = [];
				$(zTree.getCheckedNodes()).each(function(index,item){
					if(item.diy.type == 'store'){
						store.push(item.diy.id);
					}
				});
				if(store.length == 0){
					layer.msg("请选择相应的门店！", {icon: 3});
					return false;
				}
				$("#stores").val(store.join(",")); */
				var param = $("#userForm").serializeArray();
				var index = layer.load();
				$.ajax({
					type:"POST",
					url: __ctxPath+"/user/save",
					data: param,
					dataType:"json",
					success: function(result){
						if(result.success){
							$("#id").val(result.systemUser.id);
							$("#version").val(result.systemUser.version);
							layer.msg("保存成功", {icon: 6, time:2000}, function(){
								window.location = __ctxPath+"/user/index";
							});
						} else {
							layer.msg("发生错误，操作失败！", {icon: 3});
						}
						layer.close(index);
					}
				});
				
				
			}
			$("#saveBtn1").attr("id","saveBtn");
		});
		$("#fileBtn").change(function() {
	        uploadfile($('#fileBtn'), $('#headurl'), $('#preview'));
	    });
		
		$("#roleName").click(function(event){
			event.stopPropagation();
			$("#panel-content-tree").show();
		});
		$("body").click(function(){
			$("#panel-content-tree").hide();
		});
		
		$(".tip-layer").mouseover(function() {
			layer.tips($(this).attr("data-text"), $(this), {
				tips : [ 2, '#3595CC' ],
				time : -1
			});
		}).mouseout(function() {
			layer.closeAll();
		});
		
		 $.fn.zTree.init($("#roleTree"), settingRole);
	});
</script>