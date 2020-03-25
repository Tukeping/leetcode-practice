package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=1324 lang=java
 *
 * [1324] 竖直打印单词
 *
 * https://leetcode-cn.com/problems/print-words-vertically/description/
 *
 * algorithms
 * Medium (56.85%)
 * Likes:    12
 * Dislikes: 0
 * Total Accepted:    2K
 * Total Submissions: 3.6K
 * Testcase Example:  '"HOW ARE YOU"'
 *
 * 给你一个字符串 s。请你按照单词在 s 中的出现顺序将它们全部竖直返回。
 * 单词应该以字符串列表的形式返回，必要时用空格补位，但输出尾部的空格需要删除（不允许尾随空格）。
 * 每个单词只能放在一列上，每一列中也只能有一个单词。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "HOW ARE YOU"
 * 输出：["HAY","ORO","WEU"]
 * 解释：每个单词都应该竖直打印。
 * ⁠"HAY"
 * "ORO"
 * "WEU"
 *
 *
 * 示例 2：
 *
 * 输入：s = "TO BE OR NOT TO BE"
 * 输出：["TBONTB","OEROOE","   T"]
 * 解释：题目允许使用空格补位，但不允许输出末尾出现空格。
 * "TBONTB"
 * "OEROOE"
 * "   T"
 *
 *
 * 示例 3：
 *
 * 输入：s = "CONTEST IS COMING"
 * 输出：["CIC","OSO","N M","T I","E N","S G","T"]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 200
 * s 仅含大写英文字母。
 * 题目数据保证两个单词之间只有一个空格。
 *
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tukeping
 * @date 2020/3/25
 **/
public class LeetCode1324 {

    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int maxWordLen = 0;
        for (String word : words) {
            maxWordLen = Math.max(maxWordLen, word.length());
        }
        List<String> res = new ArrayList<>(maxWordLen);
        for (int i = 0; i < maxWordLen; i++) {
            StringBuilder newWord = new StringBuilder();
            for (String word : words) {
                if (i > word.length() - 1) newWord.append(" ");
                else newWord.append(word.charAt(i));
            }
            int len = newWord.length();
            while (newWord.charAt(len - 1) == ' ') len--;
            res.add(newWord.toString().substring(0, len));
        }
        return res;
    }

    /**
     * 输入：s = "HOW ARE YOU"
     * 输出：["HAY","ORO","WEU"]
     * 解释：每个单词都应该竖直打印。
     *  "HAY"
     *  "ORO"
     *  "WEU"
     */
    @Test
    public void test1() {

    }

    /**
     * 输入：s = "TO BE OR NOT TO BE"
     * 输出：["TBONTB","OEROOE","   T"]
     * 解释：题目允许使用空格补位，但不允许输出末尾出现空格。
     * "TBONTB"
     * "OEROOE"
     * "   T"
     */
    @Test
    public void test2() {

    }

    /**
     * 输入：s = "CONTEST IS COMING"
     * 输出：["CIC","OSO","N M","T I","E N","S G","T"]
     */
    @Test
    public void test3() {

    }
}
