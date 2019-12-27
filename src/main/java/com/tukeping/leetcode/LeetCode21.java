package com.tukeping.leetcode;

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

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tukeping
 * @since 2019/12/27
 */
public class LeetCode21 {

    @Test
    public void test() {
        // 1->2->4, 1->3->4
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(4);

        ListNode n2 = new ListNode(1);
        n2.next = new ListNode(3);
        n2.next.next = new ListNode(4);

        ListNode ret = mergeTwoListsV1(n1, n2);

        // 1->1->2->3->4->4

        Assert.assertEquals(1, ret.val);
        Assert.assertEquals(1, ret.next.val);
        Assert.assertEquals(2, ret.next.next.val);
        Assert.assertEquals(3, ret.next.next.next.val);
        Assert.assertEquals(4, ret.next.next.next.next.val);
        Assert.assertEquals(4, ret.next.next.next.next.next.val);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoListsV1(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);

        ListNode prev = preHead;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        prev.next = (l1 == null) ? l2 : l1;

        return preHead.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        List<Integer> list = new ArrayList<>();

        ListNode last1 = l1;
        while (last1 != null) {
            list.add(last1.val);
            last1 = last1.next;
        }

        ListNode last2 = l2;
        while (last2 != null) {
            list.add(last2.val);
            last2 = last2.next;
        }

        if (list.isEmpty()) {
            return null;
        }

        Collections.sort(list);

        ListNode ret = new ListNode(list.get(0));
        ListNode retLast = ret;
        for (int i = 1; i < list.size(); i++) {
            retLast.next = new ListNode(list.get(i));
            retLast = retLast.next;
        }

        return ret;
    }
}
