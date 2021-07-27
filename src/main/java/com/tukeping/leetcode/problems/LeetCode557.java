package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/22
 **/
public class LeetCode557 {

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == ' ') {
                reverse(chars, start, i - 1);
                start = i + 1;
            } else if (i == n - 1) {
                reverse(chars, start, i);
            }
        }
        return new String(chars);
    }

    private void reverse(char[] s, int l, int r) {
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
