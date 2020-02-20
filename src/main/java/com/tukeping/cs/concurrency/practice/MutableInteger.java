package com.tukeping.cs.concurrency.practice;

/**
 * @author tukeping
 * @date 2020/2/6
 **/
@NotThreadSafe
public class MutableInteger {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
