package com.tukeping.cs.concurrency.forkjoin;

import java.util.stream.LongStream;

/**
 * @author tukeping
 * @date 2020/2/6
 **/
public class Main {

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 1000).toArray();
        Calculator calculator = new ForkJoinCalculator();
        System.out.println(calculator.sum(numbers));
    }
}
