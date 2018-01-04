<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="pager">
	<span class="fl">当前第${pagination.pageNum }页</span>
	<span class="fl">共${pagination.totalPage }页</span>
	<span class="fl">共${pagination.totalRecord }条记录</span>
	<c:choose>
		<c:when test="${pagination.totalPage gt 1}">
			<div class="f-page-btn fr">
				<a class='fl ${pagination.pageNum <= 1 ? "disableCss" : ""}' href="javascript:gotoPage(${pagination.pageNum-1})" style="width: 40px;" >←Prev</a>
				<c:set var="minPager" 
					value="${(pagination.totalPage-pagination.pageNum>=2)?(pagination.pageNum >= 3 ? pagination.pageNum - 2 : 1):(pagination.pageNum >= (3 + 2 - pagination.totalPage + pagination.pageNum) ? (pagination.pageNum - 4 + pagination.totalPage - pagination.pageNum) : 1)}" />
				<c:set var="maxPager"
					value="${(minPager + 4 > pagination.totalPage) ? pagination.totalPage : (minPager + 4)}" />
				<c:if test="${minPager >1 }">
					<a class="fl" href="javascript:gotoPage(1})">...</a>
				</c:if>
				<c:forEach var="i" begin="${minPager}" end="${maxPager}" step="1">
					<a class="fl ${pagination.pageNum eq i ? 'cur':'' }" href="javascript:gotoPage(${i})">${i}</a>
				</c:forEach>
				<c:if test="${maxPager < pagination.totalPage }">
					<a class="fl" href="javascript:gotoPage(${pagination.totalPage})">...</a>
				</c:if>
				<a class='fl ${pagination.pageNum >= pagination.totalPage ? "disableCss" : ""}' href="javascript:gotoPage(${pagination.pageNum+1})" style="width: 40px;">Next→ </a>
			</div>
		</c:when>
	</c:choose>
</div>
<div style="display: none;">
	<script type="text/javascript">
	    function gotoPage(toPage) {
	    	if(toPage > "${pagination.totalPage}"){
	    		toPage = "${pagination.totalPage}";
	    	}
	    	if(toPage < 1){
	    		toPage = 1;
	    	}
	        $("#pageNum").val(toPage);
	        $("#searchForm").submit();
	    }
	</script>
</div>