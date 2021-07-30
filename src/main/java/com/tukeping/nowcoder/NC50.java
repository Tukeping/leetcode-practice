package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/29
 **/
public class NC50 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;

        ListNode guard = new ListNode(-1);
        guard.next = head;

        ListNode cur = guard;
        while (enoughK(cur, k)) {
            ListNode a = cur.next;
            ListNode b = a.next;
            ListNode c;
            int cnt = k - 1;
            while (cnt-- > 0) {
                c = b.next;
                b.next = a;
                a = b;
                b = c;
            }
            ListNode d = cur.next;
            d.next = b;
            cur.next = a;
            cur = d;
        }
        return guard.next;
    }

    private boolean enoughK(ListNode cur, int k) {
        int cnt = 0;
        for (ListNode i = cur.next; i != null; i = i.next) {
            cnt++;
            if (cnt == k) return true;
        }
        return false;
    }

    @Test
    public void test() {
        ListNode head = ListNodeHelper.as(1, 2, 3, 4, 5);
        ListNode actual = reverseKGroup(head, 2);
        ListNodeHelper.assertThat(actual, 2, 1, 4, 3, 5);
    }
}
