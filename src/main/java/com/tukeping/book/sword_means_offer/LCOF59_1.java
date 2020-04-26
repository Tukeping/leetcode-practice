package com.tukeping.book.sword_means_offer;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF59_1 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length <= 1) return nums;

        int len = nums.length;
        int[] ans = new int[len - k + 1];
        for (int p1 = 0, p2, maxPos = -1; p1 <= len - k; p1++) {
            p2 = p1 + k - 1;
            if (maxPos != -1 && nums[p2] >= nums[maxPos]) {
                maxPos = p2;
                ans[p1] = nums[maxPos];
            } else if (p1 <= maxPos) {
                ans[p1] = nums[maxPos];
            } else {
                int maxWindow = Integer.MIN_VALUE;
                int maxPosWindow = 0;
                for (int i = p1; i <= p2; i++) {
                    if (nums[i] > maxWindow) {
                        maxPosWindow = i;
                        maxWindow = nums[i];
                    }
                }
                maxPos = maxPosWindow;
                ans[p1] = nums[maxPos];
            }
        }
        return ans;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length <= 1) return nums;
        int len = nums.length, idx = 0;
        int[] ans = new int[len - k + 1];
        for (int p1 = 0, p2 = k - 1; p2 < len; p1++, p2++) {
            ans[idx++] = max(nums, p1, p2);
        }
        return ans;
    }

    private int max(int[] nums, int start, int end) {
        int max = nums[start];
        for (int i = start + 1; i <= end; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
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
