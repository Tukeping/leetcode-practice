package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=203 lang=java
 *
 * [203] 移除链表元素
 *
 * https://leetcode-cn.com/problems/remove-linked-list-elements/description/
 *
 * algorithms
 * Easy (43.18%)
 * Likes:    350
 * Dislikes: 0
 * Total Accepted:    63K
 * Total Submissions: 142K
 * Testcase Example:  '[1,2,6,3,4,5,6]\n6'
 *
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * linked-list
 *
 * @author tukeping
 * @date 2020/3/9
 **/
public class LeetCode203 {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;

        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode pre = sentinel;
        ListNode cur = head;

        while (cur != null) {
            if (cur.val == val) pre.next = cur.next;
            else pre = cur;
            cur = cur.next;
        }

        return sentinel.next;
    }

    /**
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     */
    @Test
    public void test1() {
        ListNode head = ListNodeHelper.as(1, 2, 6, 3, 4, 5, 6);
        ListNode res = removeElements(head, 6);
        ListNodeHelper.assertThat(res, 1, 2, 3, 4, 5);
    }

    @Test
    public void test2() {
        ListNode head = ListNodeHelper.as(1);
        ListNode res = removeElements(head, 1);
        assertNull(res);
    }
}
