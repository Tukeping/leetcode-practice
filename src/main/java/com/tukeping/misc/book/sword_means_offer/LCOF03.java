package com.tukeping.misc.book.sword_means_offer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/*
 * 面试题03. 数组中重复的数字
 *
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 *
 * 限制：
 *
 * 2 <= n <= 100000
 */

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF03 {

    public int findRepeatNumber(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }

    public int findRepeatNumber2(int[] nums) {
        Map<Integer, Boolean> M = new HashMap<>();
        for (int num : nums) {
            if (M.containsKey(num)) {
                return num;
            } else {
                M.put(num, Boolean.TRUE);
            }
        }
        return -1;
    }

    /**
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     */
    @Test
    public void test1() {
        int n = findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3});
        assertThat(n, is(2));
    }
}
