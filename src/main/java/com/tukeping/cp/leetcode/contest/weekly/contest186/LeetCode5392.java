package com.tukeping.cp.leetcode.contest.weekly.contest186;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/26
 **/
public class LeetCode5392 {

    public int maxScore(String s) {
        int n = s.length();
        int c0 = 0, c1 = 0;
        for (char c : s.toCharArray()) if (c == '1') c1++;
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            char c = s.charAt(i);
            if (c == '0') {
                c0++;
            } else if (c == '1') {
                c1--;
            }
            ans = Math.max(ans, c0 + c1);
        }
        return ans;
    }

    /**
     * 输入：s = "011101"
     * 输出：5
     * 解释：
     * 将字符串 s 划分为两个非空子字符串的可行方案有：
     * 左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 + 4 = 5
     * 左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4
     * 左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 = 3
     * 左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2
     * 左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
     */
    @Test
    public void test1() {
        int n = maxScore("011101");
        assertThat(n, is(5));
    }

    /**
     * 输入：s = "00111"
     * 输出：5
     * 解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
     */
    @Test
    public void test2() {
        int n = maxScore("00111");
        assertThat(n, is(5));
    }

    /**
     * 输入：s = "1111"
     * 输出：3
     */
    @Test
    public void test3() {
        int n = maxScore("1111");
        assertThat(n, is(3));
    }
}
