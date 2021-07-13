package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=239 lang=java
 *
 * [239] 滑动窗口最大值
 *
 * https://leetcode-cn.com/problems/sliding-window-maximum/description/
 *
 * algorithms
 * Hard (43.13%)
 * Likes:    193
 * Dislikes: 0
 * Total Accepted:    23.3K
 * Total Submissions: 53.9K
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k
 * 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 * ⁠ 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * ⁠1 [3  -1  -3] 5  3  6  7       3
 * ⁠1  3 [-1  -3  5] 3  6  7       5
 * ⁠1  3  -1 [-3  5  3] 6  7       5
 * ⁠1  3  -1  -3 [5  3  6] 7       6
 * ⁠1  3  -1  -3  5 [3  6  7]      7
 *
 *
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 *
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 */

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author tukeping
 * @date 2019/12/29
 **/
public class LeetCode239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        return maxSlidingWindow_Deque(nums, k);
    }

    private ArrayDeque<Integer> deque = new ArrayDeque<>();
    private int[] nums;
    private int k;

    /** 降序排序, 从大到小 **/
    private void push(int idx) {
        while (!deque.isEmpty() && nums[deque.getLast()] < nums[idx]) {
            deque.removeLast();
        }
        if (deque.size() < k) {
            deque.addLast(idx);
        }
    }

    /** 移除窗口外的值 **/
    private void sliding(int idx) {
        while (!deque.isEmpty() && deque.getFirst() < idx - k + 1) {
            deque.removeFirst();
        }
    }

    /**
     * 18/18 cases passed (21 ms)
     * Your runtime beats 40.8 % of java submissions
     * Your memory usage beats 95.75 % of java submissions (40.6 MB)
     */
    private int[] maxSlidingWindow_Deque(int[] nums, int k) {
        int len = nums.length;

        if (len == 0 || k == 0) {
            return new int[0];
        }

        if (k == 1) {
            return nums;
        }

        this.nums = nums;
        this.k = k;

        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            if (i < k) {
                push(i);
            } else {
                // 窗口滑动之前先输出前一个窗口最大值
                ret.add(nums[deque.getFirst()]);
                // 窗口开始滑动
                sliding(i);
                // 添加当前值至窗口队列里
                push(i);
            }
        }
        // 最后一个窗口队列的最大值
        ret.add(nums[deque.getFirst()]);

        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 18/18 cases passed (1491 ms)
     * Your runtime beats 5.05 % of java submissions
     * Your memory usage beats 82.85 % of java submissions (46.7 MB)
     */
    private int[] maxSlidingWindow_Violence(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[]{};
        }

        int[] window = new int[k];
        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i <= nums.length - k; i++) {
            System.arraycopy(nums, i, window, 0, k);
            Arrays.sort(window);
            ret.add(window[window.length - 1]);
        }

        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     */
    @Test
    public void test1() {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};

        int[] ret = maxSlidingWindow(nums, 3);

        // [3,3,5,5,6,7]
        assertThat(ret[0], is(3));
        assertThat(ret[1], is(3));
        assertThat(ret[2], is(5));
        assertThat(ret[3], is(5));
        assertThat(ret[4], is(6));
        assertThat(ret[5], is(7));
    }

    @Test
    public void test2() {
        int[] ret = maxSlidingWindow(new int[]{}, 0);

        assertThat(ret.length, is(0));
    }
}
