package com.tukeping.tools.aspect;

import com.tukeping.tools.Prints;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.TimeUnit;

/**
 * @author tukeping
 * @date 2019/12/26
 **/
@Aspect
public class CostAspect {

    @Pointcut("within(@com.tukeping.tools.annotation.Cost *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    @Pointcut("execution(@com.tukeping.tools.annotation.Cost * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    @Around("method()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startNanos = System.nanoTime();
        Object result = joinPoint.proceed();
        long stopNanos = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);
        System.out.println(Prints.prettyPrint(millis));

        return result;
    }
}
