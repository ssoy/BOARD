<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/include.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세조회</title>
<!-- 핸들바 탬플릿 cdn추가 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
 <!-- 탬플릿 소스 -->
 <script id="template_source" type="text/x-handlebars-template">
    {{#each.}}
		<div>
			<input type="text" class="restep" value="{{restep}}">
			<input type="text" class="relevel" value="{{relevel}}">
			<input type="text" class="rnum" value="{{rnum}}">
			<input type="text" class="userid" value="{{userid}}">
			<br>
			<textarea rows="3" cols="20" class="content">{{content}}</textarea><br>
			<button class="btnReplyUpdate">수정</button>
			<button class="bthReplyDelete">삭제</button>
			<button class="bthReply">댓글</button>
		</div>
    {{/each}}
</script>
<script type="text/javascript">
	$(function() {
		//좋아요 버튼
		$('#btnLike').click(function() {
			const bnum = $('#bnum').val();
			//alert(bnum);
			
			$.ajax({
				type:'get',
				url:'${path}/board/likeCnt/'+bnum,
				dataType:'json',
				success: function(result) {
					//alert(result);
					$('#likecnt').html(result.likecnt);
					$('#dislikecnt').html(result.dislikecnt);
					
				},
				error: function(result) {
					alert('error');
				}
				
			});
		});
		//싫어요 버튼
		$('#btnDisLike').click(function() {
			const bnum = $('#bnum').val();
			//alert(bnum);
			
			$.ajax({
				type:'get',
				url:'${path}/board/dislikeCnt/'+bnum,
				dataType:'json',
				success: function(result) {
					//alert(result);
					$('#likecnt').html(result.likecnt);
					$('#dislikecnt').html(result.dislikecnt);
					
				},
				error: function(result) {
					alert('error');
				}
				
			});
		});
		
		//수정버튼을 눌럿을때
		$('#btnModify').click(function() {
			const bnum = $('#bnum').val();
			//alert(bnum);
			//userid체크
			const session_userid = '${sessionScope.userid}';
			const userid = $('#userid').val();
			//alert(userid);
			if (userid != session_userid){
				alert('수정권한이 없습니다');
				return ;				
			}
			
			location.href="${path}/board/modify/"+bnum;	
		});
		
		/* -------------------댓글처리------------------------- */
		
		//댓글추가 버튼을 눌렀을때
		$('#btnReplyAdd').click(function() {
			const bnum = $('#bnum').val();
			const replycontent = $('#replycontent').val(); 
			const restep = $('#restep').val();
			const relevel = $('#relevel').val();
			//alert(restep);
			if (replycontent==''){
				alert('댓글 내용을 입력해주세요');
				$('#replycontent').focus();
				return;
			}
			
			$.ajax({
				type:'post',
				contentType: "application/json",
				url: '${path}/reply/',
				data : JSON.stringify({bnum:bnum,content:replycontent,restep:restep,relevel:relevel}),//json문자열
				dataType: 'text',
				success: function(result) {
					//alert(result);
					replyList() ; //댓글 리스트 
				},
				error: function(result) {
					alert('error');
				}
				
			});
			
		});
		
		//원본의 댓글 버튼을 눌렀을때
		$('#bthReply').on('click',function() {
			//alert(restep);
			//alert(relevel);
			$('#restep').val('0');
			$('#relevel').val('0');
			
		});

		//댓글의 댓글 버튼을 눌렀을때
		$('#replyList').on('click', '.bthReply', function() {
			const restep = $(this).parent().find('.restep').val();
			const relevel = $(this).parent().find('.relevel').val();
			//alert(restep);
			//alert(relevel);
			$('#restep').val(restep);
			$('#relevel').val(relevel);
			
		});
		
		//댓글의 수정 버튼을 눌렀을때
		$('#replyList').on('click', '.btnReplyUpdate', function() {
			const rnum = $(this).parent().find('.rnum').val();
			const userid = $(this).parent().find('.userid').val();
			const content = $(this).parent().find('.content').val();
			console.log(rnum);
			console.log(userid);
			console.log(content);
			
			//userid체크
			const session_userid = '${sessionScope.userid}';
			if (userid != session_userid){
				alert('수정권한이 없습니다');
				return ;				
			}
			
			$.ajax({
				type:'put', //수정
				contentType: "application/json",
				url: '${path}/reply/',
				data : JSON.stringify({rnum:rnum,userid:userid,content:content}),//json문자열
				dataType: 'text',
				success: function(result) {
					alert(result);
					
				},
				error: function(result) {
					alert('error');
				}
				
			});
		});
		
		//댓글의 삭제 버튼을 눌렀을때
		$('#replyList').on('click', '.bthReplyDelete', function() {
			const rnum = $(this).parent().find('.rnum').val();
			const userid = $(this).parent().find('.userid').val();
			console.log(rnum);
			console.log(userid);
			
			//userid체크
			const session_userid = '${sessionScope.userid}';
			if (userid != session_userid){
				alert('삭제권한이 없습니다');
				return ;				
			}
			
			$.ajax({
				type:'delete', //삭제
				url: '${path}/reply/'+rnum,
				dataType: 'text',
				success: function(result) {
					alert(result);
					replyList() ; //댓글 리스트 
				},
				error: function(result) {
					alert('error');
				}
			});
		});
		
		//댓글 리스트 조회
		function replyList() {
			const bnum = $('#bnum').val();
			//alert(bnum);
			$.ajax({
				type:'get',
				url: '${path}/reply/'+bnum,
				dataType: 'json',
				success: function(result) {
					//alert(result);
					console.log(result);
					//탬플릿을 이용하여 화면에 출력
					var source = $('#template_source').html();
		            var template = Handlebars.compile(source);
		            $('#replyList').html(template(result));	
					
				},
				error: function(result) {
					alert('error');
				}
				
			});
		}		
		
		replyList() ; //댓글 리스트 
		
	});


</script>
</head>
<body>

<%@include file="../menu.jsp" %>
<div class="container">
	<%-- 	${resultMap.bdto}
			${resultMap.bflist}--%>
	<h2>상세조회</h2>
	번호 : <input type="text" id="bnum" value="${resultMap.bdto.bnum}" readonly="readonly"> <br>
	아이디 : <input type="text" id="userid" value="${resultMap.bdto.userid}" readonly="readonly"> <br>
	제목 : ${resultMap.bdto.subject} <br>
	내용 : <textarea rows="5" cols="20" readonly>${resultMap.bdto.content}</textarea><br>
	<hr>
	파일 : <br>
	<c:forEach var="file" items="${resultMap.bflist}">
		${file.filename} <br>
	</c:forEach>
	
	<hr>	
	조회수 :  ${resultMap.bdto.readcount} 
	<button id="btnLike">좋아요</button><span id="likecnt">${resultMap.bdto.likecnt}</span>
	<button id="btnDisLike">싫어요</button><span id="dislikecnt">${resultMap.bdto.dislikecnt}</span>
	<hr>	
	등록일자 :  ${resultMap.bdto.regdate} <br>
	수정일자 :  ${resultMap.bdto.modifydate} <br>
	<hr>	
	<button id="btnModify">수정</button>
	<button id="bthReply">댓글</button>
	
	<!-- 댓글 -->
	<hr>
	<input type="text" id="restep" value="0">
	<input type="text" id="relevel" value="0"><br>
	<textarea rows="5" cols="20" id="replycontent"></textarea><br>
	<button id="btnReplyAdd">추가</button>
	<hr>
	<!-- 댓글의 리스트 -->
	<div id="replyList"></div>
	
	
	
	
	
	
	
	
	
</div>
</body>
</html>