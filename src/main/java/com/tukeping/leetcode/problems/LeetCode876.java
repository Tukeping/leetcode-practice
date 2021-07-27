package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=876 lang=java
 *
 * [876] 链表的中间结点
 *
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/description/
 *
 * algorithms
 * Easy (63.54%)
 * Likes:    154
 * Dislikes: 0
 * Total Accepted:    29K
 * Total Submissions: 44.5K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next
 * = NULL.
 *
 * 示例 2：
 *
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *
 * 提示：
 * 给定链表的结点数介于 1 和 100 之间。
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ordered-map
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/3/6
 **/
public class LeetCode876 {

    public ListNode middleNodeV2(ListNode head) {
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        int mid = count / 2;
        for (int i = 1; i <= mid; i++) {
            head = head.next;
        }
        return head;
    }

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     */
    @Test
    public void test1() {
        ListNode res = middleNode(ListNodeHelper.as(1, 2, 3, 4, 5));
        assertThat(res.val, is(3));
    }

    /**
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
     */
    @Test
    public void test2() {
        ListNode res = middleNode(ListNodeHelper.as(1, 2, 3, 4, 5, 6));
        assertThat(res.val, is(4));
    }
}
