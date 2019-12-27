package com.tukeping;

import com.tukeping.tools.annotation.Cost;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2019/12/26
 **/
public class CostAspectTest {

    @Cost
    public void method() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("invoke method");
    }

    @Test
    public void test() throws InterruptedException {
        CostAspectTest main = new CostAspectTest();
        main.method();
    }
}
