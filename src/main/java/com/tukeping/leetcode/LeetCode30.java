package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=30 lang=java
 *
 * [30] 串联所有单词的子串
 *
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/description/
 *
 * algorithms
 * Hard (29.86%)
 * Likes:    243
 * Dislikes: 0
 * Total Accepted:    28.7K
 * Total Submissions: 95.3K
 * Testcase Example:  '"barfoothefoobarman"\n["foo","bar"]'
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 * ⁠ s = "barfoothefoobarman",
 * ⁠ words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 *
 * 示例 2：
 *
 * 输入：
 * ⁠ s = "wordgoodgoodgoodbestword",
 * ⁠ words = ["word","good","best","word"]
 * 输出：[]
 *
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * hash-table | two-pointers | string
 *
 * @author tukeping
 * @date 2020/4/7
 **/
public class LeetCode30 {

    public List<Integer> findSubstring(String s, String[] words) {
        int sLen = s.length();
        if (sLen == 0) return Collections.emptyList();
        int wordSize = words.length;
        if (wordSize == 0) return Collections.emptyList();
        int slidingWindowSize = 0;
        int minWordSize = Integer.MAX_VALUE;
        int maxWordSize = Integer.MIN_VALUE;

        Map<String, Integer> wordMap = new HashMap<>(wordSize, 1.0F);
        for (String word : words) {
            int wLen = word.length();
            slidingWindowSize += wLen;
            minWordSize = Math.min(minWordSize, wLen);
            maxWordSize = Math.max(maxWordSize, wLen);
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        if (sLen < slidingWindowSize) {
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();
        int usedWordCnt = 0;
        Map<String, Integer> wordLibs = new HashMap<>(wordMap);
        for (int wStart = 0, wEnd = slidingWindowSize; wEnd <= sLen; ) {
            if (usedWordCnt > 0) {
                wordLibs = new HashMap<>(wordMap);
                usedWordCnt = 0;
            }
            for (int i = wStart, j = i + minWordSize; j <= wEnd && j - i <= maxWordSize; ) {
                String pickWord = s.substring(i, j);
                if (wordLibs.containsKey(pickWord)) {
                    if (wordLibs.get(pickWord) <= 0)
                        break;
                    wordLibs.put(pickWord, wordLibs.get(pickWord) - 1);
                    usedWordCnt++;
                    i = j;
                    j = i + minWordSize;
                } else {
                    j++;
                }
            }
            // right answer => move whole window
            if (usedWordCnt == wordSize) {
                ans.add(wStart);
            }
            // wrong answer => move sliding window 1 step
            wStart++;
            wEnd++;
        }
        return ans;
    }

    /**
     * 输入：
     *   s = "barfoothefoobarman",
     *   words = ["foo","bar"]
     * 输出：[0,9]
     * 解释：
     * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
     * 输出的顺序不重要, [9,0] 也是有效答案。
     */
    @Test
    public void test1() {
        List<Integer> ans = findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
        assertThat(ans, containsInAnyOrder(0, 9));
    }

    /**
     * 输入：
     *   s = "wordgoodgoodgoodbestword",
     *   words = ["word","good","best","word"]
     * 输出：[]
     */
    @Test
    public void test2() {
        List<Integer> ans = findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"});
        assertThat(ans, is(Collections.emptyList()));
    }

    @Test
    public void test3() {
        List<Integer> ans = findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"});
        assertThat(ans, containsInAnyOrder(6, 9, 12));
    }

    @Test
    public void test4() {
        List<Integer> ans = findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
        assertThat(ans, containsInAnyOrder(8));
    }

    @Test
    public void test5() {
        List<Integer> ans = findSubstring("", new String[]{});
        assertThat(ans, is(Collections.emptyList()));
    }

    @Test
    public void test6() {
        List<Integer> ans = findSubstring("a", new String[]{});
        assertThat(ans, is(Collections.emptyList()));
    }

    @Test
    public void test7() {
        List<Integer> ans = findSubstring("aaaaaaaa", new String[]{"aa", "aa", "aa"});
        assertThat(ans, containsInAnyOrder(0, 1, 2));
    }
}
