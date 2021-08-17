package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/17
 **/
public class LeetCode504 {

    public String convertToBase7(int num) {
        if (num == 0) return "0";
        boolean isNegative = num < 0;
        if (isNegative) num = -num;
        String ans = "";
        while (num > 0) {
            int a = num / 7, b = num % 7;
            ans = b + ans;
            num = a;
        }
        return isNegative ? "-" + ans : ans;
    }
}
