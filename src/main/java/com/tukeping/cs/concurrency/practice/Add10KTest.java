package com.tukeping.cs.concurrency.practice;

/**
 * @author tukeping
 * @date 2020/6/13
 **/
public class Add10KTest {
    private static long count = 0;

    private void add10K() {
        int idx = 0;
        while (idx < 10000) {
            count++;
            idx++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Add10KTest test = new Add10KTest();

        Thread t1 = new Thread(() -> {
            test.add10K();
        });

        Thread t2 = new Thread(() -> {
            test.add10K();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);
    }
}
