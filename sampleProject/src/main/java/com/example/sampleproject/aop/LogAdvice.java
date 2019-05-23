package com.example.sampleproject.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component //스프링에서 관리하는 bean
@Aspect // 공통적인 업무를 지원하는 bean
public class LogAdvice {
	// private : 외부에서 로그를 가로채지 못하도록 하기 위해
	// static final : 로그 내용이 바뀌지 않으므로
	// 로깅툴을 사용하는 이유 : sysout명령어는 IO리소스를 많이 사용하여 시스템이 느려질 수 있다, 로그를 파일로 저장하여 분석할 필요가 있다.
	//로깅을 위한 변수
	private static final Logger logger
		= LoggerFactory.getLogger(LogAdvice.class);
	
//	@Around(
//		"execution(* com.example.spring02.controller..*Controller.*(..))"
//		+ " or execution(* com.example.spring02.service..*Impl.*(..))"
//		+ " or execution(* com.example.spring02.model..dao.*Impl.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint)
		throws  Throwable{
		long start=System.currentTimeMillis();
		
		Object result = joinPoint.proceed(); //핵심업무 실행
		
		//호출한 클래스 이름
		String type=
			joinPoint.getSignature().getDeclaringTypeName();
		String name="";
		if(type.indexOf("Controller") > -1) {
			name="Controller \t:";
		}else if(type.indexOf("Service") > -1) {
			name = "ServiceImpl \t:";
		}else if(type.indexOf("DAO") > -1) {
			name = "DaoImpl \t:";
		}
		//호출한 클래스, method 정보
		logger.info(name+type+"."+joinPoint.getSignature().getName()+"()");
		//method에 전달되는 매개변수들
		logger.info(Arrays.toString(joinPoint.getArgs()));
		long end=System.currentTimeMillis();
		long time=end-start;
		logger.info("실행시간"+time);
		return result;
	}
}
