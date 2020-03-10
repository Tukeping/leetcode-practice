package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=61 lang=java
 *
 * [61] 旋转链表
 *
 * https://leetcode-cn.com/problems/rotate-list/description/
 *
 * algorithms
 * Medium (39.54%)
 * Likes:    211
 * Dislikes: 0
 * Total Accepted:    47.2K
 * Total Submissions: 117.9K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 *
 *
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
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
public class LeetCode61 {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;

        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        k = k % len;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p1 = dummy;
        ListNode p2 = dummy;

        for (int i = 0; i <= k; i++) p1 = p1.next;

        ListNode tail = p1;
        boolean follow = false;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
            if (follow) tail = tail.next;
            else follow = true;
        }

        tail.next = dummy.next;
        dummy.next = p2.next;
        p2.next = null;

        return dummy.next;
    }

    /**
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     */
    @Test
    public void test1() {
        ListNode res = rotateRight(ListNodeHelper.build0(1, 2, 3, 4, 5), 2);
        ListNodeHelper.check0(res, 4, 5, 1, 2, 3);
    }

    /**
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     */
    @Test
    public void test2() {
        ListNode res = rotateRight(ListNodeHelper.build0(0, 1, 2), 4);
        ListNodeHelper.check0(res, 2, 0, 1);
    }
}
