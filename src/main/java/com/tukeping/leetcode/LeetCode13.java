package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=13 lang=java
 *
 * [13] 罗马数字转整数
 *
 * https://leetcode-cn.com/problems/roman-to-integer/description/
 *
 * algorithms
 * Easy (59.94%)
 * Likes:    712
 * Dislikes: 0
 * Total Accepted:    129.6K
 * Total Submissions: 216K
 * Testcase Example:  '"III"'
 *
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
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
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 *
 * 输入: "III"
 * 输出: 3
 *
 * 示例 2:
 *
 * 输入: "IV"
 * 输出: 4
 *
 * 示例 3:
 *
 * 输入: "IX"
 * 输出: 9
 *
 * 示例 4:
 *
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 *
 *
 * 示例 5:
 *
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tukeping
 * @since 2018/12/15
 **/
public class LeetCode13 {

    public int romanToInt(String s) {
        Map<String, Integer> data = new HashMap<>(13);
        data.put("I", 1);
        data.put("V", 5);
        data.put("X", 10);
        data.put("L", 50);
        data.put("C", 100);
        data.put("D", 500);
        data.put("M", 1000);
        data.put("IV", 4);
        data.put("IX", 9);
        data.put("XL", 40);
        data.put("XC", 90);
        data.put("CD", 400);
        data.put("CM", 900);

        Integer sum = 0;
        String current;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (('I' == c || 'X' == c || 'C' == c) && i + 1 < chars.length) {
                current = String.valueOf(c) + String.valueOf(chars[i + 1]);
                if ("IV".equals(current) ||
                        "IX".equals(current) ||
                        "XL".equals(current) ||
                        "XC".equals(current) ||
                        "CD".equals(current) ||
                        "CM".equals(current)) {
                    i = i + 1;
                } else {
                    current = String.valueOf(c);
                }
            } else {
                current = String.valueOf(c);
            }
            Integer value = data.getOrDefault(current, 0);
            sum += value;
        }

        return sum;
    }

    @Test
    public void test1() {
        int result = romanToInt("III");
        Assert.assertEquals(3, result);
    }

    @Test
    public void test2() {
        int result = romanToInt("IV");
        Assert.assertEquals(4, result);
    }

    @Test
    public void test3() {
        int result = romanToInt("IX");
        Assert.assertEquals(9, result);
    }

    @Test
    public void test4() {
        int result = romanToInt("LVIII");
        Assert.assertEquals(58, result);
    }

    @Test
    public void test5() {
        int result = romanToInt("MCMXCIV");
        Assert.assertEquals(1994, result);
    }
}
