package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tukeping
 * @date 2021/7/8
 **/
public class NC24 {

    /**
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteDuplicates(ListNode head) {
        Set<Integer> set = new HashSet<>();

        ListNode guard = new ListNode(-1);
        guard.next = head;

        Set<Integer> dup = new HashSet<>();

        ListNode prev = guard;
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur.val)) {
                prev.next = cur.next;
                dup.add(cur.val);
            } else {
                set.add(cur.val);
            }
            prev = cur;
            cur = cur.next;
        }

        prev = guard;
        cur = guard.next;

        while (cur != null) {
            if (dup.contains(cur.val)) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }

        return guard.next;
    }

    @Test
    public void test() {
        ListNode actual = deleteDuplicates(ListNodeHelper.as(1, 2, 3, 3, 4, 4, 5));
        ListNodeHelper.assertThat(actual, 1, 2, 5);
    }
}
