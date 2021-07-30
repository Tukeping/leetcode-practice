package com.tukeping.leetcode.contest.weekly.contest145;

import java.util.Arrays;

/**
 * @author tukeping
 * @date 2020/4/28
 **/
public class LeetCode1122 {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int n = arr1.length, m = arr2.length;
        if (n == 0 || m == 0) return arr1;

        int[] rnk = new int[1050];
        for (int i = 0; i < n; i++) rnk[i] = 1050 + i;
        for (int i = 0; i < m; i++) rnk[arr2[i]] = i;

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) arr[i] = arr1[i];
        Arrays.sort(arr, (o1, o2) -> rnk[o2] - rnk[o1]);

        for (int i = 0; i < n; i++) arr1[i] = arr[i];

        return arr1;
    }
}
