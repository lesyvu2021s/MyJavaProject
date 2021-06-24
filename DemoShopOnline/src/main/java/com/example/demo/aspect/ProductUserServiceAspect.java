package com.example.demo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductUserServiceAspect {

	@Before(value = "execution(* com.example.demo.service.ProductUserService.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("Before method :" +joinPoint.getSignature());
		System.out.println("Creating ProductUser success!!");
	}
	
	@After(value = "execution(* com.example.demo.service.ProductUserService.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		System.out.println("After method : " + joinPoint.getSignature());
		System.out.println("Creating ProductUser success!!");
	}
}
