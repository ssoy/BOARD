<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>에러페이지</h2>
	<p>죄송합니다. 문제가 발생했어요</p>
	<p>잠시후 다시 시도해 주세요</p>
	<h2>예외메시지 : ${msg.getMessage()}</h2>
	
 	<c:forEach var="stack" items="${msg.getStackTrace()}">
		<li>${stack.toString()}</li>
	</c:forEach>
	
</body>
</html>