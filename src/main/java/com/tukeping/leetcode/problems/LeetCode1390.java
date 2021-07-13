package com.tukeping.leetcode.problems;

/*
 * 1390. 四因数
 *
 * 给你一个整数数组 nums，请你返回该数组中恰有四个因数的这些整数的各因数之和。
 *
 * 如果数组中不存在满足题意的整数，则返回 0 。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums = [21,4,7]
 * 输出：32
 * 解释：
 * 21 有 4 个因数：1, 3, 7, 21
 * 4 有 3 个因数：1, 2, 4
 * 7 有 2 个因数：1, 7
 * 答案仅为 21 的所有因数的和。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/24
 **/
public class LeetCode1390 {

    public int sumFourDivisors(int[] nums) {
        int ans = 0;
        for (int v : nums) {
            int num = 0;
            int s = 0;
            for (int d = 1; d * d <= v; d++) {
                if (v % d == 0) {
                    if (d * d < v) {
                        num += 2;
                        s += d;
                        s += v / d;
                    } else {
                        num++;
                    }
                }
            }
            if (num == 4) ans += s;
        }
        return ans;
    }

    public int sumFourDivisors2(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;

        int[] factor;
        int cnt, mid, sum = 0;
        for (int num : nums) {
            factor = new int[3];
            cnt = 0;
            mid = num / 2;
            for (int x = 2; cnt <= 2 && x <= mid; x++) {
                if (num % x == 0) {
                    factor[cnt++] = x;
                }
            }
            if (cnt != 2) continue;
            sum += 1 + num + factor[0] + factor[1];
        }

        return sum;
    }

    /**
     * 输入：nums = [21,4,7]
     * 输出：32
     * 解释：
     * 21 有 4 个因数：1, 3, 7, 21
     * 4 有 3 个因数：1, 2, 4
     * 7 有 2 个因数：1, 7
     * 答案仅为 21 的所有因数的和。
     */
    @Test
    public void test1() {
        int n = sumFourDivisors(new int[]{21, 4, 7});
        assertThat(n, is(32));
    }

    @Test
    public void test2() {
        int n = sumFourDivisors(new int[]{7286, 18704, 70773, 8224, 91675});
        assertThat(n, is(10932));
    }
}
