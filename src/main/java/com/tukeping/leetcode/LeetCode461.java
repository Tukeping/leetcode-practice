package com.tukeping.leetcode;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * bit-manipulation
 *
 * facebook
 *
 * @author tukeping
 * @date 2020/3/1
 **/
public class LeetCode461 {

    public int hammingDistance(int x, int y) {
        int z = x ^ y;

        String bs = Integer.toBinaryString(z);

        int count = 0;
        for (int i = 0; i < bs.length(); i++) {
            if (bs.charAt(i) == 49) {
                count++;
            }
        }

        return count;
    }

    /**
     * 输入: x = 1, y = 4
     * 输出: 2
     * 解释:
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     * 上面的箭头指出了对应二进制位不同的位置。
     */
    @Test
    public void test1() {
        int n = hammingDistance(1, 4);
        assertThat(n, is(2));
    }
}
