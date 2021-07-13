package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import java.util.Stack;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC40 {

    /**
     *
     * @param head1 ListNode类
     * @param head2 ListNode类
     * @return ListNode类
     */
    public ListNode addInList(ListNode head1, ListNode head2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (head1 != null) {
            s1.push(head1.val);
            head1 = head1.next;
        }

        while (head2 != null) {
            s2.push(head2.val);
            head2 = head2.next;
        }

        ListNode guard = new ListNode(-1);
        ListNode head = guard;
        int x = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int a;
            int b;
            if (s2.isEmpty()) {
                a = s1.pop();
                b = 0;
            } else if (s1.isEmpty()) {
                a = 0;
                b = s2.pop();
            } else {
                a = s1.pop();
                b = s2.pop();
            }
            int sum = a + b + x;
            int val;
            x = 0;
            if (sum >= 10) {
                x = 1;
                val = sum % 10;
            } else {
                val = sum;
            }
            head.next = new ListNode(val);
            head = head.next;
        }

        // reverse linked list
        ListNode prev = null;
        ListNode cur = guard.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        guard.next = prev;

        if (x == 1) {
            ListNode node = new ListNode(1);
            node.next = guard.next;
            guard.next = node;
        }
        return guard.next;
    }

    @Test
    public void test() {
        ListNode head1 = ListNodeHelper.as(9, 3, 7);
        ListNode head2 = ListNodeHelper.as(6, 3);
        ListNode actual = addInList(head1, head2);
        ListNodeHelper.assertThat(actual, 1, 0, 0, 0);
    }

    @Test
    public void test2() {
        ListNode head1 = ListNodeHelper.as(5, 9, 7, 5, 7, 1, 2, 6, 4, 2, 7, 8, 9, 6, 1, 6, 6, 1, 1, 4, 2, 9, 5, 5, 0, 4, 6, 3, 0, 4, 3, 5, 6, 7, 0, 5, 5, 4, 4, 0);
        ListNode head2 = ListNodeHelper.as(1, 3, 2, 5, 0, 6, 0, 2, 1, 4, 3, 9, 3, 0, 9, 9, 0, 3, 1, 6, 5, 7, 8, 6, 2, 3, 8, 5, 0, 9, 7, 9, 4, 5, 9, 9, 4, 9, 3, 6);
        ListNode actual = addInList(head1, head2);
        ListNodeHelper.assertThat(actual, 7, 3, 0, 0, 7, 7, 2, 8, 5, 7, 1, 8, 2, 7, 1, 5, 6, 4, 3, 0, 8, 7, 4, 1, 2, 8, 4, 8, 1, 4, 1, 5, 1, 3, 0, 5, 0, 3, 7, 6);
    }
}
