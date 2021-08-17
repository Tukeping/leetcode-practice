package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=455 lang=java
 *
 * [455] 分发饼干
 *
 * https://leetcode-cn.com/problems/assign-cookies/description/
 *
 * algorithms
 * Easy (52.37%)
 * Likes:    130
 * Dislikes: 0
 * Total Accepted:    24.3K
 * Total Submissions: 46.1K
 * Testcase Example:  '[1,2,3]\n[1,1]'
 *
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi
 * ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i
 * ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 注意：
 *
 * 你可以假设胃口值为正。
 * 一个小朋友最多只能拥有一块饼干。
 *
 * 示例 1:
 *
 *
 * 输入: [1,2,3], [1,1]
 *
 * 输出: 1
 *
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 *
 *
 * 示例 2:
 *
 *
 * 输入: [1,2], [1,2,3]
 *
 * 输出: 2
 *
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *
 *
 */

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/9
 **/
public class LeetCode455 {

    public int findContentChildrenV2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child = 0, cookie = 0;
        int childSize = g.length, cookieSize = s.length;
        while (child < childSize && cookie < cookieSize) {
            if (s[cookie] >= g[child]) {
                child++;
            }
            cookie++;
        }
        return child;
    }

    public int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null) return 0;

        Arrays.sort(g);
        Arrays.sort(s);

        int count = 0, sIndex = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            // g[i] <= s[sIndex] 表示 饼干能喂饱小朋友的胃口
            if (sIndex >= 0 && g[i] <= s[sIndex]) {
                count++;
                sIndex--;
            }
        }
        return count;
    }

    public int findContentChildren_Best(int[] g, int[] s) {
        if (g == null || s == null) return 0;

        Arrays.sort(g);
        Arrays.sort(s);

        int gi = 0, si = 0;
        while (gi < g.length && si < s.length) {
            if (g[gi] <= s[si]) {
                gi++;
            }
            si++;
        }
        return gi;
    }

    /**
     * 输入: [1,2,3], [1,1]
     *
     * 输出: 1
     *
     * 解释:
     * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
     * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
     * 所以你应该输出1。
     */
    @Test
    public void test1() {
        int n = findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1});
        assertThat(n, is(1));
    }

    /**
     * 输入: [1,2], [1,2,3]
     *
     * 输出: 2
     *
     * 解释:
     * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
     * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
     * 所以你应该输出2.
     */
    @Test
    public void test2() {
        int n = findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3});
        assertThat(n, is(2));
    }

    /**
     * [10,9,8,7]
     * [5,6,7,8]
     *
     * 输出: 2
     */
    @Test
    public void test3() {
        int n = findContentChildren(new int[]{10, 9, 8, 7}, new int[]{5, 6, 7, 8});
        assertThat(n, is(2));
    }
}
