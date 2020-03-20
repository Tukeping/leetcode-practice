package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=338 lang=java
 *
 * [338] 比特位计数
 *
 * https://leetcode-cn.com/problems/counting-bits/description/
 *
 * algorithms
 * Medium (74.81%)
 * Likes:    255
 * Dislikes: 0
 * Total Accepted:    30.4K
 * Total Submissions: 40.6K
 * Testcase Example:  '2'
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * dynamic-programming | bit-manipulation
 *
 * @author tukeping
 * @date 2020/3/18
 **/
public class LeetCode338 {

    /** 状态转移方程: P(x) = P(x & (x−1)) + 1 **/
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i & (i - 1)] + 1;
        return ans;
    }

    /** 状态转移方程: P(x) = P(x/2) + (x mod 2) **/
    public int[] countBits5(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
        return ans;
    }

    /** 状态转移方程: P(x+b) = P(x) + 1, b= 2^m >x **/
    public int[] countBits4(int num) {
        int[] ans = new int[num + 1];
        int b = 1;
        // [0, b) is calculated
        while (b <= num) {
            // generate [b, 2b) or [b, num) from [0, b)
            for (int i = 0; i < b && i + b <= num; i++) {
                ans[i + b] = ans[i] + 1;
            }
            b <<= 1; // b = 2b
        }
        return ans;
    }

    public int[] countBits3(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i)
            ans[i] = popCount(i);
        return ans;
    }

    private int popCount(int x) {
        int count;
        for (count = 0; x != 0; ++count)
            x &= x - 1; //zeroing out the least significant nonzero bit
        return count;
    }

    public int[] countBits2(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            int n = i, cnt = 0;
            while (n > 0) {
                cnt++;
                int x = (int) (Math.log(n) / Math.log(2));
                n = n - (int) Math.pow(2, x);
            }
            dp[i] = cnt;
        }
        return dp;
    }

    /**
     * 输入: 2
     * 输出: [0,1,1]
     */
    @Test
    public void test1() {
        int[] ret = countBits(2);
        assertThat(ret, is(new int[]{0, 1, 1}));
    }

    /**
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     */
    @Test
    public void test2() {
        int[] ret = countBits(5);
        assertThat(ret, is(new int[]{0, 1, 1, 2, 1, 2}));
    }
}
