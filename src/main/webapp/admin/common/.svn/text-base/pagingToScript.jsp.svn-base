<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="pager">
	<div style="min-width: 33%; float: left;">
		<span class="fl" style="margin: 0 5px;">当前第${page.number+1 }页</span>
		<span class="fl" style="margin: 0 5px;">共${page.totalPages }页</span>
		<span class="fl" style="margin: 0 5px;">共${page.totalElements }条记录</span>
	</div>
	<c:if test="${param['simple'] != true}">
		<div style="width: ${page.totalPages gt 1 ? '33%':'50%'}; float: left;" align="center">
			每页显示
			<select id="pageSizeSelect" name="pageSizeSelect" style="margin: 0 5px;">
				<option value="10" ${param['pageSize'] eq 10 ? 'selected="selected"' : '' }>10</option>
				<option value="30" ${param['pageSize'] eq 30 ? 'selected="selected"' : '' }>30</option>
				<option value="50" ${param['pageSize'] eq 50 ? 'selected="selected"' : '' }>50</option>
				<option value="100" ${param['pageSize'] eq 100 ? 'selected="selected"' : '' }>100</option>
			</select>条
		</div>
	</c:if>
	<c:if test="${page.totalPages gt 1}">
		<div style="width: ${param['simple'] == true ? '50%' : '34%'}; float: right;">
			<div class="f-page-btn fr">
				<a class='fl ${page.number <= 0 ? "disableCss" : ""}' href="javascript:gotoPage(${page.number})" style="width: 40px;" >←Prev</a>
				<c:set var="minPager" 
					value="${(page.totalPages-page.number>=2)?(page.number >= 3 ? page.number - 1 : 1):(page.number >= (3 + 2 - page.totalPages + page.number) ? (page.number - 4 + page.totalPages - page.number) : 1)}" />
				<c:set var="maxPager"
					value="${(minPager + 4 > page.totalPages) ? page.totalPages : (minPager + 4)}" />
				<c:if test="${minPager >1 }">
					<a class="fl" href="javascript:gotoPage(${minPager-1 })">...</a>
				</c:if>
				<c:forEach var="i" begin="${minPager}" end="${maxPager}" step="1">
					<a class="fl ${page.number eq (i-1) ? 'cur':'' }" href="javascript:gotoPage(${i})">${i}</a>
				</c:forEach>
				<c:if test="${maxPager < page.totalPages }">
					<a class="fl" href="javascript:gotoPage(${maxPager+1})">...</a>
				</c:if>
				<a class='fl ${(page.number+1) >= page.totalPages ? "disableCss" : ""}' href="javascript:gotoPage(${page.number+2})" style="width: 40px;">Next→ </a>
			</div>
		</div>
	</c:if>
</div>
<div style="display: none;">
	<script type="text/javascript">
	    function gotoPage(toPage) {
	    	if(toPage > "${page.totalPages}"){
	    		toPage = "${page.totalPages}";
	    	}
	    	if(toPage < 1){
	    		toPage = 1;
	    	}
	        $("#pageNum").val(toPage);
	        search(toPage);
	    }
	    $(function(){
	    	$("#pageSizeSelect").change(function(){
	    		$("#pageSize").val($(this).val());
	    		$("#pageNum").val(1);
		        search(1);
	    	});
	    });
	</script>
</div>