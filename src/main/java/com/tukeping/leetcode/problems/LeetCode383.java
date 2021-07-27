package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/24
 **/
public class LeetCode383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] ransom = new int[26];
        for (char c : ransomNote.toCharArray()) {
            ransom[c - 'a']++;
        }

        int[] letters = new int[26];
        for (char c : magazine.toCharArray()) {
            letters[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (ransom[i] > letters[i]) return false;
        }
        return true;
    }
}
