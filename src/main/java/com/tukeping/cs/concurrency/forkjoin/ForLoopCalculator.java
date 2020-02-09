package com.tukeping.cs.concurrency.forkjoin;

/**
 * @author tukeping
 * @date 2020/2/6
 **/
public class ForLoopCalculator implements Calculator {

    @Override
    public long sum(long[] numbers) {
        long sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
