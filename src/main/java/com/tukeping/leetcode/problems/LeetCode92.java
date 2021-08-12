package com.tukeping.leetcode.problems;

import com.tukeping.leetcode.structures.ListNode;

/**
 * @author tukeping
 * @date 2021/8/11
 **/
public class LeetCode92 {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;

        ListNode guard = new ListNode(-1);
        guard.next = head;

        ListNode start = head;
        ListNode prev = guard;
        while (left-- > 1) {
            prev = start;
            start = start.next;
        }

        ListNode end = head;
        while (right-- > 1) {
            end = end.next;
        }

        ListNode newPartHead = reverse(end.next, start, end);
        prev.next = newPartHead; // 1 -> 4

        return guard.next;
    }

    private ListNode reverse(ListNode prev, ListNode head, ListNode end) {
        //    ? -> 2 -> 3 -> 4 -> ?
        //    5 <- 2 <- 3 <- 4
        //                   cur
        // 前驱节点 最好用虚拟节点 万一 cur 节点是头节点
        // ListNode prev = null; // 开始头节点 不能为nul
        ListNode cur = head;
        ListNode next;
        while (cur != null && cur != end) { // 终止条件，现在不能是 cur != null
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        cur.next = prev;
        return cur;
    }
}
