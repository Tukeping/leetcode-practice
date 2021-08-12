package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=160 lang=java
 *
 * [160] 相交链表
 *
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/description/
 *
 * algorithms
 * Easy (50.87%)
 * Likes:    547
 * Dislikes: 0
 * Total Accepted:    79.1K
 * Total Submissions: 149.8K
 * Testcase Example:  '8\n[4,1,8,4,5]\n[5,0,1,8,4,5]\n2\n3'
 *
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 如下面的两个链表：
 *
 *
 *
 * 在节点 c1 开始相交。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2,
 * skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为
 * [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 *
 *
 *
 * 示例 2：
 *
 *
 *
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB
 * = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为
 * [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 *
 *
 *
 * 示例 3：
 *
 *
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为
 * 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 *
 *
 *
 * 注意：
 *
 *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 *
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/3
 **/
public class LeetCode160 {

    public ListNode getIntersectionNodeV2(ListNode headA, ListNode headB) {
        if (headA == headB) return headA;
        ListNode p1 = headA;
        ListNode p2 = headB;
        boolean p1Reset = false, p2Reset = false;
        while (true) {
            p1 = p1.next;
            if (p1 == null) {
                if (p1Reset) break;
                p1 = headB;
                p1Reset = true;
            }
            p2 = p2.next;
            if (p2 == null) {
                if (p2Reset) break;
                p2 = headA;
                p2Reset = true;
            }
            if (p1 == p2) return p1;
        }
        return null;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;

            if (pA == null && pB == null) return null;

            if (pA == null) pA = headB;
            if (pB == null) pB = headA;
        }

        return pA;
    }

    /**
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Reference of the node with value = 8
     * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
     * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     */
    @Test
    public void test1() {
        ListNode common = ListNodeHelper.as(8, 4, 5);
        ListNode headA = ListNodeHelper.as(4, 1);
        headA.next = common;
        ListNode headB = ListNodeHelper.as(5, 0, 1);
        headB.next = common;
        ListNode ret = getIntersectionNode(headA, headB);
        assertThat(ret.val, is(8));
    }

    /**
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Reference of the node with value = 2
     * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
     * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     */
    @Test
    public void test2() {
        ListNode common = ListNodeHelper.as(2, 4);
        ListNode headA = ListNodeHelper.as(0, 9, 1);
        headA.next = common;
        ListNode headB = ListNodeHelper.as(3);
        headB.next = common;
        ListNode ret = getIntersectionNode(headA, headB);
        assertThat(ret.val, is(2));
    }

    /**
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
     * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 解释：这两个链表不相交，因此返回 null。
     */
    @Test
    public void test3() {
        ListNode headA = ListNodeHelper.as(2, 6, 4);
        ListNode headB = ListNodeHelper.as(1, 5);
        ListNode ret = getIntersectionNode(headA, headB);
        assertNull(ret);
    }
}
