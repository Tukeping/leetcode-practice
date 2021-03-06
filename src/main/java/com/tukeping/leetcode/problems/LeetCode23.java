package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并K个排序链表
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (48.16%)
 * Likes:    426
 * Dislikes: 0
 * Total Accepted:    62.6K
 * Total Submissions: 130K
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 */

// @lc code=start

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * linked-list | divide-and-conquer | heap
 *
 * airbnb | amazon | facebook | google | linkedin | microsoft | twitter | uber
 *
 * frequency 4
 *
 * @author tukeping
 * @since 2019/12/27
 */
public class LeetCode23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> heap = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        Collections.addAll(heap, lists);

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            if (node.next != null) heap.add(node.next);
            cur.next = node;
            cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        if (lists.length == 1)
            return lists[0];

        return mergeKListsDivideAndConquer(lists, 0, lists.length - 1);
    }

    public ListNode mergeKListsDivideAndConquer(ListNode[] lists, int from, int to) {
        // step 1, write end condition
        if (from == to)
            return lists[from];

        if (from + 1 == to)
            return mergeTwoLists(lists[from], lists[to]);

        // step 2, split sub problem
        int middle = (from + to) / 2;
        ListNode leftList = mergeKListsDivideAndConquer(lists, from, middle);
        ListNode rightList = mergeKListsDivideAndConquer(lists, middle + 1, to);

        // step 3, merge sub problems => final problem
        return mergeTwoLists(leftList, rightList);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }

        pre.next = (l1 == null) ? l2 : l1;
        return dummy.next;
    }

    public ListNode mergeKListsV1(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        for (ListNode listNode : lists) {
            while (listNode != null) {
                list.add(listNode.val);
                listNode = listNode.next;
            }
        }

        if (list.isEmpty()) {
            return null;
        }

        Collections.sort(list);

        ListNode ret = new ListNode(list.get(0));
        ListNode last = ret;
        for (int i = 1; i < list.size(); i++) {
            last.next = new ListNode(list.get(i));
            last = last.next;
        }

        return ret;
    }

    public ListNode mergeKListsV2(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        ListNode mergedList = lists[0];
        for (int i = 1; i < lists.length; i++) {
            mergedList = mergeTwoLists(mergedList, lists[i]);
        }
        return mergedList;
    }

    @Test
    public void test() {
        ListNode l1 = ListNodeHelper.as(1, 4, 5);
        ListNode l2 = ListNodeHelper.as(1, 3, 4);
        ListNode l3 = ListNodeHelper.as(2, 6);
        ListNode l4 = mergeKLists(new ListNode[]{l1, l2, l3});

        // 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
        ListNodeHelper.assertThat(l4, 1, 1, 2, 3, 4, 4, 5, 6);
    }
}
