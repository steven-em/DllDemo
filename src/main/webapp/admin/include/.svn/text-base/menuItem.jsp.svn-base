<%@ page pageEncoding="utf-8" %>
<%@ include file="/admin/common/taglibs.jsp"%>
<div style="overflow: auto;">
	<table class="items">
		<thead>
			<tr>
				<th width="40px;"></th>
				<th>id</th>
				<th>title</th>
				<th>url</th>
				<th>parent</th>
				<th>status</th>
				<th>sort</th>
				<th width="100px;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="menu" varStatus="vs">
				<tr class="${vs.count%2==0?'even':'old'}">
					<td><input type="checkbox" name="multiIds" value="${menu.id }" title="${menu.title }"/></td>
					<td>${menu.id }</td>
					<td>${menu.title }</td>
					<td>${menu.url }</td>
					<td>${menu.parent.title }</td>
					<td>${menu.status }</td>
					<td>${menu.sortOrder }</td>
					<td>
						<i class="edit-icon" title="编辑" onclick="javascript:alert('edit');"></i>
						<i class="delete-icon" title="删除"></i>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="/admin/common/pagingToScript.jsp"%>
