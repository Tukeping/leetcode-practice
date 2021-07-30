package com.tukeping.leetcode.contest.weekly.contest163;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/28
 **/
public class LeetCode1262 {

    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[] f = {0, 0, 0};

        for (int i = 0; i < n; i++) {
            int mod = nums[i] % 3;

            int a = f[(3 + 0 - mod) % 3];
            int b = f[(3 + 1 - mod) % 3];
            int c = f[(3 + 2 - mod) % 3];

            if (a != 0 || mod == 0) f[0] = Math.max(f[0], a + nums[i]);
            if (b != 0 || mod == 1) f[1] = Math.max(f[1], b + nums[i]);
            if (c != 0 || mod == 2) f[2] = Math.max(f[2], c + nums[i]);
        }

        return f[0];
    }

    /**
     * 输入：nums = [3,6,5,1,8]
     * 输出：18
     * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
     */
    @Test
    public void test2() {
        int[] nums = {3, 6, 5, 1, 8};
        int n = maxSumDivThree(nums);
        assertThat(n, is(18));
    }

    /**
     * 输入：nums = [4]
     * 输出：0
     * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
     */
    @Test
    public void test3() {
        int[] nums = {4};
        int n = maxSumDivThree(nums);
        assertThat(n, is(0));
    }

    /**
     * 输入：nums = [1,2,3,4,4]
     * 输出：12
     * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
     */
    @Test
    public void test4() {
        int[] nums = {1, 2, 3, 4, 4};
        int n = maxSumDivThree(nums);
        assertThat(n, is(12));
    }

    @Test
    public void test1() {
        int[] nums = {366, 809, 6, 792, 822, 181, 210, 588, 344, 618, 341, 410, 121, 864, 191, 749, 637, 169, 123, 472, 358, 908, 235, 914, 322, 946, 738, 754, 908, 272, 267, 326, 587, 267, 803, 281, 586, 707, 94, 627, 724, 469, 568, 57, 103, 984, 787, 552, 14, 545, 866, 494, 263, 157, 479, 823, 835, 100, 495, 773, 729, 921, 348, 871, 91, 386, 183, 979, 716, 806, 639, 290, 612, 322, 289, 910, 484, 300, 195, 546, 499, 213, 8, 623, 490, 473, 603, 721, 793, 418, 551, 331, 598, 670, 960, 483, 154, 317, 834, 352};
        int n = maxSumDivThree(nums);
        assertThat(n, is(50487));
    }
}
