package com.tukeping.leetcode.lcof;

/*
 * 面试题22. 链表中倒数第k个节点
 *
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2020/3/9
 **/
public class LCOF_22 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        for (int i = k; i < len; i++) {
            head = head.next;
        }

        return head;
    }

    /**
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     *
     * 返回链表 4->5.
     */
    @Test
    public void test1() {
        ListNode res = getKthFromEnd(ListNodeHelper.build0(1, 2, 3, 4, 5), 2);
        ListNodeHelper.check0(res, 4, 5);
    }
}
