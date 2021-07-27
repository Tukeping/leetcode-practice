package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=387 lang=java
 *
 * [387] 字符串中的第一个唯一字符
 *
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/description/
 *
 * algorithms
 * Easy (42.20%)
 * Likes:    180
 * Dislikes: 0
 * Total Accepted:    61.1K
 * Total Submissions: 141K
 * Testcase Example:  '"leetcode"'
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *
 *
 *
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 *
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/20
 **/
public class LeetCode387 {

    public int firstUniqCharV2(String s) {
        int[] letters = new int[26];
        for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (letters[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /*
     * 104/104 cases passed (63 ms)
     * Your runtime beats 22.24 % of java submissions
     * Your memory usage beats 5.11 % of java submissions (42.1 MB)
     */

    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) return -1;
        if (s.length() == 1) return 0;

        Map<Character, Boolean> map = new HashMap<>(s.length());
        int i;

        for (i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), Boolean.TRUE);
            } else {
                map.put(s.charAt(i), Boolean.FALSE);
            }
        }

        for (i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)) && Boolean.FALSE.equals(map.get(s.charAt(i)))) {
                break;
            }
        }

        if (i == s.length())
            i = -1;

        return i;
    }

    /**
     * s = "leetcode"
     * 返回 0.
     *
     * s = "loveleetcode",
     * 返回 2.
     */
    @Test
    public void test1() {
        int i = firstUniqChar("leetcode");
        assertThat(i, is(0));

        i = firstUniqChar("loveleetcode");
        assertThat(i, is(2));
    }
}
