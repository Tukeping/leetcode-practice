package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=1290 lang=java
 *
 * [1290] 二进制链表转整数
 *
 * https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/description/
 *
 * algorithms
 * Easy (80.26%)
 * Likes:    19
 * Dislikes: 0
 * Total Accepted:    11.5K
 * Total Submissions: 14.2K
 * Testcase Example:  '[1,0,1]'
 *
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 *
 * 请你返回该链表所表示数字的 十进制值 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [1,0,1]
 * 输出：5
 * 解释：二进制数 (101) 转化为十进制数 (5)
 *
 *
 * 示例 2：
 *
 * 输入：head = [0]
 * 输出：0
 *
 *
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：1
 *
 *
 * 示例 4：
 *
 * 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
 * 输出：18880
 *
 *
 * 示例 5：
 *
 * 输入：head = [0,0]
 * 输出：0
 *
 *
 *
 *
 * 提示：
 *
 *
 * 链表不为空。
 * 链表的结点总数不超过 30。
 * 每个结点的值不是 0 就是 1。
 *
 *
 */

import com.tukeping.leetcode.structures.ListNode;
import com.tukeping.tools.ListNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/9
 **/
public class LeetCode1290 {

    public int getDecimalValue(ListNode head) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        int sum = 0, i = 0;
        while (head != null) {
            sum += head.val * (int) Math.pow(2, len - 1 - i);
            head = head.next;
            i++;
        }

        return sum;
    }

    /**
     * 输入：head = [1,0,1]
     * 输出：5
     * 解释：二进制数 (101) 转化为十进制数 (5)
     */
    @Test
    public void test1() {
        int n = getDecimalValue(ListNodeHelper.build0(1, 0, 1));
        assertThat(n, is(5));
    }

    /**
     * 输入：head = [0]
     * 输出：0
     */
    @Test
    public void test2() {
        int n = getDecimalValue(ListNodeHelper.build0(0));
        assertThat(n, is(0));
    }

    /**
     * 输入：head = [1]
     * 输出：1
     */
    @Test
    public void test3() {
        int n = getDecimalValue(ListNodeHelper.build0(1));
        assertThat(n, is(1));
    }

    /**
     * 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
     * 输出：18880
     */
    @Test
    public void test4() {
        int n = getDecimalValue(ListNodeHelper.build0(1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0));
        assertThat(n, is(18880));
    }

    /**
     * 输入：head = [0,0]
     * 输出：0
     */
    @Test
    public void test5() {
        int n = getDecimalValue(ListNodeHelper.build0(0, 0));
        assertThat(n, is(0));
    }
}
