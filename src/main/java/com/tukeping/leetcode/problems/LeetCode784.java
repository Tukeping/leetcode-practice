package com.tukeping.leetcode.problems;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author tukeping
 * @date 2021/7/29
 **/
public class LeetCode784 {

    List<String> ret = new ArrayList<>();

    public List<String> letterCasePermutation(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        char[] path = new char[n];
        helper(chars, n, 0, path, 0);
        return ret;
    }

    private void helper(char[] chars, int k, int startIndex, char[] path, int idx) {
        if (idx == k) {
            ret.add(new String(path));
            return;
        }

        for (int i = startIndex; i < k; i++) {
            char c = chars[i];
            if (c >= '0' && c <= '9') {
                path[idx] = c;
                helper(chars, k, i + 1, path, idx + 1);
            } else {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        c = Character.toLowerCase(chars[i]);
                    } else { // j == 1
                        c = Character.toUpperCase(chars[i]);
                    }
                    path[idx] = c;
                    helper(chars, k, i + 1, path, idx + 1);
                }
            }
        }
    }

    @Test
    public void test() {
        List<String> actual = letterCasePermutation("a1b2");
        assertThat(actual, is(Arrays.asList("a1b2", "a1B2", "A1b2", "A1B2")));
    }
}
