<%@ page pageEncoding="utf-8" %>
<%@ include file="/admin/common/taglibs.jsp"%>
<div style="overflow: auto;">
	<table id="userTable" class="items" style="min-width: 350px;">
		<thead>
			<tr>
				<!-- <th width="40px;"><input type="checkbox" id="checkAll" onclick="javascript:$('input[name=multiIds]').prop('checked',this.checked);"/></th> -->
				<th>国家</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="address" varStatus="vs">
				<tr class="${vs.count%2==0?'even':'old'}" onclick="select_country(this)" data-id="${address.id}" data-name="${address.name }">
					<%-- <td><input type="checkbox" name="multiIds" value="${user.id }" title="${user.username }"/></td> --%>
					<td>${address.name }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<input type="hidden" id="pageNum" />
<%@ include file="/admin/common/pagingToScript.jsp"%>