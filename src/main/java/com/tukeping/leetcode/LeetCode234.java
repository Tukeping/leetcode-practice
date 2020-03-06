package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=234 lang=java
 *
 * [234] 回文链表
 *
 * https://leetcode-cn.com/problems/palindrome-linked-list/description/
 *
 * algorithms
 * Easy (39.47%)
 * Likes:    416
 * Dislikes: 0
 * Total Accepted:    66.9K
 * Total Submissions: 164.5K
 * Testcase Example:  '[1,2]'
 *
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 *
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * linked-list | two-pointers
 *
 * amazon | facebook
 *
 * @author tukeping
 * @date 2020/3/3
 **/
public class LeetCode234 {

    public boolean isPalindrome(ListNode head) {
        // corner case
        if (head == null) return true;

        // 1. 中中间位置
        ListNode firstHalfEnd = findFirstHalfEnd(head);

        // 2. 反转后半部分链表
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 3. 双指针遍历链表 判断是否回文
        boolean result = isPalindrome(head, secondHalfStart);

        // 4. 复原被反转的后半部分链表, 复原消耗1ms, 整体函数消耗2ms
        firstHalfEnd.next = reverseList(secondHalfStart);

        return result;
    }

    private ListNode findFirstHalfEnd(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private boolean isPalindrome(ListNode p1, ListNode p2) {
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    /**
     * 输入: 1->2
     * 输出: false
     */
    @Test
    public void test1() {
        boolean b = isPalindrome(ListNodeHelper.build0(1, 2));
        assertFalse(b);
    }

    /**
     * 输入: 1->2->2->1
     * 输出: true
     */
    @Test
    public void test2() {
        boolean b = isPalindrome(ListNodeHelper.build0(1, 2, 2, 1));
        assertTrue(b);
    }
}
