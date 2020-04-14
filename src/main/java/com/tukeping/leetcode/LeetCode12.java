package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=12 lang=java
 *
 * [12] 整数转罗马数字
 *
 * https://leetcode-cn.com/problems/integer-to-roman/description/
 *
 * algorithms
 * Medium (62.00%)
 * Likes:    269
 * Dislikes: 0
 * Total Accepted:    66.1K
 * Total Submissions: 105.6K
 * Testcase Example:  '3'
 *
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V +
 * II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数
 * 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 *
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: "III"
 *
 * 示例 2:
 *
 * 输入: 4
 * 输出: "IV"
 *
 * 示例 3:
 *
 * 输入: 9
 * 输出: "IX"
 *
 * 示例 4:
 *
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 *
 * 示例 5:
 *
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * math | string
 *
 * twitter
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode12 {

    /**
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */
    public String intToRoman(int num) {
        // 1 <= num <= 3999
        int[] nums = new int[4];
        int idx = 0;
        while (num > 0) {
            nums[idx++] = num % 10;
            num /= 10;
        }

        StringBuilder ans = new StringBuilder();
        if (nums[0] != 0) {
            helper(nums, 0, ans, "IX", "V", "IV", "I");
        }
        if (nums[1] != 0) {
            helper(nums, 1, ans, "XC", "L", "XL", "X");
        }
        if (nums[2] != 0) {
            helper(nums, 2, ans, "CM", "D", "CD", "C");
        }
        if (nums[3] != 0) {
            helper(nums, 3, ans, "", "", "", "M");
        }
        return ans.toString();
    }

    private void helper(int[] nums, int idx, StringBuilder ans, String nine, String five, String four, String one) {
        if (nums[idx] == 9) {
            ans.insert(0, nine);
        } else if (nums[idx] == 5) {
            ans.insert(0, five);
        } else if (nums[idx] == 4) {
            ans.insert(0, four);
        } else if (nums[idx] < 4) {
            for (int i = 0; i < nums[idx]; i++) {
                ans.insert(0, one);
            }
        } else { // 5 < nums[idx] < 9
            ans.insert(0, five);
            for (int i = 0; i < nums[idx] - 5; i++) {
                ans.insert(1, one);
            }
        }
    }

    /**
     * 输入: 3
     * 输出: "III"
     */
    @Test
    public void test1() {
        String s = intToRoman(3);
        assertThat(s, is("III"));
    }

    /**
     * 输入: 4
     * 输出: "IV"
     */
    @Test
    public void test2() {
        String s = intToRoman(4);
        assertThat(s, is("IV"));
    }

    /**
     * 输入: 9
     * 输出: "IX"
     */
    @Test
    public void test3() {
        String s = intToRoman(9);
        assertThat(s, is("IX"));
    }

    /**
     * 输入: 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     */
    @Test
    public void test4() {
        String s = intToRoman(58);
        assertThat(s, is("LVIII"));
    }

    /**
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */
    @Test
    public void test5() {
        String s = intToRoman(1994);
        assertThat(s, is("MCMXCIV"));
    }
}
