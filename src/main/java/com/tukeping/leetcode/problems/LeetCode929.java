package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=929 lang=java
 *
 * [929] 独特的电子邮件地址
 *
 * https://leetcode-cn.com/problems/unique-email-addresses/description/
 *
 * algorithms
 * Easy (63.91%)
 * Likes:    103
 * Dislikes: 0
 * Total Accepted:    14.3K
 * Total Submissions: 22.4K
 * Testcase Example:  '["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]'
 *
 * 每封电子邮件都由一个本地名称和一个域名组成，以 @ 符号分隔。
 *
 * 例如，在 alice@leetcode.com中， alice 是本地名称，而 leetcode.com 是域名。
 *
 * 除了小写字母，这些电子邮件还可能包含 '.' 或 '+'。
 *
 * 如果在电子邮件地址的本地名称部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名称中没有点的同一地址。例如，"alice.z@leetcode.com”
 * 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。 （请注意，此规则不适用于域名。）
 *
 * 如果在本地名称中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件，例如 m.y+name@email.com 将转发到
 * my@email.com。 （同样，此规则不适用于域名。）
 *
 * 可以同时使用这两个规则。
 *
 * 给定电子邮件列表 emails，我们会向列表中的每个地址发送一封电子邮件。实际收到邮件的不同地址有多少？
 *
 * 示例：
 * 输入：["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * 输出：2
 * 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
 *
 * 提示：
 * 1 <= emails[i].length <= 100
 * 1 <= emails.length <= 100
 * 每封 emails[i] 都包含有且仅有一个 '@' 字符。
 */

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/20
 **/
public class LeetCode929 {

    /*
     * 184/184 cases passed (45 ms)
     * Your runtime beats 8.19 % of java submissions
     * Your memory usage beats 5.2 % of java submissions (43.6 MB)
     */

    public int numUniqueEmails(String[] emails) {
        // corner case
        if (emails == null || emails.length == 0) return 0;

        Set<String> uniqueEmails = new HashSet<>();

        for (String email : emails) {
            String[] emailParts = email.split("@");
            String localName = emailParts[0];
            localName = localName.replaceAll("\\.", "");
            int firstPlusIndex = localName.indexOf("+");
            if (firstPlusIndex != -1) {
                localName = localName.substring(0, firstPlusIndex);
            }
            uniqueEmails.add(localName + "@" + emailParts[1]);
        }

        return uniqueEmails.size();
    }

    /**
     * 输入：["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
     * 输出：2
     * 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
     */
    @Test
    public void test1() {
        int n = numUniqueEmails(new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"});
        assertThat(n, is(2));
    }

    @Test
    public void test2() {
        int n = numUniqueEmails(new String[]{"testemail@leetcode.com", "testemail1@leetcode.com", "testemail+david@lee.tcode.com"});
        assertThat(n, is(3));
    }
}
