package com.tukeping.leetcode.weekly.contest183;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/5
 **/
public class LeetCode5376 {

    public List<Integer> minSubsequence(int[] nums) {
        int len = nums.length;
        if (len == 1) return Arrays.asList(nums[0]);

        Arrays.sort(nums);

        int[] sumUp = new int[len];
        System.arraycopy(nums, 0, sumUp, 0, len);

        for (int i = 1; i < len; i++) {
            sumUp[i] += sumUp[i - 1];
        }

        List<Integer> ans = new ArrayList<>();
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += nums[i];
            ans.add(nums[i]);
            if (i >= 1 && sum > sumUp[i - 1]) {
                break;
            }
        }
        return ans;
    }

    @Test
    public void test1() {
        List<Integer> ans = minSubsequence(new int[]{8, 8});
        assertThat(com.tukeping.tools.Arrays.asArray(ans), is(new int[]{8, 8}));
    }

    @Test
    public void test2() {
        List<Integer> ans = minSubsequence(new int[]{5, 3});
        assertThat(com.tukeping.tools.Arrays.asArray(ans), is(new int[]{5}));
    }

    /**
     * 输入：nums = [4,3,10,9,8]
     * 输出：[10,9]
     * 解释：子序列 [10,9] 和 [10,8] 是最小的、满足元素之和大于其他各元素之和的子序列。但是 [10,9] 的元素之和最大。
     */
    @Test
    public void test3() {
        List<Integer> ans = minSubsequence(new int[]{4, 3, 10, 9, 8});
        assertThat(com.tukeping.tools.Arrays.asArray(ans), is(new int[]{10, 9}));
    }

    /**
     * 输入：nums = [4,4,7,6,7]
     * 输出：[7,7,6]
     * 解释：子序列 [7,7] 的和为 14 ，不严格大于剩下的其他元素之和（14 = 4 + 4 + 6）。因此，[7,6,7] 是满足题意的最小子序列。注意，元素按非递增顺序返回。
     */
    @Test
    public void test4() {
        List<Integer> ans = minSubsequence(new int[]{4, 4, 7, 6, 7});
        assertThat(com.tukeping.tools.Arrays.asArray(ans), is(new int[]{7, 7, 6}));
    }

    /**
     * 输入：nums = [6]
     * 输出：[6]
     */
    @Test
    public void test5() {
        List<Integer> ans = minSubsequence(new int[]{6});
        assertThat(com.tukeping.tools.Arrays.asArray(ans), is(new int[]{6}));
    }
}
