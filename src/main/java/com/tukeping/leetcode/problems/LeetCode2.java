package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=2 lang=java
 *
 * [2] 两数相加
 *
 * https://leetcode-cn.com/problems/add-two-numbers/description/
 *
 * algorithms
 * Medium (36.21%)
 * Likes:    3705
 * Dislikes: 0
 * Total Accepted:    295.8K
 * Total Submissions: 814.2K
 * Testcase Example:  '[2,4,3]\n[5,6,4]'
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * linked-list | math
 *
 * adobe | airbnb | amazon | bloomberg | microsoft
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/1/9
 **/
public class LeetCode2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;

        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;

        int carry = 0, sum;
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            carry = sum / 10;
            pre.next = new ListNode(sum % 10);

            l1 = l1.next;
            l2 = l2.next;
            pre = pre.next;
        }

        if (carry > 0) {
            ListNode rest = (l1 == null) ? l2 : l1;
            while (rest != null) {
                sum = rest.val + carry;
                carry = sum / 10;
                pre.next = new ListNode(sum % 10);

                rest = rest.next;
                pre = pre.next;
            }
            if (carry > 0) {
                pre.next = new ListNode(carry);
            }
        } else {
            pre.next = (l1 == null) ? l2 : l1;
        }

        return dummy.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1); // 哨兵
        ListNode pre = sentry; // 由于单向链表, 定义一个前节点指针
        int up = 0; // 进位

        while (l1 != null || l2 != null || up > 0) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + up;
            up = sum / 10;

            pre.next = new ListNode(sum % 10);
            pre = pre.next; // 链上

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // sentry.next ==> head;
        return sentry.next;
    }

    public ListNode addTwoNumbersV2(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode pre = null;
        int up = 0;

        while (l1 != null && l2 != null) {
            int a = l1.val;
            int b = l2.val;
            int sum = (a + b) % 10;

            if (head == null) {
                head = new ListNode((sum + up) % 10);
                up = (sum + up) / 10;
                pre = head;
            } else {
                // 设置前一个节点的next属性就是 构建出的当前节点信息, 以及把链表 链起来了
                pre.next = new ListNode((sum + up) % 10);
                up = (sum + up) / 10;
                // 由于是单向链表 记录链表中每一次的当前节点的前一个节点 (为了解决单向链表, 如果是多项链表就无需pre了)
                pre = pre.next;
            }

            up = up + (a + b) / 10;

            l1 = l1.next;
            l2 = l2.next;
        }

        if (head != null && l1 == null && l2 != null) {
            pre.next = l2;
        } else if (head != null && l1 != null) {
            pre.next = l1;
        }

        if (up > 0) {
            if (l1 == null && l2 == null) {
                pre.next = new ListNode(up);
            } else {
                while (up > 0) {
                    if (pre.next == null) {
                        pre.next = new ListNode(up);
                        up = 0;
                        break;
                    }
                    int curVal = pre.next.val;
                    pre.next.val = (curVal + up) % 10;
                    up = (curVal + up) / 10;
                    // 如果还有进位 则继续迭代链表
                    if (up > 0) {
                        pre = pre.next;
                    }
                }
            }
        }

        return head;
    }

    /**
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    @Test
    public void test() {
        ListNode l1 = ListNodeHelper.as(2, 4, 3);
        ListNode l2 = ListNodeHelper.as(5, 6, 4);
        ListNode l3 = addTwoNumbers(l1, l2);
        ListNodeHelper.assertThat(l3, 7, 0, 8);
    }

    /**
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4 -> 9 -> 8)
     * 输出：7 -> 0 -> 8 -> 9 -> 8
     * 原因：342 + 89465 = 89807
     */
    @Test
    public void test2() {
        ListNode l1 = ListNodeHelper.as(2, 4, 3);
        ListNode l2 = ListNodeHelper.as(5, 6, 4, 9, 8);
        ListNode l3 = addTwoNumbers(l1, l2);
        ListNodeHelper.assertThat(l3, 7, 0, 8, 9, 8);
    }

    /**
     * 输入：(2 -> 4 -> 3 -> 1 -> 5) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8 -> 1 -> 5
     * 原因：51342 + 465 = 51807
     */
    @Test
    public void test3() {
        ListNode l1 = ListNodeHelper.as(2, 4, 3, 1, 5);
        ListNode l2 = ListNodeHelper.as(5, 6, 4);
        ListNode l3 = addTwoNumbers(l1, l2);
        ListNodeHelper.assertThat(l3, 7, 0, 8, 1, 5);
    }

    @Test
    public void test4() {
        ListNode l1 = ListNodeHelper.as(5);
        ListNode l2 = ListNodeHelper.as(5);
        ListNode l3 = addTwoNumbers(l1, l2);
        ListNodeHelper.assertThat(l3, 0, 1);
    }

    /**
     * 输入：[1] + [9,9]
     * 输出：[0,0,1]
     */
    @Test
    public void test5() {
        ListNode l1 = ListNodeHelper.as(1);
        ListNode l2 = ListNodeHelper.as(9, 9);
        ListNode l3 = addTwoNumbers(l1, l2);
        ListNodeHelper.assertThat(l3, 0, 0, 1);
    }

    /**
     * 输入: [3,7] + [9,2]
     * 输出: [2,0,1]
     */
    @Test
    public void test6() {
        ListNode l1 = ListNodeHelper.as(3, 7);
        ListNode l2 = ListNodeHelper.as(9, 2);
        ListNode l3 = addTwoNumbers(l1, l2);
        ListNodeHelper.assertThat(l3, 2, 0, 1);
    }

    @Test
    public void test7() {
        ListNode l1 = ListNodeHelper.as(1);
        ListNode l2 = ListNodeHelper.as(9, 9);
        ListNode l3 = addTwoNumbers(l1, l2);
        ListNodeHelper.assertThat(l3, 0, 0, 1);
    }
}
