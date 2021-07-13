package com.tukeping.leetcode.problems;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/13
 **/
public class LeetCode190 {

    public int reverseBits(int n) {
        int ans = 0;
        for (int bitsSize = 31; n != 0; n = n >>> 1, bitsSize--) {
            ans += (n & 1) << bitsSize;
        }
        Integer.toBinaryString(n);
        Integer.parseInt("01", 2);
        return ans;
    }

    /**
     * 输入: 00000010100101000001111010011100
     * 输出: 00111001011110000010100101000000
     * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     *       因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
     */
    @Test
    public void test1() {
        int n = reverseBits(43261596);
        assertThat(n, is(964176192));
    }

    /**
     * 输入：11111111111111111111111111111101
     * 输出：10111111111111111111111111111111
     * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
     *       因此返回 3221225471 其二进制表示形式为 10101111110010110010011101101001。
     */
    @Test
    public void test2() {
        int n = reverseBits(-3);
        assertThat(n, is(-1073741825));
    }
}
