package com.tukeping.tools;

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
}
