<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>
<%
	String[] nav = new String[]{"设备购买","APP下载链接","客服热线电话","文本管理","加入我们"};
	request.setAttribute("notifNav", nav);
%>
<link rel="stylesheet" href="${ctxPath }/css/tab/chrome-tabs.css">
<link rel="stylesheet" href="${ctxPath }/css/tab/chrome-tabs-dark-theme.css">
<div class="right-nav">
	<div class="right-nav-list fl">
		<span class="parent-name1 fl">系统管理&gt; </span>
		<span class="child-name fl">系统管理配置</span>
	</div>
</div>
<!-- <style type="text/css">
.tag li{list-style:none;padding:6px;border-top:1px solid #d0d0d0;border-right:1px solid #d0d0d0;background:#f2f2f2;display:inline-block;border-left:1px solid #d0d0d0;cursor:pointer}
.tag li a{display:block}
.tag li.cur{position:relative;background:#fff}
.tag li.cur .line{position:absolute;width:100%;height:1px;background:#fff;left:0;bottom:-1px}
</style> -->
<style>
.cssmasks .chrome-tabs .chrome-tab{
 width: 100px !important;
}
</style>
<div class="panel-content" style="margin-left: 5px;">
	<div class="search-name-box" style="padding-bottom: 0px;" >
		<!-- <ul class="tag">
		  <li class="cur" data-tabId = "1">基本配置<div class="line"></div></li>
		  <li data-tabId = "2" >网站编辑<div class="line"></div></li>
		</ul> -->
		<div class="chrome-tabs-shell" style="padding: 0 3px;">
	        <div class="chrome-tabs">
	        	<c:forEach items="${notifNav }" var="nav" varStatus="vs">
		            <div class="chrome-tab ${param['status'] == vs.index ? 'chrome-tab-current': ''} ${(param['status'] == null && vs.index eq 0) ? 'chrome-tab-current': ''}">
		                <div class="chrome-tab-title" style="cursor: pointer;">${nav }</div>
		                <div class="chrome-tab-curves">
		                    <div class="chrome-tab-curves-left-shadow"></div>
		                    <div class="chrome-tab-curves-left-highlight"></div>
		                    <div class="chrome-tab-curves-left"></div>
		                    <div class="chrome-tab-curves-right-shadow"></div>
		                    <div class="chrome-tab-curves-right-highlight"></div>
		                    <div class="chrome-tab-curves-right"></div>
		                </div>
		            </div>
	        	</c:forEach>
	        </div>
	        <!-- <div class="chrome-shell-bottom-bar"></div> -->
	    </div>
	</div>
	<div class="contain-box" style="margin-top: 0px;">
		<div class="table-div" id="searchListDiv"></div>
	</div>	
</div>
<script type="text/javascript" src="${ctxPath }/js/tab/chrome-tabs.js"></script>
<script type="text/javascript">
	 $(function(){
		 load(1);
		 /* $(".search-name-box li").click(function(){
			 $(this).addClass("cur").siblings().removeClass("cur");
			 load($(this).attr("data-tabid"));
		 }); */
	}); 
	function load(tabId){
		$("#searchListDiv").loading().load("${ctxPath}/systemConfig/contentForm",{tabId : tabId},function(){
			
		});
	}
	
	var $chromeTabsExampleShell = $('.chrome-tabs-shell')
    chromeTabs.init({
        $shell: $chromeTabsExampleShell,
        minWidth: 45,
        maxWidth: 170
    });
    $chromeTabsExampleShell.bind('chromeTabRender', function(){
        var $currentTab = $chromeTabsExampleShell.find('.chrome-tab-current');
        if ($currentTab.length && window['console'] && console.log) {
        	var status = $currentTab.index()+1;
        	load(status);
        }
    });
	
</script>

