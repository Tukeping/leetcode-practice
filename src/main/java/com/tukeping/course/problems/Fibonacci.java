package com.tukeping.course.problems;

/**
 * @author tukeping
 * @date 2020/4/25
 **/
public class Fibonacci {

    public int Fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }
}
