package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.ListNode;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC3 {

    public ListNode EntryNodeOfLoop(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) break;
        }

        if (fast == null || fast.next == null) return null;

        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
