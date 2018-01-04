<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl">广告管理&gt; </span>
		<span class="child-name fl">广告列表</span>
	</div>
</div>
<div class="panel-content" style="margin-left: 5px;">
	<div class="search-name-box">
		<form action="${pageContext.request.contextPath }/admin/advertisement/search.do" method="post" id="searchForm">
			<input type="hidden" name="pageNum" id="pageNum" value="${pageNum }">
			<input type="hidden" name="pageSize" id="pageSize">
			<div class="contain-box">
				<label class="c-lable">标题：</label> 
				<input type="search" name="title" id="keyword" class=" c-input" value="${keyword }"/>
				<label class="c-lable">位置：</label> 
				<select id="positionId" name="positionId">
				   <option value="">全部</option>
					<c:forEach items="${adPositionList }" var="adver">
						<option value="${adver.id }">${adver.title}</option>
					</c:forEach>
				</select>
				<div class="click-btn" style="vertical-align: -2px; margin-left: 100px;">
					<i class="search-icon" onclick="search();">搜索</i>
				</div>
				<!-- <div class="click-btn" style="vertical-align: -2px;">
					<i class="delete-icon" onclick="fnDoMultiDelete('advertisement', 'searchListDiv');">删除</i>
				</div> -->
				<div class="click-btn" style="vertical-align: -2px;">
					<a href="${pageContext.request.contextPath }/admin/advertisement/add"><i class="add-icon">添加</i></a>
				</div>
			</div>
		</form>
	</div>
	<div class="contain-box">
		<div class="table-div" id="searchListDiv"></div>
	</div>	
</div>
<div class="layer_notice" style="display: none;">
	<!-- <video src="" autoplay="autoplay" id="previewObj" loop="loop" width="100%" height="100%"></video> -->
	<img alt="" src="" id="previewObj" width="100%" height="100%">
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
		$("#searchListDiv").loading().load(__ctxPath+"/advertisement/search", param);
	}
	
	function previewVideo(url) {
		layer.open({
			type : 1,
			shade : false,
			title : false, //不显示标题
			area : [ '600px', '300px' ], //宽高
			content : $('.layer_notice'), //捕获的元素
			btn : false,
			success : function() {
				$("#previewObj").attr("src", __imagePath + url);
			}
		});
	}
	
	function previewImage(url){
		layer.open({
			type : 1,
			shade : false,
			title : false, //不显示标题
			area : '500px', //宽高
			content : $('.layer_notice'), //捕获的元素
			btn : false,
			success : function() {
				$("#previewObj").attr("src", __imagePath + url);
			}
		});
	}
</script>

