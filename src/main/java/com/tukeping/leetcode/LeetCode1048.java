package com.tukeping.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/13
 **/
public class LeetCode1048 {

    Map<Integer, List<String>> map = new HashMap<>();
    Map<String, int[]> cache = new HashMap<>();
    int ans = 1;

    public int longestStrChain(String[] words) {
        int mi = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
        for (String word : words) {
            int len = word.length();
            mi = Math.min(mi, len);
            mx = Math.max(mx, len);

            if (!map.containsKey(len)) {
                map.put(len, new ArrayList<>());
            }
            map.get(len).add(word);

            if (!cache.containsKey(word)) {
                int[] cnt = new int[26];
                for (int i = 0; i < len; i++) cnt[word.charAt(i) - 'a']++;
                cache.put(word, cnt);
            }
        }

        for (int i = mi; i < mx; i++) {
            if (map.containsKey(i)) {
                for (String word : map.get(i)) {
                    dfs(word, 1);
                }
            }
        }
        return ans;
    }

    private List<String> getNext(String word) {
        int n = word.length();
        List<String> nexts = map.get(n + 1);
        if (nexts == null || nexts.isEmpty()) return null;

        int[] cnt1 = cache.get(word);
        int dist = 1;
        List<String> ans = new ArrayList<>();
        for (String next : nexts) {
            int[] cnt2 = cache.get(next);
            boolean ok = true;
            dist = 1;
            for (int i = 0; i < 26; i++) {
                if (cnt1[i] - cnt2[i] != 0) dist--;
                if (dist < 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) ans.add(next);
        }
        return ans;
    }

    private void dfs(String word, int n) {
        if (word == null) return;

        List<String> nexts = getNext(word);
        if (nexts == null || nexts.isEmpty()) return;

        ans = Math.max(ans, n + 1);

        for (String nxt : nexts) {
            dfs(nxt, n + 1);
        }
    }

    /**
     * 输入：["a","b","ba","bca","bda","bdca"]
     * 输出：4
     * 解释：最长单词链之一为 "a","ba","bda","bdca"。
     */
    @Test
    public void test1() {
        int n = longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"});
        assertThat(n, is(4));
    }
}
