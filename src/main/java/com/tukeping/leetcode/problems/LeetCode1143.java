package com.tukeping.leetcode.problems;

import com.tukeping.leetcode.structures.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tukeping
 * @date 2020/2/15
 **/
public class LeetCode1143 {

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
