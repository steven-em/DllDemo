<%@ page pageEncoding="utf-8" %>
<%@ include file="/admin/common/taglibs.jsp"%>
<div style="overflow: auto;">
	<table id="userTable" class="items">
		<thead>
			<tr>
				<!-- <th width="40px;"><input type="checkbox" id="checkAll" onclick="javascript:$('input[name=multiIds]').prop('checked',this.checked);"/></th> -->
				<th>用户名</th>
				<th>昵称</th>
				<th>角色</th>
				<th>头像</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="user" varStatus="vs">
				<tr class="${vs.count%2==0?'even':'old'}" onclick="select_userInfo(this)" data-id="${user.id}" data-name="${user.nickname }">
					<%-- <td><input type="checkbox" name="multiIds" value="${user.id }" title="${user.username }"/></td> --%>
					<td>${user.username }</td>
					<td>${user.nickname }</td>
					<td>${user.role.rolename }</td>
					<td><img alt="" src="${imagePath }${user.headurl }" width="40px" height="40px" onerror="this.src='${ctxPath }/images/no_photo.jpg'"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="/admin/common/pagingToScript.jsp"%>