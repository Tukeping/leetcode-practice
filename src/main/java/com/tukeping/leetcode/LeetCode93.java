package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=93 lang=java
 *
 * [93] 复原IP地址
 *
 * https://leetcode-cn.com/problems/restore-ip-addresses/description/
 *
 * algorithms
 * Medium (45.10%)
 * Likes:    189
 * Dislikes: 0
 * Total Accepted:    24.5K
 * Total Submissions: 53.7K
 * Testcase Example:  '"25525511135"'
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

/**
 * 回溯算法 (backtracking)
 *
 * @author tukeping
 * @date 2020/2/10
 **/
public class LeetCode93 {

    public List<String> restoreIpAddresses(String s) {
        char[] chars = s.toCharArray();
        Set<String> ips = new LinkedHashSet<>();
        int len = chars.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len - 1; k++) {
                    // [(0,i), (i+1,j), (j+1,k), (k+1,len-1)] 4个ip数如果都是合法的 那么这个ip字符串就是合法的
                    int item4 = createIpItem(chars, k + 1, len - 1);
                    if (illegalIpItem(item4)) {
                        continue;
                    }

                    int item3 = createIpItem(chars, j + 1, k);
                    if (illegalIpItem(item3)) {
                        continue;
                    }

                    int item2 = createIpItem(chars, i + 1, j);
                    if (illegalIpItem(item2)) {
                        continue;
                    }

                    int item1 = createIpItem(chars, 0, i);
                    if (illegalIpItem(item1)) {
                        continue;
                    }

                    ips.add(item1 + "." + item2 + "." + item3 + "." + item4);
                }
            }
        }
        return new ArrayList<>(ips);
    }

    private int createIpItem(char[] chars, int start, int end) {
        // 单个ip字段的位数 = 末尾index - 开始index + 1
        int n = end - start + 1;
        if (n > 3) {
            return -1;
        }
        // 如果位数大于2位, 但是最高位不能为0, 可以允许 单个数字0
        int highN = Integer.parseInt(chars[start] + "");
        if (n > 1 && highN == 0) {
            return -1;
        }
        int item = 0;
        for (int i = 0; i < n; i++) {
            item += Integer.parseInt(chars[end - i] + "") * Math.pow(10, i);
        }
        // 如果ip = 00 是不允许的, 也就是 n 位数大于1位, 但是最终值却还是0 则直接返回-1表示不认可
        if (n > 1 && item == 0) {
            return -1;
        }
        return item;
    }

    private boolean illegalIpItem(int item) {
        return item > 255 || item < 0;
    }

    @Test
    public void test() {
        List<String> ips = restoreIpAddresses("25525511135");
        assertThat(ips, hasItems("255.255.11.135", "255.255.111.35"));
    }

    /**
     * ["0.1.0.10",
     * "0.1.0.10",
     * "0.1.1.0",
     * "0.10.0.10",
     * "0.10.1.0",
     * "0.100.1.0",
     * "1.0.0.10",
     * "1.0.1.0",
     * "1.0.1.0",
     * "10.0.1.0"]
     */
    @Test
    public void test2() {
        List<String> ips = restoreIpAddresses("010010");
        assertThat(ips, hasItems("0.10.0.10", "0.100.1.0"));
    }
}
