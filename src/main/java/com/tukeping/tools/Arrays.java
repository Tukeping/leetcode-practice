package com.tukeping.tools;

import java.util.List;

/**
 * @author tukeping
 * @date 2020/1/19
 **/
public final class Arrays {

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static int[] asArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        int idx = 0;
        for (Integer n : list) {
            arr[idx++] = n;
        }
        return arr;
    }

    public static void check(int[] actual, int[] expect) {
        if (actual == null && expect == null)
            return;

        if (actual == null || expect == null)
            throw new AssertionError("not matched, actual is null or expect is null");

        if (actual.length != expect.length)
            throw new AssertionError("not matched, actual length not equal expect length");

        for (int i = 0; i < actual.length; i++) {
            if (actual[i] != expect[i]) {
                throw new AssertionError(
                        String.format("not matched, actual = %d, expect = %d",
                                actual[i], expect[i]));
            }
        }
    }
}
