package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=125 lang=java
 *
 * [125] 验证回文串
 *
 * https://leetcode-cn.com/problems/valid-palindrome/description/
 *
 * algorithms
 * Easy (41.72%)
 * Likes:    154
 * Dislikes: 0
 * Total Accepted:    77.8K
 * Total Submissions: 183.7K
 * Testcase Example:  '"A man, a plan, a canal: Panama"'
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 *
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 *
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * two-pointers | string
 *
 * facebook | microsoft | uber | zenefits
 *
 * frequency 5
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode125 {

    /*
     * 476/476 cases passed (7 ms)
     * Your runtime beats 47.72 % of java submissions
     * Your memory usage beats 5.06 % of java submissions (43.7 MB)
     */

    /** time: O(n) space: O(1) **/
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.isEmpty()) return true;

        s = s.toLowerCase();

        // ascii: 0~9 => 48~57 , a-z => 97~122
        int head = 0, tail = s.length() - 1;
        for (; head < tail; head++, tail--) {
            while (true) {
                if (head > tail) break;
                else if (legalChar(s, head)) break;
                else head++;
            }
            while (true) {
                if (head > tail) break;
                else if (legalChar(s, tail)) break;
                else tail--;
            }
            if (head >= tail) break;
            if (s.charAt(head) != s.charAt(tail))
                return false;
        }

        if (head == tail + 1) return true; // 可以认为是空字符串 或者 说都是无效的字符

        return (head == tail);
    }

    private boolean legalChar(String s, int p) {
        return s.charAt(p) >= 48 && s.charAt(p) <= 57 || s.charAt(p) >= 97 && s.charAt(p) <= 122;
    }

    /**
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     */
    @Test
    public void test1() {
        boolean b = isPalindrome("A man, a plan, a canal: Panama");
        assertTrue(b);
    }

    /**
     * 输入: "race a car"
     * 输出: false
     */
    @Test
    public void test2() {
        boolean b = isPalindrome("race a car");
        assertFalse(b);
    }

    @Test
    public void test3() {
        boolean b;
        b = isPalindrome(null);
        assertFalse(b);
        b = isPalindrome("");
        assertTrue(b);
    }

    @Test
    public void test4() {
        boolean b = isPalindrome(".,");
        assertTrue(b);
    }
}
