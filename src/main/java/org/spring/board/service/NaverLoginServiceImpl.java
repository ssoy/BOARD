package org.spring.board.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.board.dao.MemberDAO;
import org.spring.board.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaverLoginServiceImpl implements NaverLoginService{
	private static final Logger logger = LoggerFactory.getLogger(NaverLoginServiceImpl.class);

	@Autowired
	private MemberDAO mdao;
	
	//네이버 인증 apiurl생성
	@Override
	public Map<String, String> getApiUrl() throws Exception {
		//apiurl과 state 리턴
		Map<String, String> resultMap = new HashMap<>(); 
	    String clientId = "bYFKHa2ft1dNpyWsIH7b";//애플리케이션 클라이언트 아이디값";
	    String redirectURI = URLEncoder.encode("http://localhost:8081/board/callback", "UTF-8");
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;		
		
	    resultMap.put("apiURL", apiURL); //네이버인증 url
	    resultMap.put("state", state); //클라이언트 인증 값
		
		return resultMap;
	}

	//네이버 회원 프로필 조회
	@Override
	public String getNaverUserInfo(String code, String state) throws Exception {
		String token = getToken(code, state); // 네이버 로그인 접근 토큰;
		
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        String apiURL = "https://openapi.naver.com/v1/nid/me";


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);
        System.out.println(responseBody);
        
        //회원정보 파싱
        JSONObject object = (JSONObject)new JSONParser().parse(responseBody);
        object = (JSONObject)object.get("response");
        String email = (String)object.get("email");
        logger.info(email);
        
        return email;
		
	}
	//접근토큰발급
	public String getToken(String code, String state) throws Exception{
		// TODO Auto-generated method stub
		String clientId = "bYFKHa2ft1dNpyWsIH7b";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "G9bWnMvDn6";//애플리케이션 클라이언트 시크릿값";
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    System.out.println("apiURL="+apiURL);
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	        logger.info(res.toString());
	        //토큰값 파싱
	        JSONObject object = (JSONObject)new JSONParser().parse(res.toString());
	        access_token = (String)object.get("access_token");
	        refresh_token = (String)object.get("refresh_token");
	        logger.info(access_token);	        
	        logger.info(refresh_token);
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	    
	    return access_token;
	}
	
	
    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

  //네이버 간편가입 로그인
    @Override
	public int loginNaver(String email) throws Exception {
		//1)간편가입 회원 조회
    	MemberDTO mdto = mdao.selectOneNaver(email);
    	//2)만약 회원가입이 안되어 있다면 가입
    	if (mdto==null) {
    		return mdao.insertNaver(email);
    	}
    	
		return 0;
	}
	

}
