package com.tukeping.leetcode.contest.weekly.contest187;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/5/3
 **/
public class LeetCode5401 {

    public boolean kLengthApart(int[] nums, int k) {
        if (k == 0) return true;

        int n = nums.length;
        int gap = 0;

        for (int i = 0, j = 0; j < n; ) {
            if (nums[j] == 1) {
                if (i == j) {
                    j++;
                } else if (j > i) {
                    if (nums[0] == 0 && i == 0) {
                        i = j;
                        j++;
                        continue;
                    }
                    gap = j - i - 1;
                    if (gap < k) return false;
                    i = j;
                    j++;
                }
            } else { // 0
                j++;
            }
        }
        return true;
    }

    /**
     * 输入：nums = [1,0,0,0,1,0,0,1], k = 2
     * 输出：true
     * 解释：每个 1 都至少相隔 2 个元素。
     */
    @Test
    public void test1() {
        assertTrue(kLengthApart(new int[]{1, 0, 0, 0, 1, 0, 0, 1}, 2));
    }

    /**
     * 输入：nums = [1,0,0,1,0,1], k = 2
     * 输出：false
     * 解释：第二个 1 和第三个 1 之间只隔了 1 个元素。
     */
    @Test
    public void test2() {
        assertFalse(kLengthApart(new int[]{1, 0, 0, 1, 0, 1}, 2));
    }

    /**
     * 输入：nums = [1,1,1,1,1], k = 0
     * 输出：true
     */
    @Test
    public void test3() {
        assertTrue(kLengthApart(new int[]{1, 1, 1, 1, 1}, 0));
    }

    /**
     * 输入：nums = [0,1,0,1], k = 1
     * 输出：true
     */
    @Test
    public void test4() {
        assertTrue(kLengthApart(new int[]{0, 1, 0, 1}, 1));
    }
}
