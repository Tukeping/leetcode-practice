package com.tukeping.cs.concurrency.practice;

/**
 * @author tukeping
 * @date 2020/2/6
 **/
public class SynchronizedInteger {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
