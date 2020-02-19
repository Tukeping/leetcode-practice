package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=142 lang=java
 *
 * [142] 环形链表 II
 *
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/description/
 *
 * algorithms
 * Medium (46.74%)
 * Likes:    364
 * Dislikes: 0
 * Total Accepted:    51.5K
 * Total Submissions: 106.2K
 * Testcase Example:  '[3,2,0,-4]\n1'
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 *
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 *
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 *
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/19
 **/
public class LeetCode142 {

    /*
     * 16/16 cases passed (1 ms)
     * Your runtime beats 55.3 % of java submissions
     * Your memory usage beats 5 % of java submissions (46.6 MB)
     */

    public ListNode detectCycle(ListNode head) {
        // no cycle should return null.

        if (head == null) return null;
        if (head.next == null) return null;

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (fast != slow) {
            if (fast == null || fast.next == null) { // no cycle
                return null;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        // slow and fast Meet in `M`

        ListNode p1 = head;
        ListNode p2 = slow;

        // 2 * slow = fast
        // slow = F + A
        // fast = F + A + B + A
        // 2(F+A) = F+A+B+A => F = B

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        // because of F = B, p1 and p2 will Meet in Entry `E`

        return p1;
    }

    /**
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：tail connects to node index 1
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     */
    @Test
    public void test1() {
        ListNode head = ListNodeHelper.buildCycle(new int[]{3, 2, 0, -4}, 1);
        ListNode entry = detectCycle(head);
        assertThat(entry.val, is(2));
    }

    /**
     * 输入：head = [1,2], pos = 0
     * 输出：tail connects to node index 0
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     */
    @Test
    public void test2() {
        ListNode head = ListNodeHelper.buildCycle(new int[]{1, 2}, 0);
        ListNode entry = detectCycle(head);
        assertThat(entry.val, is(1));
    }

    /**
     * 输入：head = [1], pos = -1
     * 输出：no cycle
     * 解释：链表中没有环。
     */
    @Test
    public void test3() {
        ListNode head = ListNodeHelper.buildCycle(new int[]{1}, -1);
        ListNode entry = detectCycle(head);
        assertNull(entry);
    }

    @Test
    public void test4() {
        ListNode head = ListNodeHelper.buildCycle(new int[]{1, 2}, -1);
        ListNode entry = detectCycle(head);
        assertNull(entry);
    }
}
