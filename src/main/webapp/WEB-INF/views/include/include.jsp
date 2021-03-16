<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!-- 제이쿼리 -->
<script src="${path}/resources/js/jquery-3.5.1.js"></script>
<!-- css -->
<%-- <link rel="stylesheet" type="text/css" href="${path}/resources/css/style.css"> --%>
<link rel="stylesheet" href="${path}/resources/css/bootstrap.css">
<%-- <link rel="stylesheet" href="${path}/resources/css/bootstrap-theme.css"> --%>
<!-- 자바스크립트 -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> -->
<script src="${path}/resources/js/bootstrap.js"></script>
<!-- 사용자 자바스크립트 -->
<%-- <script src="${path}/resources/js/customer.js"></script> --%>
<script>
	if ('${msg}'!=''){
		alert('${msg}');
	}
	
	if ('${param.msg}'!=''){
		alert('${param.msg}');
	}
	
</script> 