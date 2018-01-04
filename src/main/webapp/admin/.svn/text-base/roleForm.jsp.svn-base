<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div class="panel" style="float: left; width: 350px;">
	<form action="${ctxPath }/role/save" method="post" id="roleForm">
		<input type="hidden" id="id" name="id" value="${role.id }">
		<input type="hidden" id="version" name="version" value="${role.version }">
		<input type="hidden" id="menus" name="menus" value="${role.menus }">
		<input type="hidden" id="parentId" name="parentId" value="${role.parentId }"/>
		<input type="hidden" id="rolePath" name="rolePath" value="${role.rolePath }"/>
		<div class="contain-box">
			<label class="c-lable">父节点：</label>
			<span id="parentFull">${role.rolePathStr }</span>
		</div>
		<div class="contain-box">
			<label class="c-lable"><span class="red">*</span>角色名：</label>
			<input type="text" id="rolename" name="rolename" class="c-input" value="${role.rolename }" onblur="requiredValid(this);">
		</div>
		<div class="contain-box">
			<label class="c-lable">状态：</label>
			<select id="status" name="status">
				<option value="1" ${role.status ? 'selected="selected"' : ''}>显示</option>
				<option value="0" ${role.status ? '' : 'selected="selected"'}>隐藏</option>
			</select>
		</div>
		<div class="contain-box">
			<label class="c-lable">排序：</label>
			<input type="text" id="sortOrder" name="sortOrder" class="c-input" value="${role.sortOrder }">
		</div>
	</form>
	<div class="panel-content">
		<div class="panel-footer">
			<span class="save-btn bor-radius" id="saveBtn">保存</span>
		</div>
	</div>	
</div>
<div class="panel" style="float: left; width: 300px;">
	<div class="panel-title">
		<h3>菜单</h3>
		<span class="up-down-btn up-icon" title="收起"></span>
	</div>
	<div class="panel-content">
		<ul id="menuTree" class="ztree" style="width: 220px;"></ul>
	</div>	
</div>

<script type="text/javascript">
	var menuSetting = {
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "s", "N": "s" }
			},
			view: {
				showIcon: false,
				showIcon: false,
				selectedMulti: false
			},
			async: {
				enable: true,
				url: __ctxPath+"/menu/getJsonMenu?roleId=${role.id }",
				autoParam: ["id"],
				dataFilter: menuFilter
			},
			callback: {
				beforeClick: menuBeforeClick
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
	

	$(document).ready(function(){
		$.fn.zTree.init($("#menuTree"), menuSetting);
	});
</script>