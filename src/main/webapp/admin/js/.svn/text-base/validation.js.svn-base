function validError(that, bool, msg){
	if(bool){
		$(that).css("border", "1px dashed #ee770e");
		if($(that).siblings(".validError").length > 0){
    		$(that).siblings(".validError").html(msg).show();
		} else {
			$(that).parent().append("<span class='validError'>"+msg+"</span>");
		}
	} else {
		$(that).css("border", "1px solid #cdcdcd");
		$(that).siblings(".validError").hide();
	}
}
/**
 * 非空验证
 * @param that
 */
function requiredValid(that){
	var value = $(that).val();
	if (that.type=="radio" || that.type=="checkbox") {
		var objs = document.getElementsByName(that.name);
		if (objs && objs.length > 0) {
			var err = true;
			for (var i = 0; i < objs.length; i++) {
				if (objs[i].checked) {
					err = false;
				}
			}
			validError(objs[objs.length-1], err, "不能为空");
		}
	} else {
		validError($(that), (value == null || value.trim().length == 0), "不能为空");
	}
}
/**
 * 输入长度验证
 * @param that
 * @param minLen
 * @param maxLen
 */
function lengthValid(that, minLen, maxLen){
	var value = $(that).val();
	if(value != null && value.length > 0){
		if(minLen != null){
			if(value.length < minLen){
				validError($(that), value.length < minLen, "最少输入【"+minLen+"】个字");
				return;
			}
		} 
		if(maxLen != null){
			validError($(that), value.length > maxLen, "最多输入【"+maxLen+"】个字");
		}
	}
}
/**
 * 数字验证（整型）
 * @param that
 */
function numberValid(that){
	var value = $(that).val();
	if(value != null && value.length > 0){
		validError($(that), !(/^[0-9]+$/.test(value)), "必须是正数");
	}
}
/**
 * 浮点型验证（两位小数）
 * @param that
 */
function double2Valid(that){
	var value = $(that).val();
	if(value != null && value.length > 0){
		validError($(that), !(/^[0-9]+\.{0,1}[0-9]{0,2}$$/.test(value)), "只支持两位小数点");
	}
}
/**
 * 浮点型验证
 * @param that
 */
function doubleValid(that){
	var value = $(that).val();
	if(value != null && value.length > 0){
		validError($(that), !(/^[0-9]+\.{0,1}[0-9]{0,9}$$/.test(value)), "格式不正确");
	}
}
/**
 * 手机号码验证
 * @param that
 */
function telephoneValid(that){
	var value = $(that).val();
	if(value != null && value.length > 0){
		validError($(that), !(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(value)), "格式不正确");
	}
	//numberValid(that);
}
/**
 * 电子邮箱验证
 * @param that
 */
function emailValid(that){
	var value = $(that).val();
	if(value != null && value.length > 0){
		validError($(that), !(/^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/.test(value)), "格式不正确");
	}
}
/**
 * 输入字符统计
 * @param that
 * @param minLength
 * @param maxLength
 */
function charLengthValid(that, minLength, maxLength){
	//var len = getStrLength($(that).val());
	var len = $(that).val().length;
	if(len > maxLength || len < minLength){
		var len = "<span class='red'>"+len+"</span>"
		$(that).siblings(".charLength").html(len+"/"+maxLength+"字");
	}else{
    	$(that).siblings(".charLength").html(len+"/"+maxLength+"字");
	}
}
/**
 * 获取字符长度
 * @param str
 * @returns {Number}
 */
function getStrLength(str) { 
    var len = str.length; 
    var reLen = 0; 
    for (var i = 0; i < len; i++) {        
        if (str.charCodeAt(i) < 27 || str.charCodeAt(i) > 126) { 
            // 全角    
            reLen += 2; 
        } else { 
            reLen++;   
        } 
    } 
    return reLen;    
}

/**
 * 检查手机号码是否存在
 * @param that
 */
function checkTelephone(that){
	var _telephone = $(that).val();
	if(_telephone != '' && _telephone != null){
		$.ajax({
			type:"POST",
			url: __ctxPath+"/member/checkTelephone",
			data: {
				telephone : _telephone
			},
			dataType:"json",
			success: function(result){
				if(result.success){
					validError($(that), true, "手机号码已经存在");
				}
			},
			error: function(xhr, status, error){
				layer.msg("通讯发生错误，请重新尝试或刷新", {icon: 3});
			}
		});
	}
}

function checkUrl(that){
	var _url = $(that).val();
	if(/(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/.test(_url)){
		validError($(that), false, "请输入正确的链接");
	}else{
		validError($(that), true, "请输入正确的链接");
	}
}

/**
 * 固定电话验证
 * @param that
 */
function phoneValid(that){
	var value = $(that).val();
	if(value != null && value.length > 0){
		var isPhone  = /^(?:(?:0\d{2,3})-)?(?:\d{7,8})(-(?:\d{3,}))?$/; 
		validError($(that), !(isPhone .test(value)), "请输入正确的固定电话");
	}
	//numberValid(that);
}
