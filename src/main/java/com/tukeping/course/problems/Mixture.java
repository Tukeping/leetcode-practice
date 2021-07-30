package com.tukeping.course.problems;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/25
 **/
public class Mixture {

    public boolean chkMixture(String A, int n, String B, int m, String C, int v) {
        Map<Character, Integer> aMap = new HashMap<>();
        for (char c : A.toCharArray()) {
            aMap.put(c, aMap.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> bMap = new HashMap<>();
        for (char c : B.toCharArray()) {
            bMap.put(c, bMap.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> cMap = new HashMap<>();
        for (char c : C.toCharArray()) {
            cMap.put(c, cMap.getOrDefault(c, 0) + 1);
        }
        Set<Character> keys = cMap.keySet();
        for (char c : keys) {
            int cVal = cMap.getOrDefault(c, 0);
            int aVal = aMap.getOrDefault(c, 0);
            int bVal = bMap.getOrDefault(c, 0);
            if (cVal > aVal + bVal) return false;
        }
        return true;
    }

    @Test
    public void test1() {
        boolean b = chkMixture("bcbccabccacccbcac", 17, "abbbacaabccbccaaaabbcbcbaaacccbaaba", 35, "babbbacaabccbccaaaabbcbcbaaacccbaabacbccabccacccbcac", 52);
        assertTrue(b);
    }

    @Test
    public void test2() {
        boolean b = chkMixture("aabaa", 5, "bbcaabcacaccbbbbbaaabbaabcabccca", 32, "abbcaabcacaccbbbbbaaabbaabcabcccaabaa", 37);
        assertTrue(b);
    }
}
