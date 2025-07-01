package org.example.hiring.AOP;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MailAOP {
    @After("execution(* org.example.hiring.service.*.*(..))")
    public void AfterMail(Joinpoint joinpoint){
        System.out.println("the mail is forware "+joinpoint.getSignature().getName());
    }
}
