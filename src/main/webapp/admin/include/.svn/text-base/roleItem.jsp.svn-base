<%@ page pageEncoding="utf-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div style="overflow: auto;">
	<table class="items">
		<thead>
			<tr>
				<th width="40px;"><input type="checkbox" id="checkAll" onclick="javascript:$('input[name=multiIds]').prop('checked',this.checked);"/></th>
				<th>角色名</th>
				<!-- <th width="100px;">操作</th> -->
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty page.content }">
				<tr><td colspan="2">暂无记录</td></tr>
			</c:if>
			<c:forEach items="${page.content}" var="role" varStatus="vs">
				<tr class="${vs.count%2==0?'even':'old'}">
					<td><input type="checkbox" name="multiIds" value="${role.id }" title="${role.rolename }"/></td>
					<td><a href="${pageContext.request.contextPath }/admin/role/edit/${role.id }">${role.rolename }</a></td>
					<%-- <td>
						<i class="delete-icon" title="删除" onclick="fnDoDelete('role', ${role.id })"></i>
					</td> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<c:if test="${not empty page.content }">
<%@ include file="/admin/common/pagingToScript.jsp"%>
</c:if>
