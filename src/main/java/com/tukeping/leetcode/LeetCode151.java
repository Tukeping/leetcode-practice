package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=151 lang=java
 *
 * [151] 翻转字符串里的单词
 *
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/description/
 *
 * algorithms
 * Medium (33.89%)
 * Likes:    104
 * Dislikes: 0
 * Total Accepted:    29.3K
 * Total Submissions: 83.1K
 * Testcase Example:  '"the sky is blue"'
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 *
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 *
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 *
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 进阶：
 *
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/10
 **/
public class LeetCode151 {

    /**
     * 25/25 cases passed (7 ms)
     * Your runtime beats 49.94 % of java submissions
     * Your memory usage beats 56.16 % of java submissions (37.6 MB)
     */
    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder word = new StringBuilder();
        boolean completeWord = false;

        char[] chars = s.toCharArray();
        int i = 0, wPutIndex = chars.length, len = chars.length, moveStep = 0, lastR = 0, rLen = 0;

        while (i <= wPutIndex - 1) {
            char c = chars[i];
            if (c == ' ') { // before insert
                while (i <= len - 2 && chars[i + 1] == ' ') {
                    rLen++; // redundant blank space
                    moveStep++;
                    i++;
                }
                word.insert(0, ' ');
                completeWord = true;
                moveStep++;
                i++;
            } else {
                if (completeWord && moveStep > 0) {
                    // 设置前置空格
                    for (int k = lastR; k < lastR + rLen; k++) {
                        chars[k] = ' ';
                    }

                    // 单词被移走后 将后续字符全部往上移
                    if (wPutIndex - i >= 0) System.arraycopy(chars, i, chars, i - moveStep + rLen, wPutIndex - i);

                    // 把单词替换放在最后面
                    for (int x = wPutIndex - 1, y = moveStep - rLen - 1; x >= wPutIndex - moveStep - rLen && y >= 0; x--, y--) {
                        chars[x] = word.charAt(y);
                    }

                    // 设置前置空白位置
                    lastR += rLen;
                    i = lastR;

                    // 设置已排好和未排好的单词分界线
                    wPutIndex = wPutIndex - moveStep + rLen;
                    rLen = 0;

                    // 重置单词相关的临时变量
                    completeWord = false;
                    word.delete(0, word.length());
                    moveStep = 0;
                } else { // append word
                    word.append(c);
                    moveStep++;
                    i++;
                }
            }
        }

        return new String(chars).trim();
    }

    /**
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     */
    @Test
    public void test1() {
        String s = reverseWords("the sky is blue");
        assertThat(s, is("blue is sky the"));
    }

    /**
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     */
    @Test
    public void test2() {
        String s = reverseWords("  hello world!  ");
        assertThat(s, is("world! hello"));
    }

    /**
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */
    @Test
    public void test3() {
        String s = reverseWords("a good   example");
        assertThat(s, is("example good a"));
    }

    /**
     * 输入: "   a   b  c d   e  "
     * 输出: "e d c b a"
     */
    @Test
    public void test4() {
        String s = reverseWords("   a   b  c d   e  ");
        assertThat(s, is("e d c b a"));
    }
}
