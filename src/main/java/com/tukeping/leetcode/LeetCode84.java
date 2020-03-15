package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=84 lang=java
 *
 * [84] 柱状图中最大的矩形
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/description/
 *
 * algorithms
 * Hard (37.83%)
 * Likes:    436
 * Dislikes: 0
 * Total Accepted:    27.6K
 * Total Submissions: 71.3K
 * Testcase Example:  '[2,1,5,6,2,3]'
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *
 *
 *
 *
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 *
 *
 *
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 *
 *
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 */

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | stack
 *
 * @author tukeping
 * @date 2020/3/12
 **/
public class LeetCode84 {

    /** Stack time: O(n) space: O(n) **/
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxArea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        return maxArea;
    }

    /** DivideAndConquer time: O(n log n) space: O(n) **/
    public int largestRectangleArea4(int[] heights) {
        return calcArea(heights, 0, heights.length - 1);
    }

    private int calcArea(int[] heights, int start, int end) {
        if (start > end) return 0;
        int minIndex = start;
        for (int i = start; i <= end; i++) {
            if (heights[minIndex] > heights[i]) minIndex = i;
        }
        int maxArea = heights[minIndex] * (end - start + 1);
        int maxLeftArea = calcArea(heights, start, minIndex - 1);
        int maxRightArea = calcArea(heights, minIndex + 1, end);
        return Math.max(maxArea, Math.max(maxLeftArea, maxRightArea));
    }

    /** BruteForce time: O(n^2) space: O(1) **/
    public int largestRectangleArea3(int[] heights) {
        int maxArea = 0, minHeight, len = heights.length;
        for (int i = 0; i < len; i++) {
            minHeight = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    /** BruteForce time: O(n^3) space: O(1) **/
    public int largestRectangleArea2(int[] heights) {
        int maxArea = 0, minHeight, len = heights.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                minHeight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    minHeight = Math.min(minHeight, heights[k]);
                }
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    /**
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     */
    @Test
    public void test1() {
        int n = largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
        assertThat(n, is(10));
    }
}
