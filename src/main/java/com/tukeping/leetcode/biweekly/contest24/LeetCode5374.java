package com.tukeping.leetcode.biweekly.contest24;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/18
 **/
public class LeetCode5374 {

    public String getHappyString(int n, int k) {
        int[] po = new int[20];
        po[0] = 1;

        for (int i = 1; i <= n; ++i) po[i] = po[i - 1] * 2;
        if (k > po[n - 1] * 3) return "";

        int l = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= n; ++i) {
            for (int j = 'a'; j <= 'c'; ++j) {
                if (l != j) {
                    if (k > po[n - i]) {
                        k -= po[n - i];
                    } else {
                        ans.append((char) j);
                        l = j;
                        break;
                    }
                }
            }
        }
        return ans.toString();
    }

    public String getHappyString2(int n, int k) {
        char[] chars = {'a', 'b', 'c'};
        PriorityQueue<String> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
        helper(chars, n, k, 0, new StringBuilder(), maxHeap);
        return maxHeap.size() < k ? "" : maxHeap.peek();
    }

    private void helper(char[] chars, int n, int k, int size, StringBuilder track, PriorityQueue<String> maxHeap) {
        if (maxHeap.size() == k) {
            return;
        }
        if (size == n) {
            maxHeap.add(track.toString());
            return;
        }
        for (char c : chars) {
            int trackLen = track.length();
            if (trackLen > 0 && track.charAt(trackLen - 1) == c) continue;
            track.append(c);
            helper(chars, n, k, size + 1, track, maxHeap);
            track.deleteCharAt(trackLen);
            if (maxHeap.size() == k) return;
        }
    }

    @Test
    public void test1() {
        String res = getHappyString(1, 3);
        assertThat(res, is("c"));
    }

    @Test
    public void test2() {
        String res = getHappyString(1, 4);
        assertThat(res, is(""));
    }

    @Test
    public void test3() {
        String res = getHappyString(3, 9);
        assertThat(res, is("cab"));
    }

    @Test
    public void test4() {
        String res = getHappyString(2, 7);
        assertThat(res, is(""));
    }

    @Test
    public void test5() {
        String res = getHappyString(10, 100);
        assertThat(res, is("abacbabacb"));
    }
}
