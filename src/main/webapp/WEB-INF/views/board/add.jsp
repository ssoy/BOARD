<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물추가</title>
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
		
		//등록버튼을 클릭했을때
		$('#btnAdd').on('click', function(e) {
			e.preventDefault();
			//유효성체크
			const subject = frmAdd.subject.value;
			const content = frmAdd.content.value;
			if (subject==''){
				alert('제목을 입력해주세요');
				frmAdd.subject.focus();
				return;
			}else if (content==''){
				alert('내용을 입력해주세요');
				frmAdd.content.focus();
				return;
			}
			
			//form의 속성 변경
			$('#frmAdd').attr('action','${path}/board/add');
			$('#frmAdd').attr('method','post');
			$('#frmAdd').attr('enctype','multipart/form-data');
			$('#frmAdd').submit();
			
		});
		
		
	});


</script>
</head>
<body>
<%@include file="../menu.jsp" %>
	<h2>게시물추가</h2>
	
	<form id="frmAdd" name="frmAdd" >
		제목 : <input type="text" name="subject" id="subject" ><br>
		내용 : 
		<textarea rows="5" cols="20" name="content" id="content"></textarea> <br>
		파일 : 
			<button id="btnFileAdd">추가</button> 
			 
			
			<ul id="fileGroup">
				<li><input type="file" name="uploadfiles"><button class="btnFileRemove">삭제</button> </li>	
			</ul>
				 
		<button id="btnAdd">등록</button>
		<input type="reset" value="취소">
	
	</form>
	
</body>
</html>