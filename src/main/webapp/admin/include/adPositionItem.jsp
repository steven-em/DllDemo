
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div style="overflow: auto;">
	<table class="items">
		<thead>
			<tr>
				<th width="40px;"><input type="checkbox" id="checkAll" onclick="javascript:$('input[name=multiIds]').prop('checked',this.checked);"/></th>
				<th>广告位</th>
				<th>位置编码</th>
				<th>状态</th>
				<th>广告位宽</th>
				<th>广告位高</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty page.content }">
				<tr><td colspan="3">暂无记录</td></tr>
			</c:if>
			<c:forEach items="${page.content }" var="adPosition" varStatus="vs">
				<tr class="${vs.count%2==0?'even':'old'}">
					<td><input type="checkbox" name="multiIds" value="${adPosition.id }" title=""/></td>
					<td><a href="${pageContext.request.contextPath }/admin/adPosition/edit/${adPosition.id }">${adPosition.title }</a></td>
					<td>${adPosition.positionCode }</td>
					<td>${adPosition.status eq 1 ? '显示' : '隐藏' }</td>
					<td>${adPosition.width }</td>
					<td>${adPosition.height }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<c:if test="${not empty page.content }">
<%@ include file="/admin/common/pagingToScript.jsp"%>
</c:if>