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

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode midNode = middleNode(head);
        ListNode rightHead = midNode.next;
        midNode.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        return mergeTwoLists(left, right);
    }

    /** 876.链表的中间结点 **/
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /** 21.合并两个有序链表 **/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1);
        ListNode cur = sentry;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = (l1 == null) ? l2 : l1;
        return sentry.next;
    }

    public ListNode sortList2(ListNode head) {
        // 增加头结点
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode preNode = pre;

        /*
         * 循环增加排序子链表的长度
         * (preNode = sortSubList(preNode, len)) != null 作为边界条件，
         * 意为第一次调用sortSubList就返回null，说明链表长度小于len*2，可以结束排序
         */
        for (int len = 1; (preNode = sortSubList(preNode, len)) != null; len *= 2) {
            while (preNode != null) {
                preNode = sortSubList(preNode, len);
            }
            preNode = pre;
        }

        return pre.next;
    }

    // 返回排序后的链表的最后一个节点
    private ListNode sortSubList(ListNode pre, int num) {
        //当前指针位置
        int fstnum = 0, lstnum = 0;
        //当前节点
        ListNode fst = pre.next, lst = fst;

        //找到lst节点位置
        for (int i = 0; i < num; i++) {
            //fst长度不够，直接返回null，此时子链表已经是排好序的
            if (lst == null)
                return null;
            lst = lst.next;
        }

        //比较，终止条件为fstnum或lastnum大于子链表长度，或者lsatnum到达链表末尾
        while (fstnum < num && lstnum < num && lst != null) {
            if (fst.val <= lst.val) {
                pre.next = fst;
                fst = fst.next;
                fstnum++;
            } else {
                pre.next = lst;
                lst = lst.next;
                lstnum++;
            }
            pre = pre.next;
        }

        //如果fst子链表还有剩余节点，接到当前节点后面
        if (fstnum < num) {
            pre.next = fst;
            //让当前节点走到子链表末尾
            for (; fstnum < num; fstnum++)
                pre = pre.next;
            //接上后面的链表
            pre.next = lst;
        }

        //和上面类似，此时还需考虑last子链表长度小于num的情况
        if (lstnum < num && lst != null) {
            pre.next = lst;
            for (; lstnum < num && pre != null; lstnum++)
                pre = pre.next;
        }

        //返回最后一个节点，作为下次排序的pre
        return pre;
    }

    /**
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     */
    @Test
    public void test1() {
        ListNode node = ListNodeHelper.build0(4, 2, 1, 3);
        ListNode res = sortList(node);
        ListNodeHelper.check0(res, 1, 2, 3, 4);
    }

    /**
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     */
    @Test
    public void test2() {
        ListNode node = ListNodeHelper.build0(-1, 5, 3, 4, 0);
        ListNode res = sortList(node);
        ListNodeHelper.check0(res, -1, 0, 3, 4, 5);
    }
}
