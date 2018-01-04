<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctxPath }/js/plugn/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctxPath }/js/plugn/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<style>
<!--
.ztree li {width:100%;}
-->
</style>
<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl">系统管理 &gt; </span><span
			class="child-name fl">菜单管理</span>
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
		<ul id="treeDemo" class="ztree" style="width: 220px;"></ul>
	</div>	
</div>
<div class="panel" style="width: 700px; float: left;">
	<div class="panel-title">
		<h3 id="editTitle">基本信息</h3>
		<span class="up-down-btn up-icon" title="收起"></span>
	</div>
	<div class="panel-content" id="menuFormDiv" style="height: 126px; margin-bottom: 5px;">
		<!-- <div align="center" style="width:100%; height: 100%; padding-top: 50px;"><h2 style="font-size: 20px;">菜单根目录</h2></div> -->
		<%@ include file="menuForm.jsp" %>
	</div>
	<div class="panel-footer">
		<span class="save-btn bor-radius" id="saveBtn">保存</span>
	</div>
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
				url: __ctxPath+"/menu/getJsonMenu?pageSize=20",
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
			$("#menuFormDiv").loading().load(__ctxPath+"/menu/edit/"+treeNode.id, {"decorator": "blank"});
		}
	}	
	
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
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

	function addNode(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length == 0 || nodes.length > 1){
			sysMsg("请选择一个父菜单！", true);
		} else {
			$("#menuForm input").val("");
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
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length == 0 || nodes.length > 1){
			sysMsg("请选择一个菜单！", true);
		} else {
			if(nodes[0].isParent){
				sysMsg("此菜单下还有子菜单，不能删除", true);
			} else {
				var param = {"id": nodes[0].id};
				$.ajax({
					type:"POST",
					url: __ctxPath+"/menu/delete/"+nodes[0].id,
					data: param,
					dataType:"json",
					success: function(result){
						if(result.success){
							addNode();
							layer.msg("删除成功!", {icon: 6});
							$.fn.zTree.init($("#treeDemo"), setting);
						} else {
							layer.msg("发生错误，操作失败！", {icon: 3});
						}
					},
					error: function(xhr, status, error){
						layer.msg("通讯发生错误，请重新尝试或刷新!", {icon: 3});
					}
				});
			}
		}
	}
	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting);
		$(document).on("click","#saveBtn",function(){
			$("#menuForm :input").trigger("blur");
			if(!$(".validError").is(":visible")){
				var param = $("#menuForm").serializeArray();
				$.ajax({
					type:"POST",
					url: __ctxPath+"/menu/save",
					data: param,
					dataType:"json",
					success: function(result){
						if(result.success){
							$("#id").val(result.menu.id);
							$("#menuPath").val(result.menu.menuPath);
							$("#version").val(result.menu.version);
							layer.msg("保存成功!", {icon: 6});
							//sysMsg("保存成功");
							$.fn.zTree.init($("#treeDemo"), setting);
						} else {
							sysMsg("发生错误，操作失败！", true);
						}
					}
				}); 
			}
		});
	});
</script>
