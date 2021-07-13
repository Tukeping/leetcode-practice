package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=57 lang=java
 *
 * [57] 插入区间
 *
 * https://leetcode-cn.com/problems/insert-interval/description/
 *
 * algorithms
 * Hard (36.18%)
 * Likes:    101
 * Dislikes: 0
 * Total Accepted:    15.3K
 * Total Submissions: 41.3K
 * Testcase Example:  '[[1,3],[6,9]]\n[2,5]'
 *
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 *
 *
 * 示例 2:
 *
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 *
 */

import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | sort
 *
 * facebook | google | linkedin
 *
 * frequency 5
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0], newEnd = newInterval[1];
        int idx = 0, n = intervals.length;
        LinkedList<int[]> output = new LinkedList<>();

        while (idx < n && newStart > intervals[idx][0])
            output.add(intervals[idx++]);

        int[] interval;
        if (output.isEmpty() || output.getLast()[1] < newStart) {
            output.add(newInterval);
        } else {
            interval = output.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            output.add(interval);
        }

        while (idx < n) {
            interval = intervals[idx++];
            int start = interval[0], end = interval[1];
            if (output.getLast()[1] < start) {
                output.add(interval);
            } else {
                interval = output.removeLast();
                interval[1] = Math.max(interval[1], end);
                output.add(interval);
            }
        }

        return output.toArray(new int[output.size()][2]);
    }

    /**
     * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出: [[1,5],[6,9]]
     */
    @Test
    public void test1() {
        int[][] intervals = {
                {1, 3},
                {6, 9}
        };
        int[] newInterval = {2, 5};
        int[][] ret = insert(intervals, newInterval);
        int[][] expect = {
                {1, 5},
                {6, 9}
        };
        assertThat(ret, is(expect));
    }

    /**
     * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出: [[1,2],[3,10],[12,16]]
     * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     */
    @Test
    public void test2() {
        int[][] intervals = {
                {1, 2},
                {3, 5},
                {6, 7},
                {8, 10},
                {12, 16}
        };
        int[] newInterval = {4, 8};
        int[][] ret = insert(intervals, newInterval);
        int[][] expect = {
                {1, 2},
                {3, 10},
                {12, 16}
        };
        assertThat(ret, is(expect));
    }

    @Test
    public void test3() {
        int[][] intervals = {};
        int[] newInterval = {5, 7};
        int[][] ret = insert(intervals, newInterval);
        int[][] expect = {
                {5, 7}
        };
        assertThat(ret, is(expect));
    }

    @Test
    public void test4() {
        int[][] intervals = {
                {1, 5}
        };
        int[] newInterval = {2, 3};
        int[][] ret = insert(intervals, newInterval);
        int[][] expect = {
                {1, 5}
        };
        assertThat(ret, is(expect));
    }

    @Test
    public void test5() {
        int[][] intervals = {
                {1, 5}
        };
        int[] newInterval = {6, 8};
        int[][] ret = insert(intervals, newInterval);
        int[][] expect = {
                {1, 5},
                {6, 8}
        };
        assertThat(ret, is(expect));
    }

    @Test
    public void test6() {
        int[][] intervals = {
                {1, 5}
        };
        int[] newInterval = {0, 0};
        int[][] ret = insert(intervals, newInterval);
        int[][] expect = {
                {0, 0},
                {1, 5}
        };
        assertThat(ret, is(expect));
    }

    @Test
    public void test7() {
        int[][] intervals = {
                {3, 5},
                {12, 15}
        };
        int[] newInterval = {6, 6};
        int[][] ret = insert(intervals, newInterval);
        int[][] expect = {
                {3, 5},
                {6, 6},
                {12, 15}
        };
        assertThat(ret, is(expect));
    }
}
