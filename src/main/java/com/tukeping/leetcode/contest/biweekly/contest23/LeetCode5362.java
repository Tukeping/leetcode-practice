package com.tukeping.leetcode.contest.biweekly.contest23;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/4
 **/
public class LeetCode5362 {

    public boolean canConstruct(String s, int k) {
        int len = s.length();
        if (len == 1) return k == 1;
        if (len < k) return false;
        if (len == k) return true;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

//        map.forEach((c, v) -> {
//            System.out.println(c + " : " + v);
//        });

        int min = 0, max = 0;
        boolean b1 = false, b2 = false;
        for (Integer cnt : map.values()) {
            if (cnt % 2 == 0) {
                max += cnt / 2;
                b1 = true;
            } else {
                if (cnt == 1) {
                    max++;
                    min++;
                } else {
                    if (b1 && !b2) {
                        b2 = true;
                    } else {
                        max += cnt / 2 + 1;
                        min++;
                    }
                }
            }
        }

        max = Math.max(max, len);

//        System.out.println("min = " + min + ", max = " + max);
        return k >= min && k <= max;
    }

    /**
     * 输入：s = "annabelle", k = 2
     * 输出：true
     * 解释：可以用 s 中所有字符构造 2 个回文字符串。
     * 一些可行的构造方案包括："anna" + "elble"，"anbna" + "elle"，"anellena" + "b"
     */
    @Test
    public void test1() {
        assertTrue(canConstruct("annabelle", 2));
    }

    /**
     * 输入：s = "leetcode", k = 3
     * 输出：false
     * 解释：无法用 s 中所有字符构造 3 个回文串。
     */
    @Test
    public void test2() {
        assertFalse(canConstruct("leetcode", 3));
    }

    /**
     * 输入：s = "true", k = 4
     * 输出：true
     * 解释：唯一可行的方案是让 s 中每个字符单独构成一个字符串。
     */
    @Test
    public void test3() {
        assertTrue(canConstruct("true", 4));
    }

    /**
     * 输入：s = "yzyzyzyzyzyzyzy", k = 2
     * 输出：true
     * 解释：你只需要将所有的 z 放在一个字符串中，所有的 y 放在另一个字符串中。那么两个字符串都是回文串。
     */
    @Test
    public void test4() {
        assertTrue(canConstruct("yzyzyzyzyzyzyzy", 2));
    }

    /**
     * 输入：s = "cr", k = 7
     * 输出：false
     * 解释：我们没有足够的字符去构造 7 个回文串。
     */
    @Test
    public void test5() {
        assertFalse(canConstruct("cr", 7));
    }

    @Test
    public void test6() {
        assertTrue(canConstruct("messi", 3));
    }

    @Test
    public void test7() {
        assertTrue(canConstruct("aaa", 2));
    }

    @Test
    public void test8() {
        assertTrue(canConstruct("ibzkwaxxaggkiwjbeysz", 15));
    }
}
