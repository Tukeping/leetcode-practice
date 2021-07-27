package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/22
 **/
public class LeetCode344 {

    public void reverseString(char[] s) {
        int n = s.length;
        int l = 0, r = n - 1;
        while (l < r) {
            swap(s, l, r);
            l++;
            r--;
        }
    }

    private void swap(char[] s, int l, int r) {
        char tmp = s[l];
        s[l] = s[r];
        s[r] = tmp;
    }
}
