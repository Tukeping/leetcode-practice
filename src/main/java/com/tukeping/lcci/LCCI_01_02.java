package com.tukeping.lcci;

import java.util.Arrays;

/**
 * @author tukeping
 * @date 2021/7/21
 **/
public class LCCI_01_02 {

    public boolean CheckPermutationV2(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 != n2) return false;
        int[] letters = new int[26];
        for (char c : s1.toCharArray()) {
            int val = c - 'a';
            letters[val]++;
        }
        for (char c : s2.toCharArray()) {
            int val = c - 'a';
            letters[val]--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean CheckPermutation(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 != n2) return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        for (int i = 0; i < n1; i++) {
            if (c1[i] != c2[i]) return false;
        }
        return true;
    }
}
