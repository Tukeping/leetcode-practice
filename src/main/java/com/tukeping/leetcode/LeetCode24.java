package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
 *
 * algorithms
 * Medium (63.35%)
 * Likes:    415
 * Dislikes: 0
 * Total Accepted:    70.8K
 * Total Submissions: 110.2K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * linked-list
 *
 * bloomberg | microsoft | uber
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode24 {

    public ListNode swapPairs(ListNode head) {
        return swapPairs(head, 2);
    }

    private ListNode swapPairs(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;



        return dummy.next;
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy, cur = head, p1 = null, p2 = null;
        while (cur != null) {
            for (int i = 0; i < 2; i++) {
                if (i == 0) p1 = cur;
                else p2 = cur;
                cur = cur == null ? null : cur.next;
            }
            if (p2 != null) {
                pre.next = p2;
                p1.next = p2.next;
                p2.next = p1;
                pre = p1;
            }
        }

        return dummy.next;
    }

    /**
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     */
    @Test
    public void test1() {
        ListNode res = swapPairs(ListNodeHelper.build0(1, 2, 3, 4));
        ListNodeHelper.check0(res, 2, 1, 4, 3);
    }

    @Test
    public void test2() {
        ListNode res = swapPairs(ListNodeHelper.build0(1));
        ListNodeHelper.check0(res, 1);
    }

    @Test
    public void test3() {
        ListNode res = swapPairs(ListNodeHelper.build0(1, 2, 3));
        ListNodeHelper.check0(res, 2, 1, 3);
    }
}
