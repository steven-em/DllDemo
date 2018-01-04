<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl">广告管理&gt; </span>
		<span class="child-name fl">广告位列表</span>
	</div>
</div>
<div class="panel-content" style="margin-left: 5px;">
	<div class="search-name-box">
		<form action="${pageContext.request.contextPath }/admin/adPosition/search.do" method="post" id="searchForm">
			<input type="hidden" name="pageNum" id="pageNum" value="${pageNum }">
			<input type="hidden" name="pageSize" id="pageSize">
			<div class="contain-box">
				<label class="c-lable">广告位：</label> 
				<input type="search" name="title" id="keyword" class=" c-input" value="${keyword }"/>
				<div class="click-btn" style="vertical-align: -2px; margin-left: 100px;">
					<i class="search-icon" onclick="search();">搜索</i>
				</div>
				<div class="click-btn" style="vertical-align: -2px;">
					<a href="${ctxPath }/adPosition/add"><i class="add-icon">添加</i></a>
				</div>
			</div>
		</form>
	</div>
	<div class="contain-box">
		<div class="table-div" id="searchListDiv"></div>
	</div>	
</div>

<script type="text/javascript">
	$(function(){
		search();
		// 按回车触发搜索
		$("#keyword").keydown(function(event){
			if(event.keyCode==13){    
	        	search();  
	        	return false;
	         }
		});
	});
	
	function search(){
		var param = $("#searchForm").serializeArray();
		$("#searchListDiv").loading().load(__ctxPath+"/adPosition/search", param);
	}
</script>

