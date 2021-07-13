package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=6 lang=java
 *
 * [6] Z 字形变换
 *
 * https://leetcode-cn.com/problems/zigzag-conversion/description/
 *
 * algorithms
 * Medium (45.94%)
 * Likes:    580
 * Dislikes: 0
 * Total Accepted:    99.5K
 * Total Submissions: 212.5K
 * Testcase Example:  '"PAYPALISHIRING"\n3'
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 *
 *
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 *
 *
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * string
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/2/28
 **/
public class LeetCode6 {

    public String convert(String s, int numRows) {
        // corner case
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        boolean flip = false;
        int curRowNum = 0;

        for (int i = 0; i < s.length(); i++) {
            rows.get(curRowNum).append(s.charAt(i));
            if (curRowNum == 0 || curRowNum == numRows - 1) flip = !flip;
            curRowNum += flip ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }

        return res.toString();
    }

    /**
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * 解释:
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     */
    @Test
    public void test1() {
        String res = convert("LEETCODEISHIRING", 3);
        assertThat(res, is("LCIRETOESIIGEDHN"));
    }

    /**
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     */
    @Test
    public void test2() {
        String res = convert("LEETCODEISHIRING", 4);
        assertThat(res, is("LDREOEIIECIHNTSG"));
    }
}
