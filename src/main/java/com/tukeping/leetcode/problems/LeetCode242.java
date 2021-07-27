package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/24
 **/
public class LeetCode242 {

    public boolean isAnagram(String s, String t) {
        int[] c1 = new int[26];
        for (char c : s.toCharArray()) {
            c1[c - 'a']++;
        }

        int[] c2 = new int[26];
        for (char c : t.toCharArray()) {
            c2[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) return false;
        }
        return true;
    }
}
