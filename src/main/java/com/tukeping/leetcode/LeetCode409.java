package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=409 lang=java
 *
 * [409] 最长回文串
 *
 * https://leetcode-cn.com/problems/longest-palindrome/description/
 *
 * algorithms
 * Easy (52.35%)
 * Likes:    125
 * Dislikes: 0
 * Total Accepted:    24.9K
 * Total Submissions: 46.6K
 * Testcase Example:  '"abccccdd"'
 *
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 *
 * 输入:
 * "abccccdd"
 *
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * hash-table
 *
 * google
 *
 * @author tukeping
 * @date 2020/3/19
 **/
public class LeetCode409 {

    public int longestPalindrome4(String s) {
        int[] arr = new int[128];
        for (char c : s.toCharArray())
            arr[c]++;
        int count = 0;
        for (int i : arr)
            count += (i % 2);
        return count == 0 ? s.length() : (s.length() - count + 1);
    }

    /** Best 1ms, time: O(n) space: O(S), S = 128 (26*2=52) **/
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray())
            count[c]++;

        int ans = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0)
                ans++;
        }
        return ans;
    }

    public int longestPalindrome2(String s) {
        Map<Character, Integer> map = new HashMap<>(128);
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        boolean odd = false;
        int sum = 0;
        for (Integer value : map.values()) {
            if (value % 2 == 0) {
                sum += value;
            } else {
                if (value > 2) sum += value - 1;
                if (!odd) odd = true;
            }
        }
        if (odd) sum++;
        return sum;
    }

    /**
     * 输入:
     * "abccccdd"
     *
     * 输出:
     * 7
     *
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     */
    @Test
    public void test1() {
        int n = longestPalindrome("abccccdd");
        assertThat(n, is(7));
    }


    @Test
    public void test2() {
        int n = longestPalindrome("ccc");
        assertThat(n, is(3));
    }

    @Test
    public void test3() {
        int n = longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth");
        assertThat(n, is(983));
    }
}
