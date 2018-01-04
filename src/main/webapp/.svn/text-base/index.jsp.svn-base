<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<style type="text/css">
*{

 margin: 0px;
 padding: 0px;
}
.btn{
	line-height: 100%;
    border-radius: 3px;
    border: 1px solid #ea5666;
    padding: 0 10px;
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    background: #ff6672;
    padding: 10px;
    margin-left: 20px;
}
</style>

  <object classid="clsid:F01B86FC-F816-4150-9BCB-2568726056E5" 
       width="1" height="1" id="myActiveObj" style="visibility: hidden;">
   </object>
   <div style="overflow: hidden;margin-top: 42px; ">
	   <div style="width: 640px;height: 480px;border: 1px solid #dedede; margin-left: 100px; background: #7f7f7f;float: left;">
	   </div>
	   <div class="cropped" style="float: left;width: 243px;box-shadow:0 0 20px #ccc; height: 282px; margin-left: 10px;margin-top: 10px;">
	      <input type="hidden" name="resultdata" id="resultdata">
	      <img alt="" src="" style="width:243px;height:282px;  " id="resultImg">
	   </div>
   </div>
   <div style="text-align: center;width: 640px; margin-top: 10px;"> 
	   <input name="doTest" type="button" id="detect" class="btn" value="捕获人脸" onClick="doDetect();s" />
	   <input name="doClose" type="button" id="close" class="btn" value="关闭" onClick="doClose();" />
   </div>
<script type="text/javascript" src="${ctxPath}/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
$(function(){  
	 var index = 0; //设置拍照的摄像头
	 myActiveObj.SetCamera(index); 
});

function doDetect()
{    
   var result= myActiveObj.detectWithPosition(100,100);    
   $("#resultdata").val(result);
   $("#resultImg").attr("src","data:image/png;base64,"+result)
}
function doClose()
{
    var result= myActiveObj.doConsole();    
    document.getElementById("resultdiv").innerHTML=result;
    
}
function SetCamera()
{
    var index = 0;    
    myActiveObj.SetCamera(index); 
}
</script>