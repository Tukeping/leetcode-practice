package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=127 lang=java
 *
 * [127] 单词接龙
 *
 * https://leetcode-cn.com/problems/word-ladder/description/
 *
 * algorithms
 * Medium (38.49%)
 * Likes:    208
 * Dislikes: 0
 * Total Accepted:    21.5K
 * Total Submissions: 54.3K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord
 * 的最短转换序列的长度。转换需遵循如下规则：
 *
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 *
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * ⁠    返回它的长度 5。
 *
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * breadth-first-search
 *
 * amazon | facebook | linkedin | snapchat | yelp
 *
 * frequency 5
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode127 {

    /**
     * 40/40 cases passed (101 ms)
     * Your runtime beats 42.71 % of java submissions
     * Your memory usage beats 8.09 % of java submissions (48.4 MB)
     */

    static class Pair {
        public String key;
        public Integer value;

        public Pair(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public static Pair of(String key, Integer value) {
            return new Pair(key, value);
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // every word length
        int L = beginWord.length();

        // pre Processing
        Map<String, List<String>> dict = new HashMap<>();
        wordList.forEach(word -> {
            for (int i = 0; i < L; i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                List<String> list = dict.getOrDefault(newWord, new ArrayList<>());
                list.add(word);
                dict.put(newWord, list);
            }
        });

        // BFS
        LinkedList<Pair> Q = new LinkedList<>();
        // start node
        Q.add(Pair.of(beginWord, 1));
        // visited map
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, Boolean.TRUE);

        while (!Q.isEmpty()) {
            Pair node = Q.remove();
            String word = node.key;
            int level = node.value;
            for (int i = 0; i < L; i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                List<String> adjacentWordList = dict.getOrDefault(newWord, new ArrayList<>());
                for (String adjacentWord : adjacentWordList) {
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, Boolean.TRUE);
                        Q.add(Pair.of(adjacentWord, level + 1));
                    }
                }
            }

        }

        return 0;
    }

    /**
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * 输出: 5
     *
     * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog" ,
     *                      "hit" -> "hot" -> "lot" -> "log" -> "cog"
     *      返回它的长度 5。
     */
    @Test
    public void test1() {
        int n = ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        assertThat(n, is(5));
    }

    /**
     * 输入:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     *
     * 输出: 0
     *
     * 解释: endWord "cog" 不在字典中，所以无法进行转换。
     */
    @Test
    public void test2() {
        int n = ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"));
        assertThat(n, is(0));
    }
}
