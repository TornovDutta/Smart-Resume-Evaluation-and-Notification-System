package org.example.hiring.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Aspect
@Component
public class MailAOP {
    private static final Logger log=Logger.getLogger(MailAOP.class.getName());
    @After("execution(* org.example.hiring.service.*.*(..))")
    public void AfterMail(JoinPoint joinPoint){
        log.info("After Mail");
    }
}
