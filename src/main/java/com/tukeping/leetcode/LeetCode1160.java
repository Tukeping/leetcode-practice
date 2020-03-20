package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=1160 lang=java
 *
 * [1160] 拼写单词
 *
 * https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/description/
 *
 * algorithms
 * Easy (63.67%)
 * Likes:    42
 * Dislikes: 0
 * Total Accepted:    19.8K
 * Total Submissions: 28.6K
 * Testcase Example:  '["cat","bt","hat","tree"]\n"atach"'
 *
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 *
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 *
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 *
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 *
 *
 * 示例 2：
 *
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * 所有字符串中都仅包含小写英文字母
 *
 *
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/17
 **/
public class LeetCode1160 {

    public int countCharacters(String[] words, String chars) {
        if (null == words || words.length == 0) return 0;

        int[] table = new int[26];
        for (char c : chars.toCharArray()) {
            table[c - 'a']++;
        }

        int cnt = 0;
        for (String word : words) {
            if (canLearn(word, word.length(), table, 0))
                cnt += word.length();
        }
        return cnt;
    }

    /** backtracking **/
    private boolean canLearn(String word, int wordLen, int[] table, int idx) {
        if (wordLen == idx) return true;

        int charIndex = word.charAt(idx) - 'a';
        if (table[charIndex] <= 0) return false;

        table[charIndex]--;
        boolean ans = canLearn(word, wordLen, table, ++idx);
        table[charIndex]++;
        return ans;
    }

    public int countCharacters2(String[] words, String chars) {
        Map<Character, Integer> charsCnt = new HashMap<>();
        for (char c : chars.toCharArray()) {
            if (charsCnt.containsKey(c)) charsCnt.put(c, charsCnt.get(c) + 1);
            else charsCnt.put(c, 1);
        }
        Map<Character, Integer> wordCnt;
        int ans = 0;
        for (String word : words) {
            wordCnt = new HashMap<>();
            for (char c : word.toCharArray()) {
                if (wordCnt.containsKey(c)) wordCnt.put(c, wordCnt.get(c) + 1);
                else wordCnt.put(c, 1);
            }
            boolean isAns = true;
            for (char c : word.toCharArray())
                if (charsCnt.getOrDefault(c, 0) < wordCnt.getOrDefault(c, 0)) {
                    isAns = false;
                    break;
                }
            if (isAns) ans += word.length();
        }
        return ans;
    }

    public int countCharacters1(String[] words, String chars) {
        char[] table = chars.toCharArray();
        int tableLen = table.length, wordLen, sum = 0, i;
        boolean[] used = new boolean[tableLen];
        for (String word : words) {
            Arrays.fill(used, false);
            wordLen = word.length();
            for (i = 0; i < wordLen; i++) {
                int idx = contains(table, tableLen, used, word.charAt(i));
                if (idx == -1) break;
                used[idx] = true;
            }
            if (i == wordLen) sum += wordLen;
        }
        return sum;
    }

    private int contains(char[] table, int tableLen, boolean[] used, char target) {
        for (int i = 0; i < tableLen; i++)
            if (!used[i] && table[i] == target)
                return i;
        return -1;
    }

    /**
     * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
     * 输出：6
     * 解释：
     * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
     */
    @Test
    public void test1() {
        int n = countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach");
        assertThat(n, is(6));
    }

    /**
     * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
     * 输出：10
     * 解释：
     * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
     */
    @Test
    public void test2() {
        int n = countCharacters(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr");
        assertThat(n, is(10));
    }
}
