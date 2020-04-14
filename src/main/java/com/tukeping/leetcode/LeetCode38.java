package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=38 lang=java
 *
 * [38] 报数
 *
 * https://leetcode-cn.com/problems/count-and-say/description/
 *
 * algorithms
 * Easy (53.68%)
 * Likes:    372
 * Dislikes: 0
 * Total Accepted:    66.5K
 * Total Submissions: 123.1K
 * Testcase Example:  '1'
 *
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 *
 *
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 *
 * 注意：整数序列中的每一项将表示为一个字符串。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: "1"
 *
 *
 * 示例 2:
 *
 * 输入: 4
 * 输出: "1211"
 */

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * string
 *
 * facebook
 *
 * @author tukeping
 * @date 2020/4/8
 **/
public class LeetCode38 {

    public String countAndSay(int n) {
        Queue<Integer> popQueue = new LinkedList<>();
        Queue<Integer> pushQueue = new LinkedList<>();
        popQueue.add(1);
        int cnt, preX;
        for (int i = 2; i <= n; i++) {
            if (popQueue.isEmpty() && !pushQueue.isEmpty()) {
                popQueue.addAll(pushQueue);
                pushQueue.clear();
            }
            cnt = 1;
            preX = popQueue.poll();
            while (!popQueue.isEmpty()) {
                int x = popQueue.peek();
                if (preX == x) {
                    cnt++;
                    popQueue.poll();
                } else {
                    pushQueue.add(cnt);
                    pushQueue.add(preX);
                    cnt = 1;
                    preX = popQueue.poll();
                }
            }
            if (cnt > 0) {
                pushQueue.add(cnt);
                pushQueue.add(preX);
            }
        }
        Queue<Integer> ansQueue = !popQueue.isEmpty() ? popQueue : pushQueue;
        StringBuilder ans = new StringBuilder();
        while (!ansQueue.isEmpty()) {
            ans.append(ansQueue.poll());
        }
        return ans.toString();
    }

    /**
     * 输入: 1
     * 输出: "1"
     */
    @Test
    public void test1() {
        String s = countAndSay(1);
        assertThat(s, is("1"));
    }

    /**
     * 输入: 4
     * 输出: "1211"
     */
    @Test
    public void test2() {
        String s = countAndSay(4);
        assertThat(s, is("1211"));
    }
}
