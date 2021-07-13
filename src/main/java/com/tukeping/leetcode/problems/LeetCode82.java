package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=82 lang=java
 *
 * [82] 删除排序链表中的重复元素 II
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/description/
 *
 * algorithms
 * Medium (44.77%)
 * Likes:    220
 * Dislikes: 0
 * Total Accepted:    32.6K
 * Total Submissions: 71K
 * Testcase Example:  '[1,2,3,3,4,4,5]'
 *
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 *
 *
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * @author tukeping
 * @date 2020/2/19
 **/
public class LeetCode82 {

    /*
     * 168/168 cases passed (1 ms)
     * Your runtime beats 98.5 % of java submissions
     * Your memory usage beats 5.1 % of java submissions (39.5 MB)
     */

    public ListNode deleteDuplicates(ListNode head) {
        // corner case
        if (head == null) return null;

        ListNode sentry = new ListNode(-1);
        sentry.next = head;

        ListNode pre = sentry;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return sentry.next;
    }

    /**
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     */
    @Test
    public void test1() {
        ListNode head = ListNodeHelper.as(1, 2, 3, 3, 4, 4, 5);
        ListNode res = deleteDuplicates(head);
        ListNodeHelper.assertThat(res, 1, 2, 5);
    }

    /**
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     */
    @Test
    public void test2() {
        ListNode head = ListNodeHelper.as(1, 1, 1, 2, 3);
        ListNode res = deleteDuplicates(head);
        ListNodeHelper.assertThat(res, 2, 3);
    }

    /**
     * 输入: 1->1
     * 输出: null
     */
    @Test
    public void test3() {
        ListNode head = ListNodeHelper.as(1, 1);
        ListNode res = deleteDuplicates(head);
        assertNull(res);
    }
}
