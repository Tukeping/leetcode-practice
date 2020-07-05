package com.tukeping.jdk.java8;

/**
 * @author tukeping
 * @date 2020/6/19
 **/
public class LambdaTest {

    @FunctionalInterface
    interface Func {
        int exec(int x, int y);
    }

    public static void main1(String[] args) {
        Func fn = (x, y) -> x * y;
        System.out.println(fn.exec(3, 8));
    }

    public static void main(String[] args) {
        Func fn = new LambdaTest$$Lambda$1();
        System.out.println(fn.exec(3, 8));
    }

    private static int lambda$main$0(int x, int y) {
        return x + y;
    }

    static final class LambdaTest$$Lambda$1 implements Func {
        private LambdaTest$$Lambda$1() {
        }

        public int exec(int x, int y) {
            return LambdaTest.lambda$main$0(x, y);
        }
    }
}
