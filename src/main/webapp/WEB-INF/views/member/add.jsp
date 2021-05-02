<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript">
	//주소찾기 버튼 클릭시실행할 함수
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("${path}/member/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		
	}
	//주소 팝업에서 실행할 함수
	//roadAddrPart1, addrDetail, zipNo
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			document.frmAdd.addr1.value = roadAddrPart1;
			document.frmAdd.addr2.value = addrDetail;
			document.frmAdd.zip.value = zipNo;
	}

	$(function() {
		//아이디 중복 체크
		$('#btnIdCheck').click(function() {
			const userid = frmAdd.userid.value;
			console.log(userid);
			if (userid=='') {
				alert('아이디를 입력해 주세요!');
				frmAdd.userid.focus();
				return;
			}
			
			$.ajax({
				type:'post',
				url: '${path}/member/idCheck',
				data:{userid:userid},
				dataType:'json',
				success: function(result) {
					alert(result.msg);
					//alert(result.yn);
					if (result.yn=='y'){ 
						$('#idCheckYn').val('y');
					}else{
						$('#idCheckYn').val('n');
					}
				},
				error: function(result) {
					alert('error!');
					console.log(result);
				}
			});
		});
		
		//가입버튼을 클릭했을때
		$('#btnAdd').click(function(e) {
			e.preventDefault(); //기본이벤트 제거
			const userid = frmAdd.userid.value;
			const passwd = frmAdd.passwd.value;
			const email = frmAdd.email.value;
			const idCheckYn = frmAdd.idCheckYn.value; //아이디 중복 체크 여부
			
			console.log(userid);
			console.log(passwd);
			console.log(email);
			
			if (userid==''){
				alert('아이디를 입력해 주세요');
				frmAdd.userid.focus();
			}else if (passwd==''){
				alert('비밀번호를 입력해 주세요');
				frmAdd.passwd.focus();
			}else if (email==''){
				alert('이메일을 입력해 주세요');
				frmAdd.email.focus();
			}else if (idCheckYn!='y'){
				alert('아이디 중복 체크를 해주세요');
				frmAdd.btnIdCheck.focus();
			}else{
				frmAdd.submit();
			}
			
		});
		
		//userid change이벤트
		$('#userid').change(function() {
			$('#idCheckYn').val('n'); //중복체크 해제
		});
		
		//주소찾기 버튼 클릭 이벤트
		$('#btnAddrFind').click(function(e) {
			e.preventDefault(); //기본이벤트 제거
			goPopup();
			
		});
		
		//repasswd change이벤트
		$('#repasswd').change(function() {
			const passwd = $('#passwd').val();
			if (passwd != $(this).val()){
				alert('패스워드가 일치하지 않습니다.');
			}
		});
		//사진이 변경됐을때 사진 보이기 
		$('#imgfile').change(function() {
			if(this.files && this.files[0]) {
			    var reader = new FileReader;
			    
			    reader.onload = function(data) {
			     $("#selectimg img").attr("src", data.target.result).width(100);        
			    }
			    reader.readAsDataURL(this.files[0]);
			}
		});
	});
</script>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="container">
	<h2>회원가입</h2>
	<form name="frmAdd" action="${path}/member/add" method="post" enctype="multipart/form-data" >
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="userid" size="10" id="userid">
					<input type="hidden" id="idCheckYn"> <!-- value가 y이면 체크완료(사용가능) -->  
					<input type="button" value="중복체크" id="btnIdCheck">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="passwd" id="passwd">
				<input type="password" id="repasswd"></td>

			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<th rowspan="3">주소</th>
				<td><input type="text" name="zip" size="5"><button id="btnAddrFind">주소찾기</button> </td>
			</tr>
			<tr>
				<td><input type="text" name="addr1" size="30"></td>
			</tr>
			<tr>
				<td><input type="text" name="addr2" size="30"></td>
			</tr>
			<tr>
				<th>사진</th>
				<td><input type="file" name="imgfile" id="imgfile">
					<div id ="selectimg"> <img src=""></img></div>
				
				 </td>
				
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button id="btnAdd">가입</button>
					<input type="reset" value="취소">
					<button id="btnMain">메인으로</button>
					<%-- <a href="${apiURL}"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a> --%>
				</td>
			</tr>
		</table>
	</form>
</div>
<%@include file="../footer.jsp" %>	
</body>
</html>