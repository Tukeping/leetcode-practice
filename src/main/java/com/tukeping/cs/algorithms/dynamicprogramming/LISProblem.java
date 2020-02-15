package com.tukeping.cs.algorithms.dynamicprogramming;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 最长上升子序列（LIS）问题
 *
 * binary-search | dynamic-programming
 *
 * microsoft
 *
 * Longest ascending subsequence
 *
 * @author tukeping
 * @date 2020/2/15
 **/
public class LISProblem {

    /*
     * 给定一个长度n的序列a，从a抽取出子序列，这个子序列需要单调递增。问：最长上升子序列(LIS) 长度 是多少？
     */

    /** time: O(n log n) space: O(n) **/
    public int lengthOfLIS(int[] a) {
        // corner case
        if (a == null || a.length == 0) return 0;

        int len = a.length;
        int[] f = new int[len];
        f[0] = a[0];
        int fLen = 1;

        // time O(n) => for x in len
        for (int x = 1; x < len; x++) {
            if (a[x] > f[fLen - 1]) // 单调递增
                f[fLen++] = a[x];
            else // 考虑替换f数组中比a[x]大的数字
                bsearch(f, fLen, a[x]);
        }

        return fLen;
    }

    /** time O(log n) space: O(1) **/
    private void bsearch(int[] f, int fLen, int x) {
        int start = 0, end = fLen, mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (f[mid] >= x)
                end = mid;
            else
                start = mid + 1;
        }
        f[end] = Math.min(f[end], x);
    }

    /** time: O(n^2) space: O(n) **/
    public int lengthOfLISDpLoop(int[] a) {
        int len = a.length;
        int x, p, max = Integer.MIN_VALUE;

        int[] f = new int[len];
        Arrays.fill(f, 1); // 因为f(x)每个元素初始化时 该状态表示有几个元素，很显然就是每个元素的初始状态就是都是1个元素

        for (x = 0; x < len; x++) {
            for (p = 0; p < x; p++)
                if (a[p] < a[x]) f[x] = Math.max(f[x], f[p] + 1);
            System.out.println(String.format("f[%d] = %d", x, f[x]));
        }

        for (x = 0; x < len; x++)
            max = Math.max(max, f[x]);

        return max;
    }

    @Test
    public void test1() {
        int n = lengthOfLIS(new int[]{1, 5, 3, 4, 6, 9, 7, 8});
        assertThat(n, is(6)); // 1, 3, 4, 6, 7, 8
    }

    @Test
    public void test2() {
        int n = lengthOfLIS(new int[]{1, 7, 6, 2, 3, 4});
        assertThat(n, is(4)); // 1,2,3,4
    }

    /**
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     */
    @Test
    public void test3() {
        int n = lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        assertThat(n, is(4));
    }

    /**
     * 输入: [2,2]
     * 输出: 1
     */
    @Test
    public void test4() {
        int n = lengthOfLIS(new int[]{2, 2});
        assertThat(n, is(1));
    }

    /**
     * 输入: [4,10,4,3,8,9]
     * 输出: 3
     */
    @Test
    public void test5() {
        int n = lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9});
        assertThat(n, is(3));
    }

    /**
     * 输入: [1,3,6,7,9,4,10,5,6]
     * 输出: 6
     */
    @Test
    public void test6() {
        int n = lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6});
        assertThat(n, is(6));
    }
}
