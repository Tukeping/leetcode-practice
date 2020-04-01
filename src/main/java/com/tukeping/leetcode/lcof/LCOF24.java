package com.tukeping.leetcode.lcof;

/*
 * 面试题24. 反转链表
 *
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF24 {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    @Test
    public void test() {
        ListNode head = ListNodeHelper.build(new int[]{1, 2, 3, 4, 5});
        ListNode ret = reverseList(head);
        ListNodeHelper.check(ret, new int[]{5, 4, 3, 2, 1});
    }
}
