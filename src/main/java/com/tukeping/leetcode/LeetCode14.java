package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=14 lang=java
 *
 * [14] 最长公共前缀
 *
 * https://leetcode-cn.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (35.54%)
 * Likes:    811
 * Dislikes: 0
 * Total Accepted:    164K
 * Total Submissions: 460.9K
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 *
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 *
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @since 2018/12/15
 **/
public class LeetCode14 {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        String first = strs[0];
        char[] chars = first.toCharArray();

        int i;
        String prefix = "";
        boolean isBreak = false;

        for (i = 0; i < chars.length; i++) {
            prefix = prefix + String.valueOf(chars[i]);
            for (String str : strs) {
                if (!str.startsWith(prefix)) {
                    isBreak = true;
                    break;
                }
            }
            if (isBreak) {
                break;
            }
        }

        if (i == 0) {
            return "";
        } else {
            return first.substring(0, i);
        }
    }

    @Test
    public void test1() {
        String result = longestCommonPrefix(new String[]{"flower", "flow", "flight"});
        Assert.assertEquals("fl", result);
    }

    @Test
    public void test2() {
        String result = longestCommonPrefix(new String[]{"dog", "racecar", "car"});
        Assert.assertEquals("", result);
    }

    @Test
    public void test3() {
        String result = longestCommonPrefix(new String[]{});
        Assert.assertEquals("", result);
    }

    @Test
    public void test4() {
        String result = longestCommonPrefix(new String[]{"a"});
        Assert.assertEquals("a", result);
    }
}
