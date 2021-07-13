package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=120 lang=java
 *
 * [120] 三角形最小路径和
 *
 * https://leetcode-cn.com/problems/triangle/description/
 *
 * algorithms
 * Medium (62.96%)
 * Likes:    330
 * Dislikes: 0
 * Total Accepted:    44K
 * Total Submissions: 68.9K
 * Testcase Example:  '[[2],[3,4],[6,5,7],[4,1,8,3]]'
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 * ⁠    [2],
 * ⁠   [3,4],
 * ⁠  [6,5,7],
 * ⁠ [4,1,8,3]
 * ]
 *
 *
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 */

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/13
 **/
public class LeetCode120 {

    /** DP time:O(n^2) space: O(1) **/
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> line = triangle.get(i);
            List<Integer> nextLine = triangle.get(i + 1);
            for (int j = 0; j < line.size(); j++) {
                line.set(j, line.get(j) + Math.min(nextLine.get(j), nextLine.get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }

    /**
     * 例如，给定三角形：
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     */
    @Test
    public void test1() {
        int[][] triangle = {
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };
        int n = minimumTotal(ListHelper.toDoubleList(triangle));
        assertThat(n, is(11));
    }
}
