package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/17
 **/
public class LeetCode1967 {

    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;
        for (String p : patterns)
            if (isSubString(p, word))
                ans++;
        return ans;
    }

    private boolean isSubString(String p, String t) {
        int p1 = 0, p2 = 0;
        int pLen = p.length(), tLen = t.length();
        for (int i = 0; i + pLen <= tLen; i++) {
            int j = 0;
            for (; j < pLen; j++) {
                if (p.charAt(j) != t.charAt(i + j)) break;
            }
            if (j == pLen) return true;
        }
        return false;
    }
}
