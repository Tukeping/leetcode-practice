package com.tukeping.lcof;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/3
 **/
public class LCOF38 {

    private char[] chars;
    private int len;
    private List<String> res;

    public String[] permutation(String s) {
        this.len = s.length();
        this.res = new ArrayList<>();
        this.chars = s.toCharArray();
        Arrays.sort(this.chars);
        permutation(0, new StringBuilder(), new boolean[len]);
        return res.toArray(new String[0]);
    }

    private void permutation(int depth, StringBuilder track, boolean[] visited) {
        if (depth == len) {
            res.add(track.toString());
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) continue;
                visited[i] = true;
                track.append(chars[i]);
                permutation(depth + 1, track, visited);
                visited[i] = false;
                track.deleteCharAt(track.length() - 1);
            }
        }
    }

    /**
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     */
    @Test
    public void test1() {
        String[] ans = permutation("abc");
        assertThat(ans, is(new String[]{"abc", "acb", "bac", "bca", "cab", "cba"}));
    }

    @Test
    public void test2() {
        String[] ans = permutation("aab");
        assertThat(Arrays.asList(ans), containsInAnyOrder("aba", "aab", "baa"));
    }

    @Test
    public void test3() {
        String[] ans = permutation("dkjphedy");
    }
}
