package com.tukeping.tools;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author tukeping
 * @date 2020/1/14
 **/
public final class Strings {

    public static String arrayToString(int[] arr) {
        return Arrays.stream(arr).boxed().map(String::valueOf).collect(Collectors.joining(","));
    }

    public static String arrayToString(int[] arr, int exclude) {
        return Arrays.stream(arr).filter(n -> n != exclude).boxed().map(String::valueOf).collect(Collectors.joining(","));
    }
}
