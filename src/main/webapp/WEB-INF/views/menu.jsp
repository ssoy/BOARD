<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
	$(function() {
		//로그인,로그아웃,회원가입 show 여부
 		if ('${sessionScope.userid}' ==''){
			$('#aLogin').show();
			$('#aJoin').show();
			$('#aLogout').hide();
			$('#imgThumbnail').hide();
		}else{
			$('#aLogin').hide();
			$('#aJoin').hide();
			$('#aLogout').show();		
			$('#imgThumbnail').show();
		}
		

		//메인
		$('#aHome').on('click', function(e) {
			$(this).attr('href', '${path}/main');
		});
		//게시판
		$('#aBoardList').on('click', function(e) {
			$(this).attr('href', '${path}/board/');
		});

		//학원위치
		$('#aInfo').on('click', function(e) {
			$(this).attr('href', '${path}/info');
		});
		

		//로그아웃
		$('#aLogout').on('click', function(e) {
			e.preventDefault(); //객체의 기본기능을 소멸
			var result = confirm('로그아웃 하시겠습니까?');
			if (result){
				$(location).attr('href', '${path}/logout');
			}
		});

		//로그인
		$('#btnLogin').on('click', function(e) {
			//아이디/패스워드 체크
			e.preventDefault(); //객체의 기본기능을 소멸
			$('#loginForm').attr('action','${path}/login');
			$('#loginForm').attr('method','post');
			$('#loginForm').submit();
		});
	

		//로그인 취소
		$('#btnLoginCancel').on('click', function(e) {
			e.preventDefault(); //객체의 기본기능을 소멸
			$('#loginModal').modal('hide');
		});
		
		//챗봇
		$('#aChat').on('click', function(e) {
			e.preventDefault(); //객체의 기본기능을 소멸
			$(location).attr('href', '${path}/chatbot');
		});
		
	});

</script>
<!-- Navbar -->
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="" id="aHome" >메인</a>
      <a class="navbar-brand" href="" id="aBoardList">게시판</a>
      <a class="navbar-brand" href="" id="aInfo">위치</a>
      <a class="navbar-brand" href="" id="aChat" >챗봇</a>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#" data-toggle="modal" data-target="#loginModal" id="aLogin">로그인</a></li>
        <li><a href="${path}/member/add" target="myframe" id="aJoin">회원가입</a></li>
        <li><a href="" id="aLogout">로그아웃</a></li>
        <li><a href="#" id="aMyinfo" target="myframe">${sessionScope.userid}</a></li>
        <li><img id="imgThumbnail" alt="" src="${path}/localimg/${sessionScope.thumbnail}" width="30" ></li>
      </ul>
    </div>
  </div>
    
  <!-- 로그인 Modal -->
  <div class="modal fade" id="loginModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
		<div class="modal-header">
	 		<h4 class="modal-title">로그인</h4>
	    </div>
        <div class="modal-body">	    
			<form id ="loginForm">
			  	<div class="input-group">
			    	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			    	<input id="id" type="text" class="form-control" name="userid" placeholder="id">
			  	</div>
			  	<div class="input-group">
			    	<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			    	<input id="password" type="password" class="form-control" name="passwd" placeholder="Password">
			  	</div>
			</form>
			
		</div>
 		<div class="modal-footer">
 			<a href=""  class="btn btn-primary" id="btnLogin">로그인</a>
 			<a href="" class="btn btn-success" id="btnLoginCancel">취소</a>
		</div>      
 		<div class="modal-footer">
 			<a href="${apiURL}"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
		</div>			

      </div>
    </div>
  </div>
  
</nav>	