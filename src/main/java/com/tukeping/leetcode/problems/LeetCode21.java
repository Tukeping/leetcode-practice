package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=21 lang=java
 *
 * [21] 合并两个有序链表
 *
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
 *
 * algorithms
 * Easy (58.76%)
 * Likes:    769
 * Dislikes: 0
 * Total Accepted:    156.1K
 * Total Submissions: 265.5K
 * Testcase Example:  '[1,2,4]\n[1,3,4]'
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 *
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * linked-list
 *
 * amazon | apple | linkedin | microsoft
 *
 * frequency 5
 *
 * @author tukeping
 * @since 2019/12/27
 */
public class LeetCode21 {

    public ListNode mergeTwoListsV4(ListNode l1, ListNode l2) {
        ListNode guard = new ListNode(-1);
        ListNode cur = guard;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            } else if (l2 == null) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                if (l1.val <= l2.val) {
                    cur.next = l1;
                    cur = cur.next;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    cur = cur.next;
                    l2 = l2.next;
                }
            }
        }
        return guard.next;
    }

    public ListNode mergeTwoListsV3(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        ListNode guard = new ListNode(-1);
        ListNode cur = guard;
        guard.next = cur;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            } else if (l1 == null) {
                cur.next = l2;
                l2 = null;
            } else if (l2 == null) {
                cur.next = l1;
                l1 = null;
            }
        }
        return guard.next;
    }

    public ListNode mergeTwoListsV2(ListNode l1, ListNode l2) {
        ListNode guard = new ListNode(-1);
        ListNode cur = guard;
        guard.next = cur;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 == null) {
            cur.next = l2;
        } else if (l2 == null) {
            cur.next = l1;
        }

        return guard.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1);
        ListNode cur = sentry;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = (l1 == null) ? l2 : l1;

        return sentry.next;
    }

    @Test
    public void test() {
        ListNode l1 = ListNodeHelper.build(new int[]{1, 2, 4}); // 1 -> 2 -> 4
        ListNode l2 = ListNodeHelper.build(new int[]{1, 3, 4}); // 1 -> 3 -> 4
        ListNode l3 = mergeTwoLists(l1, l2);

        // 1 -> 1 -> 2 -> 3 -> 4 -> 4
        ListNodeHelper.check(l3, new int[]{1, 1, 2, 3, 4, 4});
    }
}
