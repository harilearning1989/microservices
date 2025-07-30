package com.web.fake.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    //execution(public * com.web.fake.controls..*(..))  //only public methods
    //execution(* com.web.fake.controls.*Controller.*(..)) //only controller methods
    @Before("execution(* com.web.fake.controls..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("logBefore Entry path: {}.{}", className, methodName);
    }

    @After("execution(* com.web.fake.controls..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("logAfter After Complete path: {}.{}", className, methodName);
    }

    @Around("execution(* com.web.fake.controls..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed(); // Proceed with the method execution

        long executionTime = System.currentTimeMillis() - start;

        System.out.println("logExecutionTime::"+joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

    @AfterReturning(
            pointcut = "execution(* com.web.fake.controls..*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String prettyResult;
        try {
            prettyResult = objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            prettyResult = "Unable to serialize result: " + e.getMessage();
        }

        //logger.info("Exited {}.{}() with result:\n{}", className, methodName, prettyResult);
        logger.info("logAfterReturning Exited {}.{}() ", className, methodName);
    }


    // Match any public method starting with "fetch" in your service implementation class
    @Pointcut("execution(public * com.web.fake.services.FakeRestTemplateServiceImpl.fetch*(..))")
    public void fetchMethods() {}

    @Before("fetchMethods()")
    public void beforeFetch(JoinPoint joinPoint) {
        System.out.println("📌 PontCut Before: " + joinPoint.getSignature());
    }

    @After("fetchMethods()")
    public void afterFetch(JoinPoint joinPoint) {
        System.out.println("✅ PointCut After: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "fetchMethods()", returning = "result")
    public void afterReturningFetch(JoinPoint joinPoint, Object result) {
        System.out.println("🎉 PointCut After Returning: " + joinPoint.getSignature());
        if (result instanceof java.util.List) {
            System.out.println("PointCut Returned list size: " + ((java.util.List<?>) result).size());
        }
    }
}
