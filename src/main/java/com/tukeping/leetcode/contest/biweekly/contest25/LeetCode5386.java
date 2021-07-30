package com.tukeping.leetcode.contest.biweekly.contest25;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/5/2
 **/
public class LeetCode5386 {

    public boolean checkIfCanBreak(String s1, String s2) {
        int n = s1.length();

        int[] cnt1 = new int[128];
        for (char c : s1.toCharArray()) cnt1[c]++;

        int[] cnt2 = new int[128];
        for (char c : s2.toCharArray()) cnt2[c]++;

        int i = 0, j = 0, cnt = 0;

        boolean s1w = true;

        int[] cnt1Clone = new int[128];
        System.arraycopy(cnt1, 0, cnt1Clone, 0, 128);
        int[] cnt2Clone = new int[128];
        System.arraycopy(cnt2, 0, cnt2Clone, 0, 128);

        while (cnt < n && i < 128 && j < 128) {
            while (i < 128 && cnt1Clone[i] == 0) i++;
            while (j < 128 && cnt2Clone[j] == 0) j++;
            if (i < j) {
                s1w = false;
                break;
            } else {
                cnt1Clone[i]--;
                cnt2Clone[j]--;
                if (cnt1Clone[i] == 0) i++;
                if (cnt2Clone[j] == 0) j++;
                cnt++;
            }
        }

        if (s1w) return true;

        i = 0;
        j = 0;
        cnt = 0;

        boolean s2w = true;

        while (cnt < n && i < 128 && j < 128) {
            while (i < 128 && cnt1[i] == 0) i++;
            while (j < 128 && cnt2[j] == 0) j++;
            if (j < i) {
                s2w = false;
                break;
            } else {
                cnt1[i]--;
                cnt2[j]--;
                if (cnt1[i] == 0) i++;
                if (cnt2[j] == 0) j++;
                cnt++;
            }
        }
        return s2w;
    }

    /**
     * 输入：s1 = "abe", s2 = "acd"
     * 输出：false
     * 解释：s1="abe" 的所有排列包括："abe"，"aeb"，"bae"，"bea"，"eab" 和 "eba" ，s2="acd" 的所有排列包括："acd"，"adc"，"cad"，"cda"，"dac" 和 "dca"。然而没有任何 s1 的排列可以打破 s2 的排列。也没有 s2 的排列能打破 s1 的排列。
     */
    @Test
    public void test1() {
        boolean b = checkIfCanBreak("abe", "acd");
        assertFalse(b);
    }

    /**
     * 输入：s1 = "abc", s2 = "xya"
     * 输出：true
     * 解释："ayx" 是 s2="xya" 的一个排列，"abc" 是字符串 s1="abc" 的一个排列，且 "ayx" 可以打破 "abc" 。
     */
    @Test
    public void test2() {
        boolean b = checkIfCanBreak("abc", "xya");
        assertTrue(b);
    }

    /**
     * 输入：s1 = "leetcodee", s2 = "interview"
     * 输出：true
     */
    @Test
    public void test3() {
        boolean b = checkIfCanBreak("leetcodee", "interview");
        assertTrue(b);
    }
}
