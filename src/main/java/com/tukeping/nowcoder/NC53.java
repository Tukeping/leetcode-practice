package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.ListNode;

/**
 * @author tukeping
 * @date 2021/7/8
 **/
public class NC53 {

    /**
     *
     * @param head ListNode类
     * @param n int整型
     * @return ListNode类
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0;
        ListNode tmp = head;
        while (tmp != null) {
            tmp = tmp.next;
            count++;
        }

        ListNode guard = new ListNode(-1);
        guard.next = head;
        ListNode prev = guard;
        ListNode cur = head;
        for (int i = 0; i < count - n; i++) {
            prev = cur;
            cur = cur.next;
        }

        prev.next = cur.next;

        return guard.next;
    }
}
