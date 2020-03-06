package com.tukeping.tools;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

/**
 * @author tukeping
 * @date 2020/1/14
 **/
public final class Strings {

    public static String arrayToString(Integer[] arr) {
        return Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining(","));
    }

    public static String arrayToString(int[] arr) {
        return Arrays.stream(arr).boxed().map(String::valueOf).collect(Collectors.joining(","));
    }

    public static String arrayToString(int[] arr, int exclude) {
        return arrayToString(arr, (n) -> n != exclude);
    }

    public static String arrayToString(int[] arr, IntPredicate fn) {
        return Arrays.stream(arr).filter(fn).boxed().map(String::valueOf).collect(Collectors.joining(","));
    }
}
