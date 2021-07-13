package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] K 个一组翻转链表
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (55.22%)
 * Likes:    402
 * Dislikes: 0
 * Total Accepted:    41.6K
 * Total Submissions: 73.3K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 *
 *
 * 说明：
 *
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * linked-list
 *
 * facebook | microsoft
 *
 * @author tukeping
 * @date 2020/3/9
 **/
public class LeetCode25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = dummy;
        while (cur != null) {
            int cnt = 0;
            for (ListNode i = cur.next; i != null; i = i.next)
                cnt++;
            if (cnt < k) break;

            cnt = 0;
            ListNode a = cur.next;
            ListNode b = a.next;
            while (cnt < k - 1) {
                cnt++;
                ListNode c = b.next;
                b.next = a;
                a = b;
                b = c;
            }

            ListNode p = cur.next;
            cur.next.next = b;
            cur.next = a;
            cur = p;
        }
        return dummy.next;
    }

    public ListNode reverseKGroup3(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while (cur != null && count != k) {
            cur = cur.next;
            count++;
        }
        if (count == k) {
            cur = reverseKGroup3(cur, k);
            while (count != 0) {
                ListNode next = head.next;
                head.next = cur;
                cur = head;
                head = next;
                count--;
            }
            head = cur;
        }
        return head;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = head, pre, sentry = dummy, segHead = null, segCur = null, next;
        int count = 0;
        while (cur != null) {
            if (count == 0) {
                segHead = cur;
                segCur = cur;
            }

            count++;
            cur = cur.next;

            if (count == k) {
                count = 0;
                pre = cur;
                while (segCur != cur) {
                    next = segCur.next;
                    segCur.next = pre;
                    pre = segCur;
                    segCur = next;
                }
                sentry.next = pre;
                sentry = segHead;
            }
        }

        return dummy.next;
    }

    /**
     * 给你这个链表：1->2->3->4->5
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     */
    @Test
    public void test1() {
        ListNode res1 = reverseKGroup(ListNodeHelper.as(1, 2, 3, 4, 5), 2);
        ListNodeHelper.assertThat(res1, 2, 1, 4, 3, 5);

        ListNode res2 = reverseKGroup(ListNodeHelper.as(1, 2, 3, 4, 5), 3);
        ListNodeHelper.assertThat(res2, 3, 2, 1, 4, 5);
    }
}
