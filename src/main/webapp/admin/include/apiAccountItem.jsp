
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div style="overflow: auto;">
	<table class="items">
		<thead>
			<tr>
				<th width="40px;"><input type="checkbox" id="checkAll" onclick="javascript:$('input[name=multiIds]').prop('checked',this.checked);"/></th>
				<th>账号</th>
				<th>秘钥</th>
				<th>状态</th>
				<th width="120px;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty page.content }">
				<tr><td colspan="5">暂无记录</td></tr>
			</c:if>
			<c:forEach items="${page.content }" var="apiAccount" varStatus="vs">
				<tr class="${vs.count%2==0?'even':'old'}">
					<td><input type="checkbox" name="multiIds" value="${apiAccount.id }" title=""/></td>
					<td>${apiAccount.appId }</td>
					<td>${apiAccount.appkey }</td>
					<td>${apiAccount.status==0 ? '停用' : '启用' }</td>
					<td class="button-column">
						<a href="${pageContext.request.contextPath }/admin/apiAccount/edit/${apiAccount.id }">
							<i class="edit-icon" title="编辑">编辑</i>
						</a>
					    <i class="delete-icon" title="删除" onclick="fnDoDelete('apiAccount', ${apiAccount.id })">删除</i>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<c:if test="${not empty page.content }">
<%@ include file="/admin/common/pagingToScript.jsp"%>
</c:if>