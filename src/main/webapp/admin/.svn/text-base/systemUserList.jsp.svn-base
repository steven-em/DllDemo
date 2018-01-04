<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl">系统管理 &gt; </span><span
			class="child-name fl">用户列表</span>
	</div>
</div>
<div class="panel-content" style="margin-left: 5px;">
	<div class="search-name-box">
		<form action="${ctxPath }/user/search.do" method="post" id="searchForm">
			<input type="hidden" name="pageNum" id="pageNum" value="${pageNum }">
			<input type="hidden" name="pageSize" id="pageSize">
			<div class="contain-box" >
				<label class="c-lable">用户名：</label>
				<input type="text" id="username" name="username" class="c-input" style="width:200px;">
				<!-- <label class="c-lable">角色：</label>
				<select style="width:80px; vertical-align: -2px;" >
					<option>广东</option>
					<option>广西</option>
				</select>
				<label class="c-lable">状态：</label> 
				<input type="checkbox" style="width:16px;" id="status" name="status"><label >启用</label> -->
				<div class="click-btn" style="vertical-align: -2px; margin-left: 100px;">
					<i class="search-icon" onclick="search();">搜索</i>
				</div>
			   <div class="click-btn" style="vertical-align: -2px;">
					<i class="delete-icon" onclick="fnDoMultiDelete('user', 'searchListDiv');">删除</i>
				</div> 
				<div class="click-btn" style="vertical-align: -2px;">
					<a href="${ctxPath}/user/add"><i class="add-icon">添加</i></a>
				</div>
				<!-- <div class="click-btn" style="vertical-align: -2px;">
					<i class="yes-icon" onclick="updStatus(1);">激活</i>
				</div>
				<div class="click-btn" style="vertical-align: -2px;">
					<i class="disable-icon" onclick="updStatus(0);">禁用</i>
				</div> -->
			</div>
		</form>
	</div>
	<div class="contain-box">
		<div class="table-div" id="userListDiv">
			
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctxPath }/js/md5.js"></script>
<script type="text/javascript">
	$(function(){
		search();
	});
	
	function search(){
		var param = $("#searchForm").serializeArray();
		$("#userListDiv").loading().load(__ctxPath+"/user/search", param);
	}
	
	function setPwd(id){
		layer.prompt({
		    title: '请输入新密码',
		    formType: 1 //prompt风格，支持0-2
		}, function(pass){
			pass=hex_md5(pass);
			//layer.msg('演示完毕！您的口令：'+ pass);
			var param = {"id": id, "password": pass};
	    	$.ajax({
				type:"POST",
				url: __ctxPath+"/user/updatePassword",
				data: param,
				dataType:"json",
				success: function(result){
					if(result.success){
						layer.msg("修改成功", {icon: 6});
					} else {
						layer.msg("发生错误，操作失败！", {icon: 2});
					}
				},
				error: function(xhr, status, error){
					layer.msg("通讯发生错误，请重新尝试或刷新", {icon: 2});
				}
			});
			
		});
	}
	
	function updStatus(that, id){
		var status = $(that).val();
		var title = "您是否确定要【"+(status == 1 ?"启用":"禁用") +"】此用户?";
		layer.confirm(title, {
		    btn: ['确定','取消'] //按钮
		}, function(){
			var param = {"multiIds": id};
	    	$.ajax({
				type:"POST",
				url: __ctxPath+"/user/updateStatus/"+status,
				data: param,
				dataType:"json",
				success: function(result){
					if(result.success){
						layer.msg("修改成功", {icon: 6});
						$("#pageNum").val("");
						search();
					} else {
						layer.msg("发生错误，操作失败！", {icon: 2});
					}
				},
				error: function(xhr, status, error){
					layer.msg("通讯发生错误，请重新尝试或刷新", {icon: 2});
				}
			});
		}, function(){
			layer.closeAll();
		});
	}
	
	/* function updStatus(status){
		var param = $("#userListDiv input[name=multiIds]").serializeArray();
		if(param.length > 0){
			layer.confirm('确定要修改吗?', {icon: 3, title:'提示'}, 
				function(index){
			    	$.ajax({
						type:"POST",
						url: __ctxPath+"/user/updateStatus/"+status,
						data: param,
						dataType:"json",
						success: function(result){
							if(result.success){
								layer.msg("修改成功", {icon: 6});
								$("#pageNum").val("");
								search();
							} else {
								layer.msg("发生错误，操作失败！", {icon: 2});
							}
						},
						error: function(xhr, status, error){
							layer.msg("通讯发生错误，请重新尝试或刷新", {icon: 2});
						}
					});
				}
			);
		} else {
			layer.msg("请选择要修改数据！", {icon: 3});
		}
	} */
</script>