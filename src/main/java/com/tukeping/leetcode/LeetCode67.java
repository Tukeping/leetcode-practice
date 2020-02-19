package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=67 lang=java
 *
 * [67] 二进制求和
 *
 * https://leetcode-cn.com/problems/add-binary/description/
 *
 * algorithms
 * Easy (51.63%)
 * Likes:    303
 * Dislikes: 0
 * Total Accepted:    62.4K
 * Total Submissions: 119.5K
 * Testcase Example:  '"11"\n"1"'
 *
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * math | string
 *
 * facebook
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode67 {

    /*
     * 294/294 cases passed (3 ms)
     * Your runtime beats 56.88 % of java submissions
     * Your memory usage beats 5.01 % of java submissions (42.3 MB)
     */

    public String addBinary(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int up = 0;

        StringBuilder res = new StringBuilder();
        // 从个位开始二进制相加, 0 = 48 , 1 = 49 (ascii)
        for (int p1 = aLen - 1, p2 = bLen - 1; p1 >= 0 || p2 >= 0; p1--, p2--) {
            if (p1 < 0) {
                if (b.charAt(p2) == 49) {
                    up = plusWithUp(res, "1", up);
                } else {
                    up = plusWithUp(res, "0", up);
                }
            } else if (p2 < 0) {
                if (a.charAt(p1) == 49) {
                    up = plusWithUp(res, "1", up);
                } else {
                    up = plusWithUp(res, "0", up);
                }
            } else {
                if (a.charAt(p1) == 49 && b.charAt(p2) == 49) {
                    up = plusWithUp(res, "2", up);
                } else if (a.charAt(p1) == 49 || b.charAt(p2) == 49) {
                    up = plusWithUp(res, "1", up);
                } else {
                    up = plusWithUp(res, "0", up);
                }
            }
        }

        if (up == 1) {
            res.insert(0, "1");
        }

        return res.toString();
    }

    private int plusWithUp(StringBuilder res, String cur, int up) {
        if (up == 1) {
            if (cur.equals("2")) {
                res.insert(0, "1");
            } else if (cur.equals("1")) {
                res.insert(0, "0");
                up = 1;
            } else if (cur.equals("0")) {
                res.insert(0, "1");
                up = 0;
            }
        } else {
            if (cur.equals("2")) {
                res.insert(0, "0");
                up = 1;
            } else {
                res.insert(0, cur);
            }
        }
        return up;
    }

    /**
     * 输入: a = "11", b = "1"
     * 输出: "100"
     */
    @Test
    public void test1() {
        String res = addBinary("11", "1");
        assertThat(res, is("100"));
    }

    /**
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     */
    @Test
    public void test2() {
        String res = addBinary("1010", "1011");
        assertThat(res, is("10101"));
    }

    @Test
    public void test3() {
        String res = addBinary("1111", "1111");
        assertThat(res, is("11110"));
    }
}
