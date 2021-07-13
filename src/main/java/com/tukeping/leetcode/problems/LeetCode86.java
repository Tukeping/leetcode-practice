package com.tukeping.leetcode.problems;

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2020/4/8
 **/
public class LeetCode86 {

    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(0);
        return dummy.next;
    }

    /**
     * 输入: head = 1->4->3->2->5->2, x = 3
     * 输出: 1->2->2->4->3->5
     */
    @Test
    public void test1() {
        ListNode head = ListNodeHelper.as(1, 4, 3, 2, 5, 2);
        ListNode ans = partition(head, 3);
        ListNodeHelper.assertThat(ans, 1, 2, 2, 4, 3, 5);
    }
}
