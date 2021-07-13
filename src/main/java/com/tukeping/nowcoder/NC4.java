package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.ListNode;

/**
 * @author tukeping
 * @date 2021/7/4
 **/
public class NC4 {

    public boolean hasCycle(ListNode head) {
        // 3 -> 2 -> 0 -> -4 -> 2
        //           S(F)

        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        if (fast == null) {
            return false;
        }

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
