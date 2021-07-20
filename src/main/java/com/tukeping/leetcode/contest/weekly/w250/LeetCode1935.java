package com.tukeping.leetcode.contest.weekly.w250;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tukeping
 * @date 2021/7/19
 **/
public class LeetCode1935 {

    public int canBeTypedWords(String text, String brokenLetters) {
        char[] chars = brokenLetters.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            set.add(c);
        }
        int ans = 0;
        String[] words = text.split(" ");
        for (String word : words) {
            char[] ws = word.toCharArray();
            boolean broken = false;
            for (char c : ws) {
                if (set.contains(c)) {
                    broken = true;
                    break;
                }
            }
            if (!broken) ans++;
        }
        return ans;
    }
}
