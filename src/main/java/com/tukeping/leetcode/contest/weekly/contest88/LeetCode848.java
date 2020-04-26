package com.tukeping.leetcode.contest.weekly.contest88;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/11
 **/
public class LeetCode848 {

    public String shiftingLetters(String S, int[] shifts) {
        int sLen = S.length();
        int len = shifts.length;
        char[] chars = S.toCharArray();
        long sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += shifts[i];
            // a~z : 97~122
            long tmp = chars[i] - 97 + sum;
            chars[i] = (char) (tmp % 26 + 97);
        }
        return new String(chars);
    }

    /**
     * 输入：S = "abc", shifts = [3,5,9]
     * 输出："rpl"
     * 解释：
     * 我们以 "abc" 开始。
     * 将 S 中的第 1 个字母移位 3 次后，我们得到 "dbc"。
     * 再将 S 中的前 2 个字母移位 5 次后，我们得到 "igc"。
     * 最后将 S 中的这 3 个字母移位 9 次后，我们得到答案 "rpl"。
     */
    @Test
    public void test1() {
        String ans = shiftingLetters("abc", new int[]{3, 5, 9});
        assertThat(ans, is("rpl"));
    }

    @Test
    public void test2() {
        String ans = shiftingLetters("mkgfzkkuxownxvfvxasy", new int[]{505870226, 437526072, 266740649, 224336793, 532917782, 311122363, 567754492, 595798950, 81520022, 684110326, 137742843, 275267355, 856903962, 148291585, 919054234, 467541837, 622939912, 116899933, 983296461, 536563513});
        assertThat(ans, is("wqqwlcjnkphhsyvrkdod"));
    }
}
