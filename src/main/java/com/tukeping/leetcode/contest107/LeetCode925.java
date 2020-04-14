package com.tukeping.leetcode.contest107;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/8
 **/
public class LeetCode925 {

    public boolean isLongPressedName(String name, String typed) {
        int nLen = name.length();
        int tLen = typed.length();
        if (nLen == 0) return false;
        if (tLen < nLen) return false;

        int nIdx = 0, tIdx = 0, types, shouldTypes;
        while (tIdx <= tLen && nIdx < nLen) {
            types = 0;
            shouldTypes = 1;

            while (nIdx < nLen - 1 && name.charAt(nIdx) == name.charAt(nIdx + 1)) {
                shouldTypes++;
                nIdx++;
            }

            while (tIdx < tLen && typed.charAt(tIdx) == name.charAt(nIdx)) {
                types++;
                tIdx++;
            }

            if (types < shouldTypes) {
                return false;
            }

            nIdx++;
        }
        return tIdx == tLen && nIdx == nLen;
    }

    /**
     * 输入：name = "alex", typed = "aaleex"
     * 输出：true
     * 解释：'alex' 中的 'a' 和 'e' 被长按。
     */
    @Test
    public void test1() {
        assertTrue(isLongPressedName("alex", "aaleex"));
    }

    /**
     * 输入：name = "saeed", typed = "ssaaedd"
     * 输出：false
     * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
     */
    @Test
    public void test2() {
        assertFalse(isLongPressedName("saeed", "ssaaedd"));
    }

    /**
     * 输入：name = "leelee", typed = "lleeelee"
     * 输出：true
     */
    @Test
    public void test3() {
        assertTrue(isLongPressedName("leelee", "lleeelee"));
    }

    /**
     * 输入：name = "laiden", typed = "laiden"
     * 输出：true
     * 解释：长按名字中的字符并不是必要的。
     */
    @Test
    public void test4() {
        assertTrue(isLongPressedName("laiden", "laiden"));
    }

    @Test
    public void test5() {
        assertTrue(isLongPressedName("yyxbtsrs", "yyyyxbbtssrs"));
    }
}
