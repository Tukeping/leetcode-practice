package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/16
 **/
public class LeetCode680 {

    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++; r--;
            } else {
                return validPalindrome(s, l + 1, r) || validPalindrome(s, l, r - 1);
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++; r--;
        }
        return true;
    }
}
