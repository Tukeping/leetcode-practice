package com.tukeping.cses;

import java.util.Scanner;

/**
 * @author tukeping
 * @date 2020/6/15
 **/
public class MissingNumber {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = in.nextInt();
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans ^= i ^ arr[i - 1];
        }

        System.out.println(ans);
    }
}
