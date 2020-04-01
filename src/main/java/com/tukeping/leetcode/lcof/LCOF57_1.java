package com.tukeping.leetcode.lcof;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF57_1 {

    /** time: O(n) space: O(n) **/
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> M = new HashMap<>();
        for (int num : nums) {
            if (M.containsKey(num)) {
                return new int[]{M.get(num), num};
            } else {
                M.put(target - num, num);
            }
        }
        return new int[0];
    }

    /**
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[2,7] 或者 [7,2]
     */
    @Test
    public void test1() {
        int[] ans = twoSum(new int[]{2, 7, 11, 15}, 9);
        assertThat(ans, is(new int[]{2, 7}));
    }

    /**
     * 输入：nums = [10,26,30,31,47,60], target = 40
     * 输出：[10,30] 或者 [30,10]
     */
    @Test
    public void test2() {
        int[] ans = twoSum(new int[]{10, 26, 30, 31, 47, 60}, 40);
        assertThat(ans, is(new int[]{10, 30}));
    }
}
