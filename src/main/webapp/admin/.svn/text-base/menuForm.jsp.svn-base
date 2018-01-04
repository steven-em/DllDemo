<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<form action="${pageContext.request.contextPath }/menu/save" method="post" id="menuForm">
	<input type="hidden" id="id" name="id" value="${menu.id }"/>
	<input type="hidden" id="parentId" name="parentId" value="${menu.parentId }"/>
	<input type="hidden" id="menuPath" name="menuPath" value="${menu.menuPath }"/>
	<input type="hidden" id="version" name="version" value="${menu.version }"/>
	<div class="contain-box">
		<label class="c-lable">父节点：</label>
		<span id="parentFull">${menu.menuPathStr }</span>
	</div>
	<div class="contain-box">
		<label class="c-lable"><span class="red">*</span>菜单：</label>
		<input type="text" id="title" name="title" class="c-input" value="${menu.title }" onblur="requiredValid(this);">
	</div>
	<div class="contain-box">
		<label class="c-lable">路径：</label>
		<input type="text" id="url" name="url" class="c-input" value="${menu.url }">
	</div>
	<div class="contain-box">
		<label class="c-lable">状态：</label>
		<select id="status" name="status">
			<option value="1" ${menu.status ? 'selected="selected"' : ''}>显示</option>
			<option value="0" ${menu.status ? '' : 'selected="selected"'}>隐藏</option>
		</select>
	</div>
	<div class="contain-box">
		<label class="c-lable">排序：</label>
		<input type="text" id="sortOrder" name="sortOrder" class="c-input" value="${menu.sortOrder }">
	</div>
</form>