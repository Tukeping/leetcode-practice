package com.tukeping.leetcode.contest184;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/12
 **/
public class LeetCode5382 {

    public String entityParser(String text) {
        text = text.replace("&quot;", "\"");
        text = text.replace("&apos;", "'");
        text = text.replace("&amp;", "&");
        text = text.replace("&gt;", ">");
        text = text.replace("&lt;", "<");
        text = text.replace("&frasl;", "/");
        return text;
    }

    public String entityParser2(String text) {
        Map<String, String> spChar = new HashMap<>(6, 1.0F);
        spChar.put("&quot;", "\"");
        spChar.put("&apos;", "'");
        spChar.put("&amp;", "&");
        spChar.put("&gt;", ">");
        spChar.put("&lt;", "<");
        spChar.put("&frasl;", "/");

        // len : 4 ~ 7
        StringBuilder ans = new StringBuilder();
        StringBuilder compare = new StringBuilder();
        boolean input = false;
        for (char c : text.toCharArray()) {
            if (c == '&') {
                input = true;
                compare.append(c);
            } else if (c == ';') {
                input = false;
                compare.append(c);
                int len = compare.length();
                if (4 <= len && len <= 7) {
                    String replace = spChar.get(compare.toString());
                    if (replace != null) {
                        ans.append(replace);
                    } else {
                        ans.append(compare.toString());
                    }
                } else {
                    ans.append(compare.toString());
                }
                compare = new StringBuilder();
            } else if (input) {
                compare.append(c);
            } else {
                ans.append(c);
            }
        }
        return ans.toString();
    }

    /**
     * 输入：text = "&amp; is an HTML entity but &ambassador; is not."
     * 输出："& is an HTML entity but &ambassador; is not."
     * 解释：解析器把字符实体 &amp; 用 & 替换
     */
    @Test
    public void test1() {
        String s = entityParser("&amp; is an HTML entity but &ambassador; is not.");
        assertThat(s, is("& is an HTML entity but &ambassador; is not."));
    }

    /**
     * 输入：text = "and I quote: &quot;...&quot;"
     * 输出："and I quote: \"...\""
     */
    @Test
    public void test2() {
        String s = entityParser("and I quote: &quot;...&quot;");
        assertThat(s, is("and I quote: \"...\""));
    }

    /**
     * 输入：text = "Stay home! Practice on Leetcode :)"
     * 输出："Stay home! Practice on Leetcode :)"
     */
    @Test
    public void test3() {
        String s = entityParser("Stay home! Practice on Leetcode :)");
        assertThat(s, is("Stay home! Practice on Leetcode :)"));
    }

    /**
     * 输入：text = "x &gt; y &amp;&amp; x &lt; y is always false"
     * 输出："x > y && x < y is always false"
     */
    @Test
    public void test4() {
        String s = entityParser("x &gt; y &amp;&amp; x &lt; y is always false");
        assertThat(s, is("x > y && x < y is always false"));
    }

    /**
     * 输入：text = "leetcode.com&frasl;problemset&frasl;all"
     * 输出："leetcode.com/problemset/all"
     */
    @Test
    public void test5() {
        String s = entityParser("leetcode.com&frasl;problemset&frasl;all");
        assertThat(s, is("leetcode.com/problemset/all"));
    }
}
