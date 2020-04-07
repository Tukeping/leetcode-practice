package com.tukeping.leetcode.contest183;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/5
 **/
public class LeetCode5377 {

    public int numSteps(String s) {
        int len = s.length();
        if (len == 1) return 0;

        char[] chars = s.toCharArray();
        int[] bits = new int[len];
        for (int i = len - 1; i >= 0; i--)
            bits[len - 1 - i] = (chars[i] - 48);

        int cnt = 0, up = 0;
        for (int i = 0; i < len; ) {
            if (bits[i] == 1 && i != len - 1) {
                bits[i]++;
                cnt++;
                int k = i;
                while (k < len && bits[k] == 2) {
                    up = bits[k] / 2 + up;
                    bits[k] = bits[k] % 2;
                    if (k < len - 1)
                        bits[k + 1] = bits[k + 1] + up;
                    up = 0;
                    k++;
                }
                while (i < len && bits[i] == 0) {
                    i++;
                    cnt++;
                }
            } else if (bits[i] == 0) {
                i++;
                cnt++;
            } else {
                i++;
            }
        }
        return cnt;
    }

    /**
     * 输入：s = "1101"
     * 输出：6
     * 解释："1101" 表示十进制数 13 。
     * Step 1) 13 是奇数，加 1 得到 14
     * Step 2) 14 是偶数，除 2 得到 7
     * Step 3) 7  是奇数，加 1 得到 8
     * Step 4) 8  是偶数，除 2 得到 4
     * Step 5) 4  是偶数，除 2 得到 2
     * Step 6) 2  是偶数，除 2 得到 1
     */
    @Test
    public void test1() {
        int n = numSteps("1101");
        assertThat(n, is(6));
    }

    /**
     * 输入：s = "10"
     * 输出：1
     * 解释："10" 表示十进制数 2 。
     * Step 1) 2 是偶数，除 2 得到 1
     */
    @Test
    public void test2() {
        int n = numSteps("10");
        assertThat(n, is(1));
    }

    /**
     * 输入：s = "1"
     * 输出：0
     */
    @Test
    public void test3() {
        int n = numSteps("1");
        assertThat(n, is(0));
    }

    @Test
    public void test4() {
        int n = numSteps("1111011110000011100000110001011011110010111001010111110001");
    }

    @Test
    public void test5() {
        int n = numSteps("11000");
        assertThat(n, is(6));
    }
}
