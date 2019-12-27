package com.tukeping;

import com.tukeping.tools.annotation.TimeLimit;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2019/12/27
 **/
public class TimeLimitAspectTest {

    @TimeLimit
    public void method() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("invoke method");
    }

    @Test
    public void test() throws InterruptedException {
        TimeLimitAspectTest main = new TimeLimitAspectTest();
        main.method();
    }
}
