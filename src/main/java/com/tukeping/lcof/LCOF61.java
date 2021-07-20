package com.tukeping.lcof;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/3/30
 **/
public class LCOF61 {

    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int gap = 0, queen = 0;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] == 0) {
                queen++;
            } else if (nums[i] == nums[i + 1]) {
                return false;
            } else if (nums[i] != nums[i + 1] - 1) {
                gap += Math.abs(nums[i + 1] - nums[i]) - 1;
            }
        }
        return queen >= gap;
    }

    /**
     * 输入: [1,2,3,4,5]
     * 输出: True
     */
    @Test
    public void test1() {
        assertTrue(isStraight(new int[]{1, 2, 3, 4, 5}));
    }

    /**
     * 输入: [0,0,1,2,5]
     * 输出: True
     */
    @Test
    public void test2() {
        assertTrue(isStraight(new int[]{0, 0, 1, 2, 5}));
    }

    /**
     * [11,8,12,8,10]
     */
    @Test
    public void test3() {
        assertFalse(isStraight(new int[]{11, 8, 12, 8, 10}));
    }

    /**
     * [8,7,11,0,9]
     */
    @Test
    public void test4() {
        assertTrue(isStraight(new int[]{8, 7, 11, 0, 9}));
    }

    /**
     * [9,1,13,2,13]
     */
    @Test
    public void test5() {
        assertFalse(isStraight(new int[]{9, 1, 13, 2, 13}));
    }

    /**
     * [11,0,9,0,0]
     */
    @Test
    public void test6() {
        assertTrue(isStraight(new int[]{11, 0, 9, 0, 0}));
    }
}
