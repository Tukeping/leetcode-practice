package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * NC78 反转链表
 *
 * @author tukeping
 * @date 2021/7/3
 **/
public class NC78 {

    public ListNode ReverseList(ListNode head) {
        // null v1 -> v2 -> v3 -> null
        //      head
        // prev cur   next
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    @Test
    public void test() {
        ListNode head = ListNodeHelper.as(1, 2, 3, 4, 5);
        ReverseList(head);
        ListNodeHelper.assertThat(head, 5, 4, 3, 2, 1);
    }
}
