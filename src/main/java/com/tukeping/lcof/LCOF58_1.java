package com.tukeping.lcof;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF58_1 {

    public String reverseWords(String s) {
        int len = s.length();
        int[] word = {-1, -1};
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 160 || s.charAt(i) == 32) {
                if (word[0] != -1) {
                    ans.insert(0, s, word[0], word[1] + 1).insert(0, " ");
                    word = new int[]{-1, -1};
                }
            } else {
                if (word[0] == -1) {
                    word[0] = i;
                    word[1] = i;
                } else {
                    word[1] = i;
                }
            }
        }
        if (word[0] != -1) ans.insert(0, s, word[0], word[1] + 1).insert(0, " ");
        if (ans.length() > 0) ans.deleteCharAt(0);
        return ans.toString();
    }

    public String reverseWords2(String s) {
        List<int[]> words = new ArrayList<>();
        int len = s.length();
        int[] word = {-1, -1};
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 160 || s.charAt(i) == 32) {
                if (word[0] != -1) {
                    words.add(word);
                    word = new int[]{-1, -1};
                }
            } else {
                if (word[0] == -1) {
                    word[0] = i;
                    word[1] = i;
                } else {
                    word[1] = i;
                }
            }
        }
        if (word[0] != -1) words.add(word);

        StringBuilder ans = new StringBuilder();
        for (int i = words.size() - 1; i >= 0; i--) {
            ans.append(s, words.get(i)[0], words.get(i)[1] + 1).append(" ");
        }
        if (ans.length() > 0) ans.deleteCharAt(ans.length() - 1);
        return ans.toString();
    }

    /**
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     */
    @Test
    public void test1() {
        String res = reverseWords("the sky is blue");
        assertThat(res, is("blue is sky the"));
    }

    /**
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     */
    @Test
    public void test2() {
        String res = reverseWords("  hello world!  ");
        assertThat(res, is("world! hello"));
    }

    /**
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */
    @Test
    public void test3() {
        String res = reverseWords("a good   example");
        assertThat(res, is("example good a"));
    }
}
