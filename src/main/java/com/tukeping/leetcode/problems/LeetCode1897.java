package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/13
 **/
public class LeetCode1897 {

    public boolean makeEqual(String[] words) {
        int[] letter = new int[26];
        for (String word : words) {
            for (char c : word.toCharArray()) {
                letter[c - 'a']++;
            }
        }
        int n = words.length;
        for (int i = 0; i < 26; i++) {
            if (letter[i] > 0 && letter[i] % n != 0) {
                return false;
            }
        }
        return true;
    }
}
