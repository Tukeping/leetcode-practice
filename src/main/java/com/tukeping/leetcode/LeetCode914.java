package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=914 lang=java
 *
 * [914] 卡牌分组
 *
 * https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/description/
 *
 * algorithms
 * Easy (32.87%)
 * Likes:    112
 * Dislikes: 0
 * Total Accepted:    19.7K
 * Total Submissions: 53.5K
 * Testcase Example:  '[1,2,3,4,4,3,2,1]'
 *
 * 给定一副牌，每张牌上都写着一个整数。
 *
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 *
 *
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 *
 *
 * 仅当你可选的 X >= 2 时返回 true。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 *
 *
 * 示例 2：
 *
 * 输入：[1,1,1,2,2,2,3,3]
 * 输出：false
 * 解释：没有满足要求的分组。
 *
 *
 * 示例 3：
 *
 * 输入：[1]
 * 输出：false
 * 解释：没有满足要求的分组。
 *
 *
 * 示例 4：
 *
 * 输入：[1,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]
 *
 *
 * 示例 5：
 *
 * 输入：[1,1,2,2,2,2]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 *
 *
 *
 *
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * binary-search | random
 *
 * @author tukeping
 * @date 2020/3/27
 **/
public class LeetCode914 {

    public boolean hasGroupsSizeX(int[] deck) {
        int[] cnt = new int[10000];
        for (int num : deck)
            cnt[num]++;

        int g = -1;
        for (int i = 0; i < 10000; i++) {
            if (cnt[i] > 0) {
                if (g == -1) {
                    g = cnt[i];
                } else {
                    g = gcd(g, cnt[i]);
                }
            }
        }
        return g >= 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public boolean hasGroupsSizeX2(int[] deck) {
        if (deck.length <= 1) return false;

        Map<Integer, Integer> M = new HashMap<>();
        for (int num : deck) {
            M.put(num, M.getOrDefault(num, 0) + 1);
        }

        Integer[] values = M.values().toArray(new Integer[0]);
        int len = values.length;

        for (int i = 1; i < len; i++) {
            if (isInValid(values[i - 1], values[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isInValid(int big, int small) {
        if (big == 1 || small == 1) return true;

        if (big < small) {
            int tmp = big;
            big = small;
            small = tmp;
        }

        int gcd = gcd(big, small);
        if (gcd == 1) return true;

        return big % gcd != 0 && small % gcd != 0;
    }

    /**
     * 输入：[1,2,3,4,4,3,2,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
     */
    @Test
    public void test1() {
        assertTrue(hasGroupsSizeX(new int[]{1, 2, 3, 4, 4, 3, 2, 1}));
    }

    /**
     * 输入：[1,1,1,2,2,2,3,3]
     * 输出：false
     * 解释：没有满足要求的分组。
     */
    @Test
    public void test2() {
        assertFalse(hasGroupsSizeX(new int[]{1, 1, 1, 2, 2, 2, 3, 3}));
    }

    /**
     * 输入：[1]
     * 输出：false
     * 解释：没有满足要求的分组。
     */
    @Test
    public void test3() {
        assertFalse(hasGroupsSizeX(new int[]{1}));
    }

    /**
     * 输入：[1,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]
     */
    @Test
    public void test4() {
        assertTrue(hasGroupsSizeX(new int[]{1, 1}));
    }

    /**
     * 输入：[1,1,2,2,2,2]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
     */
    @Test
    public void test5() {
        assertTrue(hasGroupsSizeX(new int[]{1, 1, 2, 2, 2, 2}));
    }

    @Test
    public void test6() {
        assertTrue(hasGroupsSizeX(new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}));
    }

    @Test
    public void test7() {
        assertTrue(hasGroupsSizeX(new int[]{0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3}));
    }

    @Test
    public void test8() {
        assertTrue(hasGroupsSizeX(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 2, 2}));
    }

    @Test
    public void test9() {
        assertFalse(hasGroupsSizeX(new int[]{0, 0, 1, 1, 1, 1, 2, 2, 3, 4}));
    }
}
