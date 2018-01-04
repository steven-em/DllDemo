
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div style="overflow: auto;">
	<table class="items">
		<thead>
			<tr>
				<th width="40px;"><input type="checkbox" id="checkAll" onclick="javascript:$('input[name=multiIds]').prop('checked',this.checked);"/></th>
				<th>标题</th>
				<th>位置</th>
				<!-- <th>图片</th>
				<th>视频地址</th>
				<th>链接地址</th>
				<th>描述</th> -->
				<th>状态</th>
				<th width="120px;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty page.content }">
				<tr><td colspan="3">暂无记录</td></tr>
			</c:if>
			<c:forEach items="${page.content }" var="advertisement" varStatus="vs">
				<tr class="${vs.count%2==0?'even':'old'}">
					<td><input type="checkbox" name="multiIds" value="${advertisement.id }" title=""/></td>
					<td><a href="${pageContext.request.contextPath }/admin/advertisement/edit/${advertisement.id }">${advertisement.title }</a></td>
					<td>${advertisement.adPosition.title}</td>
					<%-- <td>${advertisement.imageUrl }</td>
					<td>${advertisement.videoUrl }</td>
					<td>${advertisement.redirectUrl }</td>
					<td>${advertisement.shortDesc }</td> --%>
					<td>${advertisement.status eq 1 ? '显示' : '隐藏' }</td>
					<td class="button-column">
						<i class="preview-icon" title="预览" onclick="previewImage('${advertisement.imageUrl }');">预览</i>
						<i class="delete-icon" title="删除" onclick="fnDoDelete('advertisement', ${advertisement.id })">删除</i>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<c:if test="${not empty page.content }">
<%@ include file="/admin/common/pagingToScript.jsp"%>
</c:if>