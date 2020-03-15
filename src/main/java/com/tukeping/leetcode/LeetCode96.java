package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=96 lang=java
 *
 * [96] 不同的二叉搜索树
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees/description/
 *
 * algorithms
 * Medium (63.97%)
 * Likes:    421
 * Dislikes: 0
 * Total Accepted:    33.1K
 * Total Submissions: 50.9K
 * Testcase Example:  '3'
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 * ⁠  1         3     3      2      1
 * ⁠   \       /     /      / \      \
 * ⁠    3     2     1      1   3      2
 * ⁠   /     /       \                 \
 * ⁠  2     1         2                 3
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * dynamic-programming | tree
 *
 * snapchat
 *
 * @author tukeping
 * @date 2020/3/11
 **/
public class LeetCode96 {

    public int numTrees(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

    public int numTrees3(int n) {
        long pre = 1, cur = 1;
        for (int i = 1; i < n + 1; i++) {
            cur = pre * (4 * i - 2) / (i + 1);
            pre = cur;
        }
        return (int) cur;
    }

    public int numTrees2(int n) {
        if (n == 1) return 1;
        long[] f = new long[n + 1];
        f[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            f[i] = f[i - 1] * (4 * i - 2) / (i + 1);
        }
        return (int) f[n];
    }

    /**
     * 输入: 3
     * 输出: 5
     * 解释:
     * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     */
    @Test
    public void test1() {
        int n = numTrees(3);
        assertThat(n, is(5));
    }

    @Test
    public void test2() {
        int n = numTrees(19);
        assertThat(n, is(1767263190));
    }
}
