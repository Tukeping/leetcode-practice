package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/9
 **/
public class LeetCode415 {

    public String addStrings(String num1, String num2) {
        char[] s1 = num1.toCharArray();
        char[] s2 = num2.toCharArray();
        int i = s1.length - 1, j = s2.length - 1;
        StringBuilder sb = new StringBuilder();
        int up = 0;
        while (i >= 0 && j >= 0) {
            int n1 = s1[i] - '0';
            int n2 = s2[j] - '0';
            int val = (n1 + n2 + up) % 10;
            up = (n1 + n2 + up) / 10;
            sb.append(val);
            i--;
            j--;
        }
        while (i != -1) {
            int n1 = s1[i] - '0';
            int val = (n1 + up) % 10;
            up = (n1 + up) / 10;
            sb.append(val);
            i--;
        }
        while (j != -1) {
            int n2 = s2[j] - '0';
            int val = (n2 + up) % 10;
            up = (n2 + up) / 10;
            sb.append(val);
            j--;
        }
        if (up > 0) {
            sb.append(up);
        }
        sb.reverse();
        return sb.toString();
    }
}
