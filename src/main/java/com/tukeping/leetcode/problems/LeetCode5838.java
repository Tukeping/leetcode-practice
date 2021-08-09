package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/8
 **/
public class LeetCode5838 {

    public boolean isPrefixString(String s, String[] words) {
        char[] c1 = s.toCharArray();
        int k = 0, n = c1.length;
        for (String word : words) {
            char[] c2 = word.toCharArray();
            if (n - k < c2.length) return false;
            for (int j = 0; j < c2.length && k != n; j++) {
                if (c1[k++] != c2[j]) return false;
            }
            if (k == n) return true;
        }
        return k == n;
    }
}
