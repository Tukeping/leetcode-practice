package com.tukeping.leetcode.problems;

import com.tukeping.leetcode.structures.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tukeping
 * @date 2020/2/15
 **/
public class LeetCode1143 {

    public int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int n = list.size(), i = 0, j = n - 1;
        // 每次遍历只需要调整两个指针
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) break;
            list.get(j).next = list.get(i);
            j--;
        }
        // 防止指针循环
        list.get(i).next = null;
    }
}
