package com.agniva.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class CRMLoggingAspect {
	
	//setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//setup pointcut declarations
	@Pointcut("execution(* com.agniva.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	//do the same for service and dao
	@Pointcut("execution(* com.agniva.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.agniva.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	
	//add @before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		//display the method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====> in @Before: calling method: "+theMethod);
		
		//display the arguments to the method
		
		//get the arguments
		Object[] args = theJoinPoint.getArgs();
		
		//loop thru the arguments
		for(Object arg : args) {
			myLogger.info("====> argument: "+arg);
		}
		
	}
	
	
	//add @AfterReturning advice
	@AfterReturning(
				pointcut="forAppFlow()",
				returning="theResult"
				)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		//display the method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====> in @AfterReturning: from method: "+theMethod);
		
		//display the data returned
		myLogger.info("====> result: "+theResult);
		
	}

}
