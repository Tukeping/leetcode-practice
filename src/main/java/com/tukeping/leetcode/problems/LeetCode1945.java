package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/4
 **/
public class LeetCode1945 {

    public int getLucky(String s, int k) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            int val = c - 'a' + 1;
            sb.append(val);
        }
        String num = sb.toString();
        // System.out.println("num = " + num);
        int sum = 0;
        while (k-- > 0) {
            sum = 0;
            for (char c : num.toCharArray()) {
                int val = c - '0';
                sum += val;
            }
            // System.out.println("sum = " + sum);
            num = String.valueOf(sum);
        }
        return sum;
    }
}
