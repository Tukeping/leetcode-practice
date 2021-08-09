package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/9
 **/
public class LeetCode5193 {

    public String makeFancyString(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(chars[0]);
        int cnt = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (cnt < 3) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
}
