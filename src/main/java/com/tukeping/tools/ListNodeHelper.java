package com.tukeping.tools;

import com.tukeping.leetcode.structures.ListNode;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/17
 **/
public class ListNodeHelper {

    public static ListNode build0(int... a) {
        return build(a);
    }

    public static ListNode build(int[] a) {
        ListNode head = new ListNode(a[0]);
        ListNode cur = head;
        for (int i = 1; i < a.length; i++) {
            cur.next = new ListNode(a[i]);
            cur = cur.next;
        }
        return head;
    }

    public static ListNode buildCycle(int[] a, int c) {
        ListNode head = new ListNode(a[0]);
        ListNode cur = head;
        ListNode cycleNode = null;
        for (int i = 1; i < a.length; i++) {
            cur.next = new ListNode(a[i]);
            cur = cur.next;
            if (i == c) cycleNode = cur;
        }
        if (c == 0) cycleNode = head;
        cur.next = cycleNode; // now cur is tail
        return head;
    }

    public static void check(ListNode check, int[] a) {
        int i = 0;
        while (check != null) {
            assertThat(check.val, is(a[i++]));
            check = check.next;
        }
    }

    public static void check0(ListNode check, int... a) {
        check(check, a);
    }
}
