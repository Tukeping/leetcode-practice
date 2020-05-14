package com.tukeping.cp.leetcode.contest.weekly.contest116;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/8
 **/
public class LeetCode962 {

    public int maxWidthRamp(int[] A) {
        int len = A.length;
        int maxRamp = 0;
        for (int i = 0; i < len - maxRamp; i++) {
            for (int j = i + maxRamp; j < len; j++) {
                if (A[i] <= A[j]) {
                    if (j - i > maxRamp) {
                        maxRamp = j - i;
                    }
                }
            }
        }
        return maxRamp;
    }

    /**
     * 输入：[6,0,8,2,1,5]
     * 输出：4
     * 解释：
     * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
     */
    @Test
    public void test1() {
        int n = maxWidthRamp(new int[]{6, 0, 8, 2, 1, 5});
        assertThat(n, is(4));
    }

    /**
     * 输入：[9,8,1,0,1,9,4,0,4,1]
     * 输出：7
     * 解释：
     * 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
     */
    @Test
    public void test2() {
        int n = maxWidthRamp(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1});
        assertThat(n, is(7));
    }

    @Test
    public void test3() {
        int n = maxWidthRamp(new int[]{1, 0});
        assertThat(n, is(0));
    }
}
