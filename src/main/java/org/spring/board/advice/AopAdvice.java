package org.spring.board.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//aop구현
@Component
@Aspect
public class AopAdvice {
	//매개변수 출력
	//리턴형 / 클래스명 / 메소드명 / 매개변수 
	@Before("execution(* org.spring.board.controller.*.*(..))")
	public void startLogService(JoinPoint jp) {
		System.out.println("------------------------------------------------");
		System.out.println(jp.getSignature().toLongString());
		System.out.println("매개변수:" + Arrays.toString(jp.getArgs()));
		System.out.println("------------------------------------------------");
	}
	
	//리턴값 출력
	//적용대상 정상수행후
	@AfterReturning(pointcut="execution(* org.spring.board.service.*.*(..))", returning = "rObj")
	public void afterLogService(JoinPoint jp, Object rObj) {
		if (rObj!=null) {
			System.out.println("------------------------------------------------");
			System.out.println(jp.getSignature().toLongString());
			System.out.println("리턴값:"+rObj.toString());
			System.out.println("------------------------------------------------");
		}
		
	}
}
