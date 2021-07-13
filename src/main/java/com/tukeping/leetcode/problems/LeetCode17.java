package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=17 lang=java
 *
 * [17] 电话号码的字母组合
 *
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * algorithms
 * Medium (51.70%)
 * Likes:    612
 * Dislikes: 0
 * Total Accepted:    88.4K
 * Total Submissions: 167.3K
 * Testcase Example:  '"23"'
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 *
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * string | backtracking
 *
 * amazon | dropbox | facebook | google | uber
 *
 * @author tukeping
 * @date 2020/3/16
 **/
public class LeetCode17 {

    private Map<Integer, char[]> map;

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return Collections.emptyList();

        map = new HashMap<>(8);
        map.put(2, new char[]{'a', 'b', 'c'});
        map.put(3, new char[]{'d', 'e', 'f'});
        map.put(4, new char[]{'g', 'h', 'i'});
        map.put(5, new char[]{'j', 'k', 'l'});
        map.put(6, new char[]{'m', 'n', 'o'});
        map.put(7, new char[]{'p', 'q', 'r', 's'});
        map.put(8, new char[]{'t', 'u', 'v'});
        map.put(9, new char[]{'w', 'x', 'y', 'z'});

        char[] chars = digits.toCharArray();
        int len = chars.length;

        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = chars[i] - 48;
        }

        List<String> res = new ArrayList<>();
        letterCombinations(nums, 0, new boolean[len][4], new ArrayList<>(), res);
        return res;
    }

    private void letterCombinations(int[] nums, int start, boolean[][] used, List<Character> track, List<String> res) {
        if (track.size() == nums.length) {
            StringBuilder s = new StringBuilder();
            for (Character c : track) s.append(c);
            res.add(s.toString());
        }
        for (int i = start; i < nums.length; i++) {
            char[] chars = map.get(nums[i]);
            for (int k = 0; k < chars.length; k++) {
                if (used[i][k]) continue;
                used[i][k] = true;
                track.add(chars[k]);
                letterCombinations(nums, i + 1, used, track, res);
                track.remove(track.size() - 1);
                used[i][k] = false;
            }
        }
    }

    /**
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    @Test
    public void test1() {
        List<String> res = letterCombinations("23");
        assertThat(res, containsInAnyOrder("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
    }

    /**
     * 输入: "22"
     * 输出: ["aa","ab","ac","ba","bb","bc","ca","cb","cc"]
     */
    @Test
    public void test2() {
        List<String> res = letterCombinations("22");
        assertThat(res, containsInAnyOrder("aa", "ab", "ac", "ba", "bb", "bc", "ca", "cb", "cc"));
    }
}
