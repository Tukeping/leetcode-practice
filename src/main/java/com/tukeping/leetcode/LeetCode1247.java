package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=1247 lang=java
 *
 * [1247] 交换字符使得字符串相同
 *
 * https://leetcode-cn.com/problems/minimum-swaps-to-make-strings-equal/description/
 *
 * algorithms
 * Medium (51.59%)
 * Likes:    11
 * Dislikes: 0
 * Total Accepted:    2K
 * Total Submissions: 3.8K
 * Testcase Example:  '"xx"\r\n"yy"\r'
 *
 * 有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。
 *
 * 每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
 *
 * 交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和
 * s1[j]。
 *
 * 最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "xx", s2 = "yy"
 * 输出：1
 * 解释：
 * 交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。
 *
 * 示例 2：
 *
 * 输入：s1 = "xy", s2 = "yx"
 * 输出：2
 * 解释：
 * 交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
 * 交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
 * 注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。
 *
 * 示例 3：
 *
 * 输入：s1 = "xx", s2 = "xy"
 * 输出：-1
 *
 *
 * 示例 4：
 *
 * 输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
 * 输出：4
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s1.length, s2.length <= 1000
 * s1, s2 只包含 'x' 或 'y'。
 *
 *
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。
 * 每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
 * 交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。
 * 最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。
 *
 * @author tukeping
 * @date 2019/11/4
 **/
public class LeetCode1247 {

    /***
     * 输入：s1 = "xx", s2 = "yy"
     * 输出：1
     * 解释：
     * 交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。
     */
    @Test
    public void test() {
        String s1 = "xx";
        String s2 = "yy";

        int n = minimumSwap(s1, s2);

        assertEquals(1, n);
    }

    /***
     * 输入：s1 = "xy", s2 = "yx"
     * 输出：2
     * 解释：
     * 交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
     * 交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
     * 注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。
     */
    @Test
    public void test2() {
        String s1 = "xy";
        String s2 = "yx";

        int n = minimumSwap(s1, s2);

        assertEquals(2, n);
    }

    /**
     * 输入：s1 = "xx", s2 = "xy"
     * 输出：-1
     */
    @Test
    public void test3() {
        String s1 = "xx";
        String s2 = "xy";

        int n = minimumSwap(s1, s2);

        assertEquals(-1, n);
    }

    /**
     * 输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
     * 输出：4
     *
     * step1. 对比数组长度, 如果不一致则直接 -1 如果 equals 则直接 0
     *
     * step2. 去除对齐的冗余
     * xxyyxyxyxx
     * xyyxyxxxyx
     *
     * step3. 切割2个字符一组
     * xy yx yx
     * xy yx xy
     *
     * 如果 xx, yy 或者 yy, xx 则交换 1 次
     * 如果 xy, yx 或者 yx, xy 则交换 2 次
     * 否则 -1
     */
    @Test
    public void test4() {
        String s1 = "xxyyxyxyxx";
        String s2 = "xyyxyxxxyx";

        int n = minimumSwap(s1, s2);

        System.out.println("count = " + n);

        assertEquals(4, n);
    }

    public int minimumSwap(String s1, String s2) {
        // step1. 对比数组长度, 如果不一致则直接 -1 如果 两个字符串equals 则直接 0
        if (s1.length() != s2.length()) {
            return -1;
        }
        if (s1.equals(s2)) {
            return 0;
        }

        // step2. 去除对齐的冗余
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        char[] f1 = new char[c1.length];
        char[] f2 = new char[c2.length];

        int index = 0;
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) {
                f1[index] = c1[i];
                f2[index] = c2[i];
                index++;
            }
        }

        // step3. 将数组以2个为一组进行分组
        // case1: 如果 xx, yy 或者 yy, xx 则交换 1 次
        // case2: 如果 xy, yx 或者 yx, xy 则交换 2 次
        // 否则 直接范围 -1, 情况可能有 xx, yx等
        // 统计count时, 有一个trip,
        // 如果是case2的情况可以将两组进行相互 互换位置 进行消融交换次数, 所以用了一个xr布尔变量, 当只剩下最后一组无法消融直接累加即可
        // 如果是case1的情况则无法消融统计直接累加
        int count = 0;
        boolean xr = false;
        for (int i = 0; i < f1.length; i = i + 2) {
            if (f1[i] == '\u0000') {
                continue;
            }
            if (f1[i] == 'x' && f1[i + 1] == 'x' && f2[i] == 'y' && f2[i + 1] == 'y') {
                count++;
            } else if (f1[i] == 'y' && f1[i + 1] == 'y' && f2[i] == 'x' && f2[i + 1] == 'x') {
                count++;
            } else if (f1[i] == 'x' && f1[i + 1] == 'y' && f2[i] == 'y' && f2[i + 1] == 'x') {
                if (!xr) {
                    count = count + 2;
                    xr = true;
                } else {
                    xr = false;
                }
            } else if (f1[i] == 'y' && f1[i + 1] == 'x' && f2[i] == 'x' && f2[i + 1] == 'y') {
                if (!xr) {
                    count = count + 2;
                    xr = true;
                } else {
                    xr = false;
                }
            } else {
                return -1;
            }
        }

        return count;
    }
}
