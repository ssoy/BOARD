package org.spring.board.service;

import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailSendServiceImpl implements MailSendService{
    @Autowired
    private JavaMailSenderImpl mailSender;

    private int size;
    
    //인증키 생성
    private String getKey(int size) {
        this.size = size;
        return getAuthCode();
    }

    //인증코드 난수 발생
    private String getAuthCode() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }

    //인증메일 보내기
    @Override
    public String sendAuthMail( String email, String userid) {
        //6자리 난수 인증번호 생성
        String authKey = getKey(6);

        //인증메일 보내기
        MimeMessage mail = mailSender.createMimeMessage();
        String mailContent = "<h1>[국제자바반 이메일 인증]</h1><br>"+userid +"<p>님 반갑습니다.아래 링크를 클릭해서 이메일인증을 완료해 주세요</p>"
                            + "<a href='http://localhost:8081/board_mysql/member/signUpConfirm?userid=" 
//                            + "<a href='http://101.101.208.52:8080/board/member/signUpConfirm?userid=" 
                            + userid + "&authKey=" + authKey + "' target='_blenk'>이메일 인증 확인(여기를 눌러주세요)</a>";

        try {
            mail.setSubject("[국제자바반]회원가입 이메일 인증 ", "utf-8");
            mail.setText(mailContent, "utf-8", "html");
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mailSender.send(mail);//메일보내기
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return authKey;
    }
}
