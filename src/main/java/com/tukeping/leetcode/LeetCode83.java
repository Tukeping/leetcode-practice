package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=83 lang=java
 *
 * [83] 删除排序链表中的重复元素
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/description/
 *
 * algorithms
 * Easy (48.43%)
 * Likes:    260
 * Dislikes: 0
 * Total Accepted:    73K
 * Total Submissions: 148.8K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 *
 *
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2020/2/19
 **/
public class LeetCode83 {

    public ListNode deleteDuplicatesV2(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode cur = head;
        while(cur.next != null) {
            if(cur.val == cur.next.val) cur.next = cur.next.next;
            else cur = cur.next;
        }

        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        // corner case
        if (head == null) return null;

        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null) {
            if (cur.val == pre.val) {
                cur = cur.next;
                pre.next = cur;
                if (cur == null) break;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return head;
    }

    /**
     * 输入: 1->1->2
     * 输出: 1->2
     */
    @Test
    public void test1() {
        ListNode head = ListNodeHelper.build0(1, 1, 2);
        ListNode res = deleteDuplicates(head);
        ListNodeHelper.check0(res, 1, 2);
    }

    /**
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     */
    @Test
    public void test2() {
        ListNode head = ListNodeHelper.build0(1, 1, 2, 3, 3);
        ListNode res = deleteDuplicates(head);
        ListNodeHelper.check0(res, 1, 2, 3);
    }

    @Test
    public void test3() {
        ListNode head = ListNodeHelper.build0(1, 1, 1);
        ListNode res = deleteDuplicates(head);
        ListNodeHelper.check0(res, 1);
    }
}
