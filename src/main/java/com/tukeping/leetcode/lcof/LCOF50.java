package com.tukeping.leetcode.lcof;

import com.tukeping.leetcode.structures.Pair;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF50 {

    public char firstUniqChar(String s) {
        if (s == null || s.isEmpty()) return ' ';
        Map<Character, Integer> M = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            M.put(c, M.getOrDefault(c, 0) + 1);
        }
        for (char c : chars) {
            if (M.get(c) == 1) return c;
        }
        return ' ';
    }

    public char firstUniqChar2(String s) {
        if (s == null || s.isEmpty()) return ' ';

        Map<Character, Pair<Integer, Integer>> M = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (M.containsKey(s.charAt(i))) {
                Pair<Integer, Integer> pair = M.get(s.charAt(i));
                pair.first++;
            } else {
                M.put(s.charAt(i), Pair.of(1, i));
            }
        }
        int min = Integer.MAX_VALUE;
        for (Pair<Integer, Integer> pair : M.values()) {
            if (pair.first == 1) {
                min = Math.min(min, pair.second);
            }
        }
        return min == Integer.MAX_VALUE ? ' ' : s.charAt(min);
    }

    /**
     * s = "abaccdeff"
     * 返回 "b"
     *
     * s = ""
     * 返回 " "
     */
    @Test
    public void test1() {
        char c = firstUniqChar("abaccdeff");
        assertThat(c, is('b'));

        c = firstUniqChar("");
        assertThat(c, is(' '));

        c = firstUniqChar("cc");
        assertThat(c, is(' '));
    }
}
