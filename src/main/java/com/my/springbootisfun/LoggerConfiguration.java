package com.my.springbootisfun;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Spring AOP
 * ==========
 * - This is to demonstrate the Spring AOP (Aspect-Oriented Programming)
 * - AOP demonstrate separation of concern
 * - This can be show by seperating the logger and business logic
 * - Got few annotation like @Before @After @Around @AfterReturning @AfterThrowing
 *
 * !! To be honest I don't see any practical for this AOP. But this could help in future.
 */
@Aspect
@Component
public class LoggerConfiguration {

    @Before("execution(* com.my.springbootisfun.user.controller.*.* (..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("Demonstration of Spring AOP");
        System.out.println("Before calling " + className + "." + methodName);
    }
}
