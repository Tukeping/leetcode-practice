package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=89 lang=java
 *
 * [89] 格雷编码
 *
 * https://leetcode-cn.com/problems/gray-code/description/
 *
 * algorithms
 * Medium (66.98%)
 * Likes:    138
 * Dislikes: 0
 * Total Accepted:    20.2K
 * Total Submissions: 29.9K
 * Testcase Example:  '2'
 *
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 *
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 *
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 *
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 *
 * 示例 2:
 *
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 * 给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 2^0 = 1。
 * 因此，当 n = 0 时，其格雷编码序列为 [0]。
 *
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * backtracking
 *
 * amazon
 *
 * @author tukeping
 * @date 2020/3/14
 **/
public class LeetCode89 {

    /**
     * 直接排列:
     *
     * 第零项: 二进制是0值的格雷码，
     * 循环 {
     *  第一项: 改变最右边的位元
     *  第二项: 改变右起第一个为1的位元的左边位元
     * }
     * 第三、四项方法同第一、二项，如此反复，即可排列出n个位元的格雷码。
     */
    public List<Integer> grayCode3(int n) {
        StringBuilder num = new StringBuilder();
        List<Integer> res = new ArrayList<>();
        res.add(0);
        double c = Math.pow(2, n);
        for (int i = 0; i < n; i++) num.append('0');
        while (res.size() < c) {
            if (num.charAt(num.length() - 1) == '0') {
                num.setCharAt(num.length() - 1, '1');
                res.add(Integer.parseInt(num.toString(), 2));
            } else {
                num.setCharAt(num.length() - 1, '0');
                res.add(Integer.parseInt(num.toString(), 2));
            }
            if (res.size() == c) break;
            int idx = num.lastIndexOf("1");
            if (num.charAt(idx - 1) == '0') {
                num.setCharAt(idx - 1, '1');
            } else {
                num.setCharAt(idx - 1, '0');
            }
            res.add(Integer.parseInt(num.toString(), 2));
        }
        return res;
    }

    /**
     * 镜射排列:
     *
     * n位元的格雷码可以从n-1位元的格雷码以上下镜射后加上新位元的方式快速的得到，如右图所示一般。
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(res.get(j) ^ (1 << i));
            }
        }
        return res;
    }

    /**
     * 二进制数转格雷码:
     *
     * （假设以二进制为0的值做为格雷码的0）
     * G：格雷码 B：二进制码 n：正在计算的位
     * 根据格雷码的定义可得：
     * G(n) = B(n+1) XOR B(n)
     * 即
     * G(n) = B(n+1) + B(n)
     * 自低位至高位运算即可，无需考虑进位。
     *
     * 格雷码转二进制数:
     *
     * 由于G(n) = B(n+1) + B(n)
     * 故而B(n) = B(n+1) - G(n)
     * 自高位至低位运算即可，无需考虑借位。
     */
    public List<Integer> grayCode1(int n) {
        int total = 1 << n;
        List<Integer> res = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            res.add(i >> 1 ^ i);
        }
        return res;
    }

    /**
     * 输入: 2
     * 输出: [0,1,3,2]
     * 解释:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     *
     * 对于给定的 n，其格雷编码序列并不唯一。
     * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
     *
     * 00 - 0
     * 10 - 2
     * 11 - 3
     * 01 - 1
     */
    @Test
    public void test1() {
        assertThat(grayCode(2), containsInAnyOrder(0, 1, 3, 2));
    }

    /**
     * 输入: 0
     * 输出: [0]
     * 解释: 我们定义格雷编码序列必须以 0 开头。
     *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     *      因此，当 n = 0 时，其格雷编码序列为 [0]。
     */
    @Test
    public void test2() {
        assertThat(grayCode(0), containsInAnyOrder(0));
    }
}
