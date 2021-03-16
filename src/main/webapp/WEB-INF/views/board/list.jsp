<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체조회</title>
<!-- 핸들바 탬플릿 cdn추가 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
 <!-- 탬플릿 소스 -->
 <script id="template_source" type="text/x-handlebars-template">
        <table border="1">
            <tr>
                <th>번호</th>
                <th>작성자id</th>
                <th>제목</th>
                <th>조회수</th>
                <th>좋아요</th>
                <th>싫어요</th>
				<th>등록일자</th>
            </tr>
            {{#each.}}
                <tr>
                    <td>{{bnum}}</td>
                    <td>{{userid}}</td>
                    <td><a href='${path}/board/detail/{{bnum}}'>{{subject}}</a></td>
                    <td>{{readcount}}</td>
                    <td>{{likecnt}}</td>
                    <td>{{dislikecnt}}</td>
                    <td>{{regdate}}</td>
                </tr>
            {{/each}}
        </table>
</script>

<script type="text/javascript">
	$(function() {
		//조회,하이퍼링크 클릭했을때 처리할 함수
		function pageList(curPage) { //현재페이지 매개변수로
			const findKey = frmList.findKey.value;
			const findValue = frmList.findValue.value;
			console.log(findKey);
			console.log(findValue);
			
			//ajax비동기방식 
			$.ajax({
				type:'get',
				url:'${path}/board/list',
				data:{findKey:findKey,findValue:findValue,curPage:curPage},
				dataType:'json', //돌려받는 데이터 형태
				success: function(result) {
					//alert('success');
					console.log(result);
					//탬플릿을 이용하여 화면에 출력
					var source = $('#template_source').html();
		            var template = Handlebars.compile(source);
		            $('#boardList').html(template(result.blist));		
		            
		            const pdto = result.pdto; //페이징 정보
		            //페이징 처리
		            $('#paging').html(''); //초기화
		            
		            if (pdto.startPage != 1){
		            	$('#paging').append('<a class="aPage" href="'+(pdto.startPage-1)+'">prev</a> ');
		            }
		            for(var i=pdto.startPage; i<pdto.endPage+1; i++){
		            	$('#paging').append('<a class="aPage" href="'+i+'">' + i+'</a> ');
		            }
		            if (pdto.endPage < pdto.totPage){
		            	$('#paging').append('<a class="aPage" href="'+(pdto.endPage+1)+'">next</a> ');
		            }
		            	
				},
				error: function(result) {
					alert('error');
					console.log(result);
					
				}
				
			});			
		}
		
		//조회버튼
		$('#btnList').click(function(e) {
			e.preventDefault();
			pageList(1) ;
		});
		
		//페이지를 클릭했을때
		$('#paging').on('click','.aPage', function(e) {
			e.preventDefault();
			const pageNo= $(this).attr('href');
			//alert(pageNo);
			pageList(pageNo) ;
		});
		
		//게시글 추가 폼으로 이동
		$('#btnAdd').click(function(e) {
			e.preventDefault();
			//$(location).attr('href','${path}/board/add' );
			location.href='${path}/board/add';
		} );
		
		//$('#btnList').trigger('click');	//강제로 click이벤트 발생
		
		pageList() ; //화면에 리스트+페이징 출력
	});

</script>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="container">
	<h2>게시물조회</h2>
	<form name="frmList" action="">
		<select name="findKey">
			<option value="userid">아이디</option>
			<option value="subject">제목</option>
			<option value="content">내용</option>
			<option value="subcon">제목+내용</option>
		</select>
		<input type="text" name="findValue"> 
		<button id="btnList">조회</button>
		<button id="btnAdd">게시글추가</button>
	</form>
	<div id="boardList"></div>
	<div id="paging"></div>
</div>
</body>
</html>