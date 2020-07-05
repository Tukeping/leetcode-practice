package com.tukeping.cses;

import java.util.Scanner;

/**
 * @author tukeping
 * @date 2020/6/15
 **/
public class WeirdAlgorithm {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        solve(n);
    }

    private static void solve(long n) {
        if (n == 1) {
            System.out.println(n);
            return;
        }

        System.out.print(n + " ");
        if (n % 2 == 0) {
            solve(n / 2);
        } else {
            solve(n * 3 + 1);
        }
    }
}
