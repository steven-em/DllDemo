function uploadfile (uploadElememt, urlElement, imageElement) {
	try {
		$.ajaxFileUpload({
			url : __ctxPath+'/upd/pic',
			secureuri : false,
			fileElement : uploadElememt,
			dataType : 'json',
			type: 'POST',
			success : function(data) {
				layer.closeAll('loading');
                if(data["checkFlag"]=="yes"){
                    urlElement.val(data["url"]);
                    imageElement.attr('src', __imagePath + data["url"]);
                }else if(data["checkFlag"]=="errorExt"){
                    layer.msg("图片格式不正确，请选择jpg、gif、bmp格式的图片！", {icon: 3});
                }else if(data["checkFlag"]=="errorPoint"){
                    layer.msg("图片文件名中有特殊字符，请去掉特殊字符！", {icon: 3});
                }else if(data["checkFlag"]=="errorSize"){
                    layer.msg("图片太大，请选择30M以内图片！", {icon: 3});
                } else {
                	layer.msg("图片上传错误！", {icon: 3});
                }
			},
			error : function(data, status, e) {
				layer.closeAll('loading');
                layer.msg("图片上传错误！", {icon: 3});
			}
		});
	} catch (e) {
		layer.msg("图片上传错误！", {icon: 3});
    }
}

function uploadVideo(uploadElememt, urlElement, videoElement) {
	try {
		$.ajaxFileUpload({
			url : __ctxPath+'/upd/video',
			secureuri : false,
			fileElement : uploadElememt,
			dataType : 'json',
			type: 'POST',
			success : function(data) {
				layer.closeAll('loading');
                if(data["checkFlag"]=="yes"){
                    urlElement.val(data["url"]);
                } else if(data["checkFlag"]=="errorExt"){
                    layer.msg("apk格式不正确！", {icon: 3});
                } else if(data["checkFlag"]=="existsError"){
                    layer.msg("已经存在此版本的包，不能重复上传！", {icon: 3});
                } else {
                	layer.msg("apk上传错误！", {icon: 3});
                }
			},
			error : function(data, status, e) {
				layer.closeAll('loading');
                layer.msg("apk上传错误！", {icon: 3});
			}
		});
	} catch (e) {
		layer.closeAll('loading');
		layer.msg("apk上传错误！", {icon: 3});
    }
}

function uploadmorefile(uploadElememt, imageElement) {
	try {
		var param = {'type':1};
		$.ajaxFileUpload({
			url : __ctxPath+'/upd/pic',
			secureuri : false,
			fileElement : uploadElememt,
			data : param,
			dataType : 'json',
			type: 'POST',
			success : function(data) {
				layer.closeAll('loading');
                if(data["checkFlag"]=="yes"){
                	var _div = $("<div></div>",{
                		'style' : 'padding-left:3px; margin-bottom: 10px;border: 1px solid #d0d0d0;',
                		'class' : '_showPic',
                		'align' : 'center'
                	});
                	
                	var _chdiv1 = $("<div></div>",{
                		'style' : 'height: 100px; width: 160px; display: table-cell; vertical-align: middle; cursor: pointer;',
                	});
                	var _chdiv2 = $("<div></div>",{
                		'style' : 'margin: 5px;',
                		'class' : 'c-snedimage',
                		'align' : 'center'
                	});
                	var _chdiv3 = $("<div></div>",{
                		'style' : 'border-top: 1px solid #d0d0d0; padding: 5px 0;cursor: pointer;',
                		'class' : '_delBPic',
                		'onclick' : 'delPic(this)'
                	});
                    var _img = $("<img/>",{
                    	'width' : '160px',
                    	'height':'120px'
                    	
                    });
                    var _input = '<input name="image" data-id="'+data["url"]+'" alt="上传图片" class="editPicfileBtn" type="file" style="width: 140px; height: 120px; position: absolute; opacity:0; cursor: pointer;">';
                    _chdiv2.html(_input);
                    _img.attr("src",__imagePath + data["url"]).attr("data-url", data["url"]);
                    _chdiv2.append(_img);
                    _chdiv1.html(_chdiv2);
                    _chdiv3.html("删除");
                    _div.html(_chdiv1);
                    _div.append(_chdiv3);
                    imageElement.prev().before(_div);
                 // 修改配置图片
            		$(".editPicfileBtn").change(function() {
            			uploadEditfile($(this),$(this).next());
            	    });
                }else if(data["checkFlag"]=="errorExt"){
                    layer.msg("图片格式不正确，请选择jpg、gif、bmp格式的图片！", {icon: 3});
                }else if(data["checkFlag"]=="errorPoint"){
                    layer.msg("图片文件名中有特殊字符，请去掉特殊字符！", {icon: 3});
                }else if(data["checkFlag"]=="errorSize"){
                    layer.msg("图片太大，请选择30M以内图片！", {icon: 3});
                } else {
                	layer.msg("图片上传错误！", {icon: 3});
                }
			},
			error : function(data, status, e) {
				layer.closeAll('loading');
                layer.msg("图片上传错误！", {icon: 3});
			}
		});
	} catch (e) {
		console.log(e.message);
		layer.msg("图片上传错误！", {icon: 3});
    }
}

function uploadExcelUserInfoFile(uploadElememt,index,type) {
	try {
		$.ajaxFileUpload({
			url : __ctxPath+"/upd/readExcel",
			secureuri : false,
			data : {
				type : type
			},
			fileElement : uploadElememt,
			dataType : 'json',
			type: 'POST',
			success : function(data) {
				 layer.closeAll('loading');
                if(data["checkFlag"]=="yes"){
                	layer.msg("导入成功！", {icon: 6});
                }else if(data["checkFlag"]=="errorExt"){
                    layer.msg("导入文件格式不正确，请选择xls、xlsx格式的文件！", {icon: 3});
                }else if(data["checkFlag"]=="errorPoint"){
                    layer.msg("导入文件名中有特殊字符，请去掉特殊字符！", {icon: 3});
                }else if(data["checkFlag"]=="errorSize"){
                    layer.msg("导入文件太大，请选择30M以内文件！", {icon: 3});
                } else {
                	layer.msg("导入文件错误！", {icon: 3});
                }
			},
			error : function(data, status, e) {
				layer.closeAll('loading');
                layer.msg("导入文件错误！", {icon: 3});
			}
		});
	} catch (e) {
		layer.closeAll('loading');
		layer.msg("导入文件错误！", {icon: 3});
    }
}