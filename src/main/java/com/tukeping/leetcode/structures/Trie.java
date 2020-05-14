package com.tukeping.leetcode.structures;

/**
 * @author tukeping
 * @date 2020/3/19
 **/
public class Trie {

    class TrieNode {
        TrieNode[] links;
        int R = 26;
        boolean end;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public TrieNode(int R) {
            this.R = R;
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

        public void end() {
            this.end = true;
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public Trie(int R) {
        root = new TrieNode(R);
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.containsKey(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        node.end();
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.containsKey(c)) {
                node = node.get(c);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}
