package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 *
 * https://leetcode-cn.com/problems/container-with-most-water/description/
 *
 * algorithms
 * Medium (60.00%)
 * Likes:    1198
 * Dislikes: 0
 * Total Accepted:    155.7K
 * Total Submissions: 251.1K
 * Testcase Example:  '[1,8,6,2,5,4,8,3,7]'
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为
 * (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/16
 **/
public class LeetCode11 {

    public int maxArea2(int[] height) {
        int len = height.length, maxArea = Integer.MIN_VALUE;
        for (int p1 = 0, p2 = len - 1; p2 > p1; ) {
            maxArea = Math.max(maxArea, (p2 - p1) * Math.min(height[p1], height[p2]));
            if (height[p1] < height[p2]) p1++;
            else p2--;
        }
        return maxArea;
    }

    public int maxArea(int[] height) {
        int len = height.length, maxArea = Integer.MIN_VALUE;
        for (int p1 = 0, p2 = len - 1; p2 > p1; ) {
            if (height[p1] < height[p2]) {
                maxArea = Math.max(maxArea, (p2 - p1) * height[p1]);
                p1++;
            } else {
                maxArea = Math.max(maxArea, (p2 - p1) * height[p2]);
                p2--;
            }
        }
        return maxArea;
    }

    /**
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     */
    @Test
    public void test1() {
        int n = maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        assertThat(n, is(49));
    }
}
