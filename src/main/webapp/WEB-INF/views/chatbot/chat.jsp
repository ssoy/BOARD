<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  
    <script>
    	//메시지 보내는 함수
	    var sendmsg = function(){
			var msg = $('#txtMsg').val();
			var date = getToday();
			var appendMsg = '<div class="outgoing_msg"> ' + 
	        				'<div class="sent_msg"> ' +
	        				'<p>' + msg + '</p> ' +
	        				'<span class="time_date"> '+ date +'</span> </div> ' +
	    					'</div> ';    
	    	
			$('#divHistory').append(appendMsg);
			$('#divHistory').scrollTop($('#divHistory').height());
			
			$.ajax({
				type:'post',
				contentType:'application/json',
				url : '${path}/chat/sendMsg',
				data : JSON.stringify({msg:msg}), //json문자열 표기법으로 변환
				dataType : 'text',  //결과값의 타입
				success : function(result){
					console.log(result);
					receiveMsgAppend(result);
					$('#txtMsg').val('');
				},
				error:function(result){
					alert("error");
					console.log(result);
				}
			});
			
			//받은메시지 추가
			function receiveMsgAppend(msg){
				var date = getToday();
				var appendMsg = '<div class="incoming_msg"> ' +
	    	                    '<div class="incoming_msg_img"> <img src="${path}/resources/images/lion.png" width="30" alt="sunil"> </div> ' +
	    	                    '<div class="received_msg"> ' +
	    	                    '<div class="received_withd_msg"> ' +
	    	                    '    <p>' + msg + '</p> ' +
	    	                    '    <span class="time_date"> '+ date +'</span></div> ' +
	    	                    '</div> ' +
	    	                	'</div>';
	    	                
				$('#divHistory').append(appendMsg);
				$('#divHistory').css("overflow-y", "scroll");
				$('#divHistory').scrollTop($('#divHistory').height());    				
			}
			
			//시스템 날짜 구하기
			function getToday(){
				var date = new Date();
				return date.getFullYear() + "-" + 
						("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + (date.getDate() + 1)).slice(-2) +  ' | ' +
						date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
			}
			
		}
    
    	$(function(){
    		//버튼 클릭했을때
    		$('#btnMsg').on('click',sendmsg );
    		//메시지 변경됐을때
    		$('.write_msg').on('change',sendmsg );

    	});
    
    </script>
    
</head>
<body>
<%@include file="../menu.jsp" %>
   <div class="chatcontainer">
        <h3 class=" text-center">Messaging</h3>
        <div class="messaging">
            <div class="mesgs">
                <div id = "divHistory" class="msg_history">
                </div>
                <div class="type_msg">
                <div class="input_msg_write">
                    <input id="txtMsg" type="text" class="write_msg" placeholder="Type a message" />
                    <button id="btnMsg" class="msg_send_btn" type="button"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
                </div>
            	</div>
       		</div>
		</div>
  	</div>  
</body>
</html>
