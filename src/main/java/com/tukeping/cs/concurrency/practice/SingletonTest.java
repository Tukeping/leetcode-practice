package com.tukeping.cs.concurrency.practice;

import java.util.Random;

/**
 * @author tukeping
 * @date 2020/6/13
 **/
public class SingletonTest {

    public static void main(String[] args) throws InterruptedException {
        int mxN = 1000;
        Thread[] threads = new Thread[mxN];
        for (int i = 0; i < mxN; i++) {
            threads[i] = new Thread(() -> {
                Singleton singleton = Singleton.getInstance();
                singleton.print();
            });
        }
        Random random = new Random();
        for (int i = 0; i < mxN; i++) {
            Thread.sleep(random.nextInt(100));
            threads[i].start();
        }
        for (int i = 0; i < mxN; i++) {
            threads[i].join();
        }
    }
}
