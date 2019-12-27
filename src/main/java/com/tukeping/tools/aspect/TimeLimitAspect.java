package com.tukeping.tools.aspect;

import com.tukeping.tools.Prints;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author tukeping
 * @date 2019/12/27
 **/
@Aspect
public class TimeLimitAspect {

    private final static long MILLIS_LIMIT = 100L;

    @Around("execution(public * *(..)) && @annotation(com.tukeping.tools.annotation.TimeLimit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long cost = (System.currentTimeMillis() - start);
        String timeLimitPrint = Prints.timeLimit(cost, MILLIS_LIMIT);
        System.out.print(Prints.prettyPrint(cost));
        if (null != timeLimitPrint) {
            System.out.println(", " + timeLimitPrint);
        }
        return result;
    }
}
