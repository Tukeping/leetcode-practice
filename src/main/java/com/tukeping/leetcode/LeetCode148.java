package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=148 lang=java
 *
 * [148] 排序链表
 *
 * https://leetcode-cn.com/problems/sort-list/description/
 *
 * algorithms
 * Medium (62.69%)
 * Likes:    414
 * Dislikes: 0
 * Total Accepted:    40.6K
 * Total Submissions: 63.6K
 * Testcase Example:  '[4,2,1,3]'
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 *
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * linked-list | sort
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/2/17
 **/
public class LeetCode148 {

    /*
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     */

    public ListNode sortList(ListNode head) {
        int period = 1, length = countLength(head);
        ListNode pre = new ListNode(-1), cur, left, right, merged;

        while (period < length) {
            cur = head;
            while (cur != null) {
                left = cur;
                right = skipPeriod(cur, period);
                mergeTwoLinkedLists(left, right, period);
                attach(pre, right);
            }
            head = pre.next;
            period *= 2;
        }

        return pre.next;
    }

    private ListNode build(ListNode cur, int period) {

        return null;
    }

    private ListNode attach(ListNode pre, ListNode merged) {

        return pre;
    }

    private ListNode skipPeriod(ListNode node, int period) {
        int count = 0;
        while (count < period) {
            count++;
            node = node.next;
        }
        return node;
    }

    private int countLength(ListNode head) {
        int length = 0;
        ListNode tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

    private ListNode mergeTwoLinkedLists(ListNode left, ListNode right, int period) {
        ListNode pre = new ListNode(-1), merged;
        int leftLifeTime = period;
        int rightLifeTime = period;

        if (left.val > right.val) {
            merged = right;
            right = right.next;
            rightLifeTime--;
        } else {
            merged = left;
            left = left.next;
            leftLifeTime--;
        }

        pre.next = merged;

        while (leftLifeTime > 0 && rightLifeTime > 0) {
            if (left.val > right.val) {
                merged.next = right;
                right = right.next;
                merged = merged.next;
                rightLifeTime--;
            } else {
                merged.next = left;
                left = left.next;
                merged = merged.next;
                leftLifeTime--;
            }
        }

        while (leftLifeTime > 0) {
            merged.next = left;
            left = left.next;
            merged = merged.next;
            leftLifeTime--;
        }

        while (rightLifeTime > 0) {
            merged.next = right;
            right = right.next;
            merged = merged.next;
            rightLifeTime--;
        }

        return pre.next;
    }

    private void compareAndSwap(ListNode node) {
        if (node == null || node.next == null)
            return;
        if (node.val > node.next.val) {
            ListNode tmp = node.next.next;
            node.next.next = node;
            node.next = tmp;
        }
    }

    /**
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     */
    @Test
    public void test1() {
        ListNode node = ListNodeHelper.build0(4, 2, 1, 3);
        ListNode ret = sortList(node);
        ListNodeHelper.check0(ret, 1, 2, 3, 4);
    }

    /**
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     */
    @Test
    public void test2() {
        ListNode node = ListNodeHelper.build0(-1, 5, 3, 4, 0);
        ListNode ret = sortList(node);
        ListNodeHelper.check0(ret, -1, 0, 3, 4, 5);
    }
}
