package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 *
 * https://leetcode-cn.com/problems/reverse-linked-list/description/
 *
 * algorithms
 * Easy (66.00%)
 * Likes:    767
 * Dislikes: 0
 * Total Accepted:    157.6K
 * Total Submissions: 235.1K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * linked-list
 *
 * adobe | amazon | apple | bloomberg | facebook | microsoft | snapchat | twitter | uber | yahoo | yelp | zenefits
 *
 * @author tukeping
 * @date 2020/2/17
 **/
public class LeetCode206 {

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

    /** time: O(n log n) space: O(1) **/
    public ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        ListNode tail = findTail(head);
        return loop(head, tail);
    }

    public ListNode reverseListLoop(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode tail = head;
        while (tail.next != null) tail = tail.next;

        ListNode cur;
        while (head != tail) {
            cur = head;
            head = head.next;
            cur.next = tail.next;
            tail.next = cur;
        }

        return head;
    }

    private ListNode findTail(ListNode head) {
        ListNode tail = head;
        while (tail.next != null) tail = tail.next;
        return tail;
    }

    private ListNode loop(ListNode head, ListNode tail) {
        ListNode cur;
        while (head != tail) {
            cur = head;
            head = head.next;
            cur.next = tail.next;
            tail.next = cur;
        }
        return head;
    }

    private ListNode recursion(ListNode head, ListNode tail) {
        if (head == tail) return head;

        ListNode cur = head;
        head = head.next;
        cur.next = tail.next;
        tail.next = cur;

        return recursion(head, tail);
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
