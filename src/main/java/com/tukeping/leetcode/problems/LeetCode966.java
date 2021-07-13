package com.tukeping.leetcode.problems;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/13
 **/
public class LeetCode966 {

//    class Trie {
//
//        class Node {
//            Node[] links;
//            boolean end;
//
//            public Node() {
//                links = new Node[26 * 2];
//            }
//
//            public boolean contains(char c) {
//                return links[offset(c)] != null;
//            }
//
//            public boolean containsIgnoreCase(char c) {
//                return links[offset(c)] != null || links[offset((char) (c ^ 32))] != null;
//            }
//
//            private boolean isVowel(char c) {
//                c |= 32;
//                return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
//            }
//
//            private int offset(char c) {
//                return isLower(c) ? 26 + c - 'a' : c - 'A';
//            }
//
//            private boolean isLower(char c) {
//                return ((((int) c) >> 5) & 1) == 1;
//            }
//
//            public void put(char c, Node node) {
//                links[offset(c)] = node;
//            }
//
//            public Node get(char c) {
//                return links[offset(c)];
//            }
//
//            public void end() {
//                end = true;
//            }
//
//            public boolean isEnd() {
//                return end;
//            }
//        }
//
//        class Pair {
//            String ret;
//            int caps;
//            int edit;
//
//            public Pair(String ret, int caps, int edit) {
//                this.ret = ret;
//                this.caps = caps;
//                this.edit = edit;
//            }
//        }
//
//        Node root;
//        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
//
//        public Trie() {
//            root = new Node();
//        }
//
//        public void insert(String word) {
//            Node node = root;
//            for (char c : word.toCharArray()) {
//                if (!node.contains(c)) {
//                    node.put(c, new Node());
//                }
//                node = node.get(c);
//            }
//            node.end();
//        }
//
//        public String search(String word) {
//            Node node = root;
//            List<Pair> ret = new ArrayList<>();
//            search(node, word.toCharArray(), 0, 0, 0, 0, new StringBuilder(), ret);
//            ret.sort((o1, o2) -> {
//                if ((o1.caps + o1.edit) == (o2.caps + o2.edit)) {
//                    if (o1.edit == o2.edit) {
//                        return o1.caps - o2.caps;
//                    } else return o1.edit - o2.edit;
//                } else return (o1.caps + o1.edit) - (o2.caps + o2.edit);
//            });
//            if (ret.isEmpty()) return "";
//            String ans = ret.get(0).ret;
//            for (Pair pair : ret) {
//                if (pair.ret.equals(word)) return pair.ret;
//            }
//            return ans;
//        }
//
//        private void search(Node node, char[] word, int iTh, int jTh, int caps, int edit, StringBuilder track, List<Pair> ret) {
//            if (track.length() == word.length || node == null) {
//                if (node != null && node.isEnd()) {
//                    ret.add(new Pair(track.toString(), caps, edit));
//                }
//                return;
//            }
//            char c = word[iTh];
//            if (node.contains(c)) {
//                track.append(c);
//                search(node.get(c), word, iTh + 1, 0, caps, edit, track, ret);
//                track.deleteCharAt(track.length() - 1);
//            }
//            if (node.contains((char) (c ^ 32))) {
//                c ^= 32;
//                track.append(c);
//                search(node.get(c), word, iTh + 1, 0, caps + 1, edit, track, ret);
//                c ^= 32;
//                track.deleteCharAt(track.length() - 1);
//            }
//            if (node.isVowel(c)) {
//                for (int j = jTh; j < vowels.length; j++) {
//                    char vowel = vowels[j];
//                    if (vowel == c || !node.containsIgnoreCase(vowel)) continue;
//                    char[] chooseVowel = {vowel, (char) (vowel ^ 32)};
//                    for (char vowelCh : chooseVowel) {
//                        if (!node.contains(vowelCh)) continue;
//                        track.append(vowelCh);
//                        search(node.get(vowelCh), word, iTh + 1, j + 1, caps, edit + 1, track, ret);
//                        track.deleteCharAt(track.length() - 1);
//                    }
//                }
//            }
//        }
//    }
//
//    public String[] spellchecker(String[] wl, String[] qs) {
//        Trie trie = new Trie();
//
//        for (String word : wl) {
//            trie.insert(word);
//        }
//
//        String val = trie.search("HARE");
//        System.out.println("HARE => " + val); // hare
//
//        String[] ans = new String[qs.length];
//        for (int i = 0; i < qs.length; i++) {
//            ans[i] = trie.search(qs[i]);
//        }
//        return ans;
//    }


    Set<String> words = new HashSet<>();
    HashMap<String, String> wordCap = new HashMap<>();
    HashMap<String, String> wordVowel = new HashMap<>();

    public String[] spellchecker(String[] wl, String[] qs) {
        for (String word : wl) {
            words.add(word);
            wordCap.putIfAbsent(word.toLowerCase(), word);
            wordVowel.putIfAbsent(deVowel(word.toLowerCase()), word);
        }
        String[] ans = new String[qs.length];
        for (int i = 0; i < qs.length; i++) {
            ans[i] = solve(qs[i]);
        }
        return ans;
    }

    private String solve(String query) {
        if (words.contains(query)) {
            return query;
        }

        String queryCap = query.toLowerCase();
        if (wordCap.containsKey(queryCap)) {
            return wordCap.get(queryCap);
        }

        String queryVowel = deVowel(queryCap);
        if (wordVowel.containsKey(queryVowel)) {
            return wordVowel.get(queryVowel);
        }

        return "";
    }

    private String deVowel(String query) {
        StringBuilder ans = new StringBuilder();
        for (char c : query.toCharArray()) {
            if (isVowel(c)) ans.append("*");
            else ans.append(c);
        }
        return ans.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    /**
     * 输入：wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
     * 输出：["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
     */
    @Test
    public void test1() {
        String[] wl = {"KiTe", "kite", "hare", "Hare"};
        String[] qs = {"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"};
        String[] expect = {"kite", "KiTe", "KiTe", "Hare", "hare", "", "", "KiTe", "", "KiTe"};
        String[] ans = spellchecker(wl, qs);
        assertThat(ans, is(expect));
    }
}
