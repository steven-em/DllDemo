$(function(){
	$(".up-down-btn").click(function(){
		if($(this).hasClass("up-icon")){
			$(this).removeClass("up-icon").addClass("down-icon").attr("title", "展开");
			$(this).parent().next().hide(500);//.fadeOut(1000);
		} else {
			$(this).removeClass("down-icon").addClass("up-icon").attr("title", "收起");
			$(this).parent().next().show(500);//.fadeIn(1000);
		}
	});
	
	$("input:text").keypress(function(e) {  
	    if (e.which == 13) {// 判断所按是否回车键  
	        var inputs = $("body").find(":text"); // 获取表单中的所有输入框  
	        var idx = inputs.index(this); // 获取当前焦点输入框所处的位置  
	        if (idx == inputs.length - 1) {// 判断是否是最后一个输入框  
	            //if (confirm("最后一个输入框已经输入,是否提交?")) // 用户确认  
	            //$("form").submit(); // 提交表单  
	        } else {  
	            inputs[idx + 1].focus(); // 设置焦点  
	            inputs[idx + 1].select(); // 选中文字  
	        }  
	        return false;// 取消默认的提交行为  
	    }  
    });
	
	$("#hideLeft").click(function(){
		if($(this).attr("s") != "1"){
			$(this).attr("s", "1")
			.html('<img src="'+__ctxPath+'/images/right.png" title="收起菜单"/>')
			.css({
				left: "168px"
			})
			.parent().css({
				width: "177px",
				height: "auto"
			});
			var width = 1019;
			$(".main-right").css({
				width: width+"px",
				"border-left": "1px solid #d0d0d0"
			});
		} else {
			$(this).attr("s", "2")
			.html('<img src="'+__ctxPath+'/images/left.png" title="展开菜单"/>')
			.css({
				left: "0px"
			})
			.parent().css({
				width: "0",
				height: "0"
			});
			$(".main-right").css({
				width: "100%",
				"border-left": 0
			});
		}
	});
});

//loading效果
$.fn.extend({
	loading:function(){
		return this.each(function(){//<label style="position: absolute; margin: 7px; height: 36px;">loading...</label>
			$(this).html('<div style="width: 100%;text-align: center;margin: 5px 0;" align="center"><img src="'+__ctxPath+'/images/loading.gif"/></div>');			
		});
	},
	loading2:function(){
		return this.each(function(){//<label style="position: absolute; margin: -2px 5px; height: 16px;">loading...</label>
			$(this).html('<div style="width: 100%;text-align: center;margin: 5px 0;" align="center"><img src="'+__ctxPath+'/images/loading2.gif"/></div>');			
		});
	}
});


//时间格式化
Date.prototype.format = function(format){ 
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
	} 
	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 
	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		} 
	} 
	return format; 
}


/**
 * 删除
 **/
function fnDoDelete(entity, id){
	layer.confirm('确定要删除吗?', {icon: 3, title:'提示'}, 
		function(index){
	    	$.ajax({
				type:"POST",
				url: __ctxPath+"/"+entity+"/delete/"+id,
				dataType:"json",
				success: function(result){
					if(result.success){
						layer.msg("成功删除", {icon: 6});
						search();
					} else {
						layer.msg("所删除记录关联一些其他信息，暂不可删除！", {icon: 2});
					}
				},
				error: function(xhr, status, error){
					layer.msg("通讯发生错误，请重新尝试或刷新", {icon: 2});
				}
			});
		}
	);
}
/**
 * 批量删除
 **/
function fnDoMultiDelete(entity, tableId){
	var param = $("#"+tableId+" input[name=multiIds]").serializeArray();
	if(param.length > 0){
		layer.confirm('确定要删除吗?', {icon: 3, title:'提示'}, 
			function(index){
		    	$.ajax({
					type:"POST",
					url: __ctxPath+"/"+entity+"/multiDelete",
					data: param,
					dataType:"json",
					success: function(result){
						if(result.success){
							layer.msg("成功删除", {icon: 6});
							$("#pageNum").val("");
							search();
						} else {
							layer.msg("所删除记录关联一些其他信息，暂不可删除！", {icon: 2});
						}
					},
					error: function(xhr, status, error){
						layer.msg("通讯发生错误，请重新尝试或刷新", {icon: 2});
					}
				});
			}
		);
	} else {
		layer.msg("请选择要删除数据！", {icon: 3});
	}
}

//判断是否非空的值
function isNotBlank(obj){
	return ($.trim(obj) != "" && typeof(obj) != 'undefined' && obj != null && obj != 'null');
}

// 判断是否为空
function isBlank(obj){
	return (!obj || $.trim(obj) === "" || typeof(obj) == 'undefined' || obj == null || obj == 'null');
}





