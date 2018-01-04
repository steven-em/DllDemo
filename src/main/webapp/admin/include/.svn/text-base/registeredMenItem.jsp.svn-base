
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div style="overflow: auto;">
	<table class="items">
		<thead>
			<tr>
				<th width="40px;"><input type="checkbox" id="checkAll" onclick="javascript:$('input[name=multiIds]').prop('checked',this.checked);"/></th>
				<th>所属账号</th>
				<th>图片一</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty page.content }">
				<tr><td colspan="7">暂无记录</td></tr>
			</c:if>
			<c:forEach items="${page.content }" var="registeredMen" varStatus="vs">
				<tr class="${vs.count%2==0?'even':'old'}">
					<td><input type="checkbox" name="multiIds" value="${registeredMen.id }" title=""/></td>
					<td>${registeredMen.apiAccount.appId }</td>
					<td>
					  <img alt="" src="data:image/png;base64,${registeredMen.image1 }" width="80px;" height="80px;">
					</td>
					<%-- <td class="button-column">
						<a href="${pageContext.request.contextPath }/admin/registeredMen/edit/${registeredMen.id }">
							<i class="edit-icon" title="编辑">编辑</i>
						</a>
					    <i class="delete-icon" title="删除" onclick="fnDoDelete('registeredMen', ${registeredMen.id })">删除</i>
					</td> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<c:if test="${not empty page.content }">
<%@ include file="/admin/common/pagingToScript.jsp"%>
</c:if>