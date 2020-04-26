package com.tukeping.leetcode.contest.weekly.contest186;

import org.junit.Test;

import java.util.ArrayDeque;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/26
 **/
public class LeetCode5180 {

    /**
     * 每一个新元素的结果取决于前K个结果中的最大值，维护一个滑动窗口，管理前K个结果中的最大值
     *
     * f[i] 表示第i个元素作为序列的最后一个数，能够取得的最大和
     * f[i] = max(nums[i], nums[i]+区间{i-k+1,i}最大的f[j])
     *
     * 区间{i-k+1,i}最大的f[j] 用滑动窗口思想
     *
     * 滑动窗口用 数组双端队列 数据结构
     */
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[n];
        f[0] = nums[0];

        ArrayDeque<Integer> deque = new ArrayDeque<>(k);
        deque.add(0);

        int max = f[0];
        for (int i = 1; i < n; i++) {
            f[i] = nums[i] + Math.max(0, f[deque.getFirst()]);
            if (i >= k) {
                while (!deque.isEmpty() && i - deque.getFirst() >= k) {
                    deque.removeFirst();
                }
            }
            while (!deque.isEmpty() && f[i] >= f[deque.getLast()]) {
                deque.removeLast();
            }
            deque.add(i);
            max = Math.max(max, f[i]);
        }
        return max;
    }

    /**
     * 输入：nums = [10,2,-10,5,20], k = 2
     * 输出：37
     * 解释：子序列为 [10, 2, 5, 20] 。
     */
    @Test
    public void test1() {
        int n = constrainedSubsetSum(new int[]{10, 2, -10, 5, 20}, 2);
        assertThat(n, is(37));
    }

    /**
     * 输入：nums = [-1,-2,-3], k = 1
     * 输出：-1
     * 解释：子序列必须是非空的，所以我们选择最大的数字。
     */
    @Test
    public void test2() {
        int n = constrainedSubsetSum(new int[]{-1, -2, -3}, 1);
        assertThat(n, is(-1));
    }

    /**
     * 输入：nums = [10,-2,-10,-5,20], k = 2
     * 输出：23
     * 解释：子序列为 [10, -2, -5, 20] 。
     */
    @Test
    public void test3() {
        int n = constrainedSubsetSum(new int[]{10, -2, -10, -5, 20}, 2);
        assertThat(n, is(23));
    }
}
