package com.tukeping.leetcode.contest.weekly.contest183;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/5
 **/
public class LeetCode5195 {

    public String longestDiverseString(int a, int b, int c) {
        MyChar[] myChars = new MyChar[]{
                new MyChar('a', a),
                new MyChar('b', b),
                new MyChar('c', c),
        };
        StringBuilder longestStr = new StringBuilder();
        while (true) {
            Arrays.sort(myChars);
            //先放最多的, 如果上个放的2个字符串和剩余个数最多的字符相同，则放置次多的字符
            if (longestStr.length() >= 2 &&
                    longestStr.charAt(longestStr.length() - 1) == myChars[2].ch &&
                    longestStr.charAt(longestStr.length() - 2) == myChars[2].ch) {
                if (myChars[1].count-- > 0) {
                    longestStr.append(myChars[1].ch);
                } else {
                    break;
                }
            } else {
                if (myChars[2].count-- > 0) {
                    longestStr.append(myChars[2].ch);
                } else {
                    break;
                }
            }
        }
        return longestStr.toString();
    }

    private class MyChar implements Comparable {
        char ch;
        int count;

        public MyChar(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

        @Override
        public int compareTo(Object o) {
            MyChar other = (MyChar) o;
            return this.count - other.count;
        }
    }

    /**
     * 输入：a = 1, b = 1, c = 7
     * 输出："ccaccbcc"
     * 解释："ccbccacc" 也是一种正确答案。
     */
    @Test
    public void test1() {
        String s = longestDiverseString(1, 1, 7);
        assertThat(s, is("ccbccacc"));
    }

    /**
     * 输入：a = 2, b = 2, c = 1
     * 输出："aabbc"
     */
    @Test
    public void test2() {
        String s = longestDiverseString(2, 2, 1);
        assertThat(s, is("baabc"));
    }

    /**
     * 输入：a = 7, b = 1, c = 0
     * 输出："aabaa"
     * 解释：这是该测试用例的唯一正确答案。
     */
    @Test
    public void test3() {
        String s = longestDiverseString(7, 1, 0);
        assertThat(s, is("aabaa"));
    }
}
