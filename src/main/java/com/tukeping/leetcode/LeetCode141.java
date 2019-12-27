package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=141 lang=java
 *
 * [141] 环形链表
 *
 * https://leetcode-cn.com/problems/linked-list-cycle/description/
 *
 * algorithms
 * Easy (45.07%)
 * Likes:    434
 * Dislikes: 0
 * Total Accepted:    90.9K
 * Total Submissions: 201.7K
 * Testcase Example:  '[3,2,0,-4]\n1'
 *
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 *
 *
 *
 * 示例 2：
 *
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 *
 *
 *
 * 示例 3：
 *
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 *
 *
 *
 *
 *
 * 进阶：
 *
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tukeping
 * @date 2019/12/23
 **/
public class LeetCode141 {

    @Test
    public void test1() {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;

        boolean b = hasCycle(head);

        Assert.assertTrue(b);
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycleV2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        Set<ListNode> visited = new HashSet<>();

        while (head.next != null) {
            if (visited.contains(head)) {
                return true;
            }
            visited.add(head);
            head = head.next;
        }

        return false;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode turtle = head;
        ListNode rabbit = head.next;

        while (turtle != rabbit) {
            if (rabbit == null || rabbit.next == null) {
                return false;
            }
            turtle = turtle.next;
            rabbit = rabbit.next.next;
        }

        return true;
    }
}
