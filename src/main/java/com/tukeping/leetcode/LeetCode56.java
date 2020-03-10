package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=56 lang=java
 *
 * [56] 合并区间
 *
 * https://leetcode-cn.com/problems/merge-intervals/description/
 *
 * algorithms
 * Medium (39.47%)
 * Likes:    286
 * Dislikes: 0
 * Total Accepted:    52.6K
 * Total Submissions: 130.6K
 * Testcase Example:  '[[1,3],[2,6],[8,10],[15,18]]'
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | sort
 *
 * bloomberg | facebook | google | linkedin | microsoft | twitter | yelp
 *
 * frequency 5
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode56 {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));

        List<int[]> ans = new ArrayList<>();
        int p1 = 0, p2 = 1;
        while (p2 < intervals.length) {
            if (intervals[p1][1] < intervals[p2][0]) {
                ans.add(intervals[p1]);
                p1 = p2;
                p2++;
            } else if (intervals[p1][1] >= intervals[p2][1]) {
                p2++;
            } else {
                intervals[p1][1] = intervals[p2][1];
                p2++;
            }
        }
        ans.add(intervals[p1]);

        return ans.toArray(new int[0][]);
    }

    /**
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     */
    @Test
    public void test1() {
        int[][] input = {
                {15, 18},
                {1, 3},
                {2, 6},
                {8, 10}
        };
        int[][] actual = merge(input);
        int[][] expect = {
                {1, 6},
                {8, 10},
                {15, 18}
        };
        assertThat(actual, is(expect));
    }

    /**
     * 输入: [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     */
    @Test
    public void test2() {
        int[][] input = {
                {1, 4},
                {4, 5}
        };
        int[][] actual = merge(input);
        int[][] expect = {
                {1, 5}
        };
        assertThat(actual, is(expect));
    }
}
