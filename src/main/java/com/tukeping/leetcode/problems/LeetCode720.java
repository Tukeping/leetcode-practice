package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=720 lang=java
 *
 * [720] 词典中最长的单词
 *
 * https://leetcode-cn.com/problems/longest-word-in-dictionary/description/
 *
 * algorithms
 * Easy (45.85%)
 * Likes:    66
 * Dislikes: 0
 * Total Accepted:    6.6K
 * Total Submissions: 14.4K
 * Testcase Example:  '["w","wo","wor","worl","world"]'
 *
 * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
 *
 * 若无答案，则返回空字符串。
 *
 * 示例 1:
 *
 * 输入:
 * words = ["w","wo","wor","worl", "world"]
 * 输出: "world"
 * 解释:
 * 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
 *
 * 示例 2:
 *
 * 输入:
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出: "apple"
 * 解释:
 * "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
 *
 * 注意:
 *
 * 所有输入的字符串都只包含小写字母。
 * words数组长度范围为[1,1000]。
 * words[i]的长度范围为[1,30]。
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * hash-table | trie
 *
 * @author tukeping
 * @date 2020/3/19
 **/
public class LeetCode720 {

    class Solution {
        public String longestWord(String[] words) {
            Trie trie = new Trie();
            int index = 0;
            for (String word : words) {
                trie.insert(word, ++index); //indexed by 1
            }
            trie.words = words;
            return trie.dfs();
        }

        class Node {
            char c;
            HashMap<Character, Node> children = new HashMap<>();
            int end;

            public Node(char c) {
                this.c = c;
            }
        }

        class Trie {
            Node root;
            String[] words;

            public Trie() {
                root = new Node('0');
            }

            public void insert(String word, int index) {
                Node cur = root;
                for (char c : word.toCharArray()) {
                    cur.children.putIfAbsent(c, new Node(c));
                    cur = cur.children.get(c);
                }
                cur.end = index;
            }

            public String dfs() {
                String ans = "";
                Stack<Node> stack = new Stack<>();
                stack.push(root);
                while (!stack.empty()) {
                    Node node = stack.pop();
                    if (node.end > 0 || node == root) {
                        if (node != root) {
                            String word = words[node.end - 1];
                            if (word.length() > ans.length() ||
                                    word.length() == ans.length() && word.compareTo(ans) < 0) {
                                ans = word;
                            }
                        }
                        for (Node nei : node.children.values()) {
                            stack.push(nei);
                        }
                    }
                }
                return ans;
            }
        }
    }

    class Trie {
        class TrieNode {
            // R links to node children
            private TrieNode[] links;
            private int R = 30;
            private boolean end;

            public TrieNode() {
                links = new TrieNode[R];
            }

            public boolean containsKey(char ch) {
                return links[ch - 'a'] != null;
            }

            public TrieNode get(char ch) {
                return links[ch - 'a'];
            }

            public void put(char ch, TrieNode node) {
                links[ch - 'a'] = node;
            }

            public boolean isEnd() {
                return end;
            }

            public void setEnd() {
                this.end = true;
            }
        }

        private TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public int insertNeedPrefixTree(String word) {
            TrieNode node = root;
            int len = word.length();
            for (int i = 0; i < len; i++) {
                char c = word.charAt(i);
                if (!node.containsKey(c) && i == len - 1) {
                    node.put(c, new TrieNode());
                } else if (!node.containsKey(c) && len > 1) {
                    return 0;
                }
                node = node.get(c);
            }
            node.setEnd();
            return len;
        }
    }

    public String longestWord(String[] words) {
        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() == o2.length()) return o1.compareTo(o2);
            else return o1.length() - o2.length();
        });
        Trie trie = new Trie();
        int longestWord = -1;
        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < words.length; i++) {
            int len = trie.insertNeedPrefixTree(words[i]);
            if (len > maxLen) {
                maxLen = len;
                longestWord = i;
            }
        }
        return longestWord == -1 ? "" : words[longestWord];
    }

    /**
     * 输入:
     * words = ["w","wo","wor","worl", "world"]
     * 输出: "world"
     * 解释:
     * 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
     */
    @Test
    public void test1() {
        String s = longestWord(new String[]{"w", "wo", "wor", "worl", "world"});
        assertThat(s, is("world"));
    }

    /**
     * 输入:
     * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
     * 输出: "apple"
     * 解释:
     * "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
     */
    @Test
    public void test2() {
        String s = longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"});
        assertThat(s, is("apple"));
    }
}
