package com.tukeping.leetcode.contest.weekly.contest185;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/19
 **/
public class LeetCode5390 {

    public int minNumberOfFrogs(String croakOfFrogs) {
        //char[] croak = {'c', 'r', 'o', 'a', 'k'};
        int[] cnt = new int[128];
        for (char c : croakOfFrogs.toCharArray()) cnt[c]++;

        int x = cnt['c'] ^ cnt['r'] ^ cnt['o'] ^ cnt['a'];
        if (x != 0 || cnt['c'] != cnt['k']) return -1;

        int n = croakOfFrogs.length();
        int[] dp = new int[n];
        dp[0] = 1;

        int max = Integer.MIN_VALUE;
        cnt = new int[128];
        for (int i = 0; i < n; i++) {
            char c = croakOfFrogs.charAt(i);
            if (c == 'r') {
                if (cnt['c'] < cnt[c]) return -1;
            } else if (c == 'o') {
                if (cnt['r'] < cnt[c]) return -1;
                if (cnt['c'] < cnt[c]) return -1;
            } else if (c == 'a') {
                if (cnt['o'] < cnt[c]) return -1;
                if (cnt['r'] < cnt[c]) return -1;
                if (cnt['c'] < cnt[c]) return -1;
            } else if (c == 'k') {
                if (cnt['a'] < cnt[c]) return -1;
                if (cnt['o'] < cnt[c]) return -1;
                if (cnt['r'] < cnt[c]) return -1;
                if (cnt['c'] < cnt[c]) return -1;
            }
            dp[i] = cnt[c] - cnt['k'] + 1;
            if (dp[i] > max) max = dp[i];
            cnt[c]++;
        }
        return max;
    }

    @Test
    public void test1() {
        int n = minNumberOfFrogs("croakcroak");
        assertThat(n, is(1));
    }

    @Test
    public void test2() {
        int n = minNumberOfFrogs("crcoakroak");
        assertThat(n, is(2));
    }

    @Test
    public void test3() {
        int n = minNumberOfFrogs("croakcrook");
        assertThat(n, is(-1));
    }

    @Test
    public void test4() {
        int n = minNumberOfFrogs("croakcroa");
        assertThat(n, is(-1));
    }

    @Test
    public void test5() {
        int n = minNumberOfFrogs("aoocrrackk");
        assertThat(n, is(-1));
    }
}
