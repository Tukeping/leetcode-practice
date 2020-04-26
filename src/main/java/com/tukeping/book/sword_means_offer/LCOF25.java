package com.tukeping.book.sword_means_offer;

/*
 * 面试题25. 合并两个排序的链表
 *
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：
 *
 * 0 <= 链表长度 <= 1000
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF25 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        return dummy.next;
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
