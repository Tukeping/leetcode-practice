package com.tukeping.cp.leetcode.contest.weekly.contest184;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/12
 **/
public class LeetCode5380 {

    public List<String> stringMatching(String[] words) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j && words[j].contains(words[i])) {
                    ans.add(words[i]);
                    break;
                }
            }
        }
        return ans;
    }

    public List<String> stringMatching2(String[] words) {
        int len = words.length;

        int[] wLens = new int[len];
        for (int i = 0; i < len; i++)
            wLens[i] = words[i].length();

        boolean[][] memo = new boolean[len][len];

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int wLen = wLens[i];
            for (int j = 0; j < len; j++) {
                if (i == j) continue;
                if (wLens[j] >= wLen) {
                    if (memo[i][j]) {
                        ans.add(words[i]);
                    } else if (words[j].contains(words[i])) {
                        memo[i][j] = true;
                        if (!ans.contains(words[i])) {
                            ans.add(words[i]);
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 输入：words = ["mass","as","hero","superhero"]
     * 输出：["as","hero"]
     * 解释："as" 是 "mass" 的子字符串，"hero" 是 "superhero" 的子字符串。
     * ["hero","as"] 也是有效的答案。
     */
    @Test
    public void test1() {
        List<String> ans = stringMatching(new String[]{"mass", "as", "hero", "superhero"});
        assertThat(ans, containsInAnyOrder("as", "hero"));
    }

    /**
     * 输入：words = ["leetcode","et","code"]
     * 输出：["et","code"]
     * 解释："et" 和 "code" 都是 "leetcode" 的子字符串。
     */
    @Test
    public void test2() {
        List<String> ans = stringMatching(new String[]{"leetcode", "et", "code"});
        assertThat(ans, containsInAnyOrder("et", "code"));
    }

    /**
     * 输入：words = ["blue","green","bu"]
     * 输出：[]
     */
    @Test
    public void test3() {
        List<String> ans = stringMatching(new String[]{"blue", "green", "bu"});
        assertThat(ans, is(new ArrayList<>()));
    }
}
