<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctxPath }/js/plugn/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctxPath }/js/plugn/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctxPath }/js/plugn/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl">账号管理 &gt; </span><span
			class="child-name fl">角色管理</span>
	</div>
</div>

<div class="panel" style="width: 230px; float: left;">
	<div class="panel-title">
		<h3>菜单树</h3>
		<span class="up-down-btn up-icon" title="收起"></span>
	</div>
	<div class="panel-content">
		<div style="margin: 5px;">
			<span style="vertical-align: -3px;" class="click-btn" onclick="addNode();"><i class="add-icon">添加</i></span>
			<span style="vertical-align: -3px;" class="click-btn" onclick="deleteNode();"><i class="delete-icon">删除</i></span>
		</div>
		<ul id="roleTree" class="ztree" style="width: 220px;"></ul>
	</div>	
</div>
<div class="panel" style="width: 700px; float: left;">
	<div class="panel-title">
		<h3 id="editTitle">基本信息</h3>
		<span class="up-down-btn up-icon" title="收起"></span>
	</div>
	<div class="panel-content" id="roleFormDiv" style="height: 126px; margin-bottom: 5px;">
		<!-- <div align="center" style="width:100%; height: 100%; padding-top: 50px;"><h2 style="font-size: 20px;">菜单根目录</h2></div> -->
		<%@ include file="roleForm.jsp" %>
	</div>
	<!-- <div class="panel-footer">
		<span class="save-btn bor-radius" id="saveBtn">保存</span>
	</div> -->
</div>
<script type="text/javascript">
	var setting = {
			view: {
				showIcon: false,
				showIcon: false,
				selectedMulti: false
			},
			async: {
				enable: true,
				url: __ctxPath+"/role/getJsonRole",
				autoParam: ["id"],
				dataFilter: filter
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick,
				onAsyncSuccess: zTreeOnAsyncSuccess
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].id = childNodes[i].diy.id;
			if(!parentNode){
				childNodes[i].pId = childNodes[i].diy.pId; 
			}
		}
		return childNodes;
	}
	function beforeClick(treeId, treeNode, clickFlag) {
		if(treeId == '-1') return false;
		return true;
	}
	function onClick(event, treeId, treeNode, clickFlag) {
		$("#editTitle").html("基本信息");
		if(treeNode.id == -1){
			addNode();
			$("#parentFull").html(treeNode.name);//设置父菜单提示
		} else {
			$("#roleFormDiv").loading().load(__ctxPath+"/role/edit/"+treeNode.id, {"decorator": "blank"});
		}
	}	
	
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		var treeObj = $.fn.zTree.getZTreeObj("roleTree");
		var id = $("#id").val();
		var node = treeObj.getNodeByParam("id", id == "" ? -1:id, null);  
        if(node !=null){
            treeObj.selectNode(node, true);  
			if(id == ""){
				$("#parentFull").html(node.name);
			}
        } 
		//return (treeNode.id !== -1);
	};

	$(document).ready(function(){
		$.fn.zTree.init($("#roleTree"), setting);
		$(document).on("click","#saveBtn",function(){
			$("#roleForm :input").trigger("blur");
			if(!$(".validError").is(":visible")){
				var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				var menus="";
				var nodes = treeObj.getCheckedNodes(true);
				if(nodes.length > 0 ){
					for(var i = 0 ; i < nodes.length; i++){
						if(isNotBlank(nodes[i].pId)){
							menus += nodes[i].pId+",";
						}
						menus += nodes[i].id+",";
					}
				}
				$("#menus").val(menus);
				var param = $("#roleForm").serializeArray();
				$.ajax({
					type:"POST",
					url: __ctxPath+"/role/save",
					data: param,
					dataType:"json",
					success: function(result){
						if(result.success){
							$("#id").val(result.role.id);
							$("#version").val(result.role.version);
							layer.msg("保存成功", {icon: 6, time:2000}, function(){
								$.fn.zTree.init($("#roleTree"), setting);
							});
						} else {
							layer.msg("发生错误，操作失败！", {icon: 3});
						}
					}
				});
			}
		});
	});
	
	function addNode(){
		$.fn.zTree.init($("#menuTree"), menuSetting);
		
		var treeObj = $.fn.zTree.getZTreeObj("roleTree");
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length == 0 || nodes.length > 1){
			sysMsg("请选择一个父菜单！", true);
		} else {
			$("#roleForm input").val("");
			if(nodes[0].id == -1){
				$("#parentFull").html(nodes[0].name);
			} else {
				$("#parentFull").append("》"+nodes[0].name);//设置父菜单提示
				$("#parentId").val(nodes[0].id);//设置父菜单
			}
			$("#editTitle").html("新增菜单");
		}
	}
	
	function deleteNode(){
		var treeObj = $.fn.zTree.getZTreeObj("roleTree");
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length == 0 || nodes.length > 1){
			sysMsg("请选择一个菜单！", true);
		} else {
			if(nodes[0].isParent){
				sysMsg("此菜单下还有子菜单，不能删除", true);
			} else {
				var param = {"id": nodes[0].id};
				layer.confirm('删除后相应角色的管理员也会删除，确定要删除吗?', {icon: 3, title:'提示'}, 
						function(index){
							$.ajax({
								type:"POST",
								url: __ctxPath+"/role/delete/"+nodes[0].id,
								data: param,
								dataType:"json",
								success: function(result){
									if(result.success){
										addNode();
										layer.msg("删除成功!", {icon: 6});
										$.fn.zTree.init($("#roleTree"), setting);
									} else {
										layer.msg("发生错误，操作失败！", {icon: 3});
									}
								},
								error: function(xhr, status, error){
									layer.msg("通讯发生错误，请重新尝试或刷新!", {icon: 3});
								}
							});;
						}
					);
			}
		}
	}
</script>