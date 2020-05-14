package com.tukeping.misc.book.sword_means_offer;

/*
 * 面试题06. 从尾到头打印链表
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF06 {

    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        if (head.next == null) return new int[]{head.val};

        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        int size = 0;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            size++;
        }
        head = pre;
        int[] ans = new int[size];
        int i = 0;
        while (head != null) {
            ans[i++] = head.val;
            head = head.next;
        }
        return ans;
    }

    /**
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     */
    @Test
    public void test1() {
        ListNode head = ListNodeHelper.build0(1, 3, 2);
        int[] ans = reversePrint(head);
        assertThat(ans, is(new int[]{2, 3, 1}));
    }
}
