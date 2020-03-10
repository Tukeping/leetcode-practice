package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=19 lang=java
 *
 * [19] 删除链表的倒数第N个节点
 *
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
 *
 * algorithms
 * Medium (36.75%)
 * Likes:    716
 * Dislikes: 0
 * Total Accepted:    132K
 * Total Submissions: 348.8K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 *
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * linked-list | two-pointers
 *
 * @author tukeping
 * @date 2020/3/9
 **/
public class LeetCode19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode p1 = sentinel;
        ListNode p2 = sentinel;

        // why move n+1, p1 will move to NULL, p2 between p1 should be gap `n` nodes.
        for (int i = 0; i <= n; i++) p1 = p1.next;

        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p2.next = p2.next.next;

        return sentinel.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode pre = sentinel;

        for (int i = 0; i < len - n; i++)
            if (pre != null) pre = pre.next;

        if (pre != null)
            pre.next = pre.next.next;

        return sentinel.next;
    }

    /**
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     */
    @Test
    public void test1() {
        ListNode res = removeNthFromEnd(ListNodeHelper.build0(1, 2, 3, 4, 5), 2);
        ListNodeHelper.check0(res, 1, 2, 3, 5);
    }

    @Test
    public void test2() {
        ListNode res = removeNthFromEnd(ListNodeHelper.build0(1, 2), 1);
        ListNodeHelper.check0(res, 1);
    }
}
