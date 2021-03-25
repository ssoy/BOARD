<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한건수정</title>
<script type="text/javascript">
	$(function() {
		//파일 추가
		$('#btnFileAdd').on('click', function(e) {
			e.preventDefault(); //기본이벤트 제거
			var appendHtml = '<li><input type="file" name="uploadfiles" >' +
							'<button class="btnFileRemove">삭제</button></li>';
			
			$('#fileGroup').append(appendHtml);
		});
		
		//파일 삭제:동적으로 생성된 버튼의 기능
		$('#fileGroup').on('click', 'button', function(e) {
			e.preventDefault(); //기본이벤트 제거(submit기능제거)
			//this 객체 자신
			$(this).parent().remove();
		});
		
		//수정버튼
		$('#btnModify').click(function(e) {
			e.preventDefault();
			//유효성체크
			const subject = frmModify.subject.value;
			const content = frmModify.content.value;
			if (subject==''){
				alert('제목을 입력해주세요');
				frmModify.subject.focus();
				return;
			}else if (content==''){
				alert('내용을 입력해주세요');
				frmModify.content.focus();
				return;
			}
			
			//form의 속성 변경
 			$('#frmModify').attr('action','${path}/board/modify');
			$('#frmModify').attr('method','post');
			$('#frmModify').attr('enctype','multipart/form-data');
			$('#frmModify').submit();			
			
		});
		//삭제버튼을 눌렀을때
		$('#btnDelete').click(function(e) {
			e.preventDefault();
			const result = confirm('삭제하시겠습니까?');
			if (!result) return;
			
			
			const bnum = $('#bnum').val();
			location.href='${path}/board/delete/'+bnum;
			
		});
		
		//목록버튼을 눌럿을때
		$('#btnList').click(function(e) {
			e.preventDefault();
			location.href="${path}/board/";	
		});
		
	});

</script>
</head>
<body>	
<%@include file="../menu.jsp" %>
<div class="container">
	<h2>게시물수정</h2>
	<form id="frmModify" name="frmModify">
		번호 : <input type="text" name="bnum" id="bnum" value="${resultMap.bdto.bnum}" readonly="readonly"> <br>
		제목 : <input type="text" name="subject" id="subject" value="${resultMap.bdto.subject}"><br>
		내용 : 
		<textarea rows="5" cols="20" name="content" id="content">${resultMap.bdto.content}</textarea> <br>
		<hr>
		파일 : <br>
			<c:forEach var="file" items="${resultMap.bflist}">
				<div>
					<input type="hidden" name="fnum" value="${file.fnum}"> ${file.filename}
					<input type="checkbox" name="fileDelete" value="${file.fnum}">삭제<br>
				</div>
			</c:forEach>
			
			<button id="btnFileAdd">추가</button> 
			 
			<ul id="fileGroup">
				<li><input type="file" name="uploadfiles"><button class="btnFileRemove">삭제</button> </li>	
			</ul>
		<hr>		 
		<button id="btnModify">수정</button>
		<input type="reset" value="취소">
		<button id="btnDelete">삭제</button>
	
		<button id="btnList">목록</button> 
		
	</form>
</div>	
</body>
</html>