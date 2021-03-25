<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/include.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찾아오시는길</title>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=ndys79m540"></script>
<script>
	$(function() {
		//회사의 위도 경도 알기
		const x = 126.9301180;
		const y= 37.4847794;
		//맵의 옵션
		var mapOptions = {
		    center: new naver.maps.LatLng(y, x),
		    zoom: 16
		};
		
		//맵 생성
		var map = new naver.maps.Map('map', mapOptions);
		
		//마커 생성하기
		var marker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(y, x),
		    map: map
		});
		
	});
	
</script>
</head>
<body>
<%@include file="menu.jsp" %>
<div class="container">
	<h2>학원위치</h2>
	<div id="map" style="width:100%;height:400px;"></div>


	
</div>

</body>
</html>