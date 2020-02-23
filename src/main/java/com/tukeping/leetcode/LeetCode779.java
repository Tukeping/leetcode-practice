package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=779 lang=java
 *
 * [779] 第K个语法符号
 *
 * https://leetcode-cn.com/problems/k-th-symbol-in-grammar/description/
 *
 * algorithms
 * Medium (42.32%)
 * Likes:    50
 * Dislikes: 0
 * Total Accepted:    6.8K
 * Total Submissions: 15.8K
 * Testcase Example:  '1\n1'
 *
 * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 *
 * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
 *
 *
 * 例子:
 *
 * 输入: N = 1, K = 1
 * 输出: 0
 *
 * 输入: N = 2, K = 1
 * 输出: 0
 *
 * 输入: N = 2, K = 2
 * 输出: 1
 *
 * 输入: N = 4, K = 5
 * 输出: 1
 *
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * 第三行: 0110
 * 第四行: 01101001
 *
 * 注意：
 *
 * N 的范围 [1, 30].
 * K 的范围 [1, 2^(N-1)].
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/2/22
 **/
public class LeetCode779 {

    // 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。

    public int kthGrammar(int N, int K) {
        return kthGrammarRecursion(N, K);
    }

    public int kthGrammarLoop(int N, int K) {
        int[] lastrow = new int[1 << N];
        for (int i = 1; i < N; ++i) {
            for (int j = (1 << (i - 1)) - 1; j >= 0; --j) {
                lastrow[2 * j] = lastrow[j];
                lastrow[2 * j + 1] = 1 - lastrow[j];
            }
        }
        return lastrow[K - 1];
    }

    public int kthGrammarRecursion(int N, int K) {
        if (N == 1) return 0;
        return (~K & 1) ^ kthGrammarRecursion(N - 1, (K + 1) / 2);
    }

    public int kthGrammarRecursion2(int N, int K) {
        if (N == 1) return 0;
        if (K <= 1 << N - 2)
            return kthGrammarRecursion2(N - 1, K);
        return kthGrammarRecursion2(N - 1, K - (1 << N - 2)) ^ 1;
    }

    public int kthGrammarBit(int N, int K) {
        return Integer.bitCount(K - 1) % 2;
    }

    /**
     * 输入: N = 1, K = 1
     * 输出: 0
     *
     * 输入: N = 2, K = 1
     * 输出: 0
     *
     * 输入: N = 2, K = 2
     * 输出: 1
     *
     * 输入: N = 4, K = 5
     * 输出: 1
     *
     * 解释:
     * 第一行: 0
     * 第二行: 01
     * 第三行: 0110
     * 第四行: 01101001
     */
    @Test
    public void test1() {
        int n = kthGrammar(1, 1);
        assertThat(n, is(0));

        n = kthGrammar(2, 1);
        assertThat(n, is(0));

        n = kthGrammar(2, 2);
        assertThat(n, is(1));

        n = kthGrammar(4, 5);
        assertThat(n, is(1));
    }

    @Test
    public void test2() {
        int n = kthGrammar(30, 434991989);
        assertThat(n, is(0));
    }
}
