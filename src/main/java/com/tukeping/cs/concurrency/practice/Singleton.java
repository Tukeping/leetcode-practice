package com.tukeping.cs.concurrency.practice;

import java.util.Random;

/**
 * @author tukeping
 * @date 2020/6/13
 **/
public class Singleton {
    private static Random random = new Random();
    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void print() {
        System.out.println("Thread:" + Thread.currentThread().getName() + ", Singleton:" + instance);
    }
}
