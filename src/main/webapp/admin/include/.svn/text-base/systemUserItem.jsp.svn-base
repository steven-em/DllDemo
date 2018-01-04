<%@ page pageEncoding="utf-8" %>
<%@ include file="/admin/common/taglibs.jsp"%>
<div style="overflow: auto;">
	<table id="userTable" class="items">
		<thead>
			<tr>
				<th width="40px;"><input type="checkbox" id="checkAll" onclick="javascript:$('input[name=multiIds]').prop('checked',this.checked);"/></th>
				<th>用户名</th>
				<th>昵称</th>
				<th>角色</th>
				<th>头像</th>
				<th>状态</th>
				<th width="135px;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="user" varStatus="vs">
				<tr class="${vs.count%2==0?'even':'old'}">
					<td><input type="checkbox" name="multiIds" value="${user.id }" title="${user.username }"/></td>
					<td><a href="${ctxPath }/user/edit/${user.id }">${user.username }</a></td>
					<td>${user.nickname }</td>
					<td>${user.role.rolename }</td>
					<td><img alt="" src="${imagePath }${user.headurl }" width="40px" height="40px" onerror="this.src='${ctxPath }/images/no_photo.jpg'"></td>
					<td>
						<select onchange="updStatus(this, ${user.id });">
							<option value="1" ${user.status ? 'selected="selected"':'' }>启用</option>
							<option value="0" ${user.status ? '':'selected="selected"' }>禁用</option>
						</select>
					</td>
					<td>
						<i class="password-icon" title="修改密码" onclick="setPwd(${user.id });">修改密码</i>
						<i class="delete-icon" title="删除" onclick="fnDoDelete('user', ${user.id })">删除</i>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="/admin/common/pagingToScript.jsp"%>