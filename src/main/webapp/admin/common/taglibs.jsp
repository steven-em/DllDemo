<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" buffer="24kb" pageEncoding="UTF-8"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags/admin" prefix="tag"%>
<script type="text/javascript">
	var __ctxPath = "${pageContext.request.contextPath }/admin";
	var __imagePath = "${pageContext.request.contextPath }";
</script>
<c:set var="ctxPath" value="${pageContext.request.contextPath }/admin" scope="request"/>
<c:set var="imagePath" value="${pageContext.request.contextPath }" scope="request"/>