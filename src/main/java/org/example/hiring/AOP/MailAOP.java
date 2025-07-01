package org.example.hiring.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MailAOP {
    @After("execution(* org.example.hiring.service.*.*(..))")
    public void AfterMail(JoinPoint joinPoint){
        System.out.println("the mail is forwared "+joinPoint.getSignature().getName());
    }
}
