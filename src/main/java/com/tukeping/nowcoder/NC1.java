package com.tukeping.nowcoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC1 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String solve(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        int p1 = len1 - 1;
        int p2 = len2 - 1;
        StringBuilder sb = new StringBuilder();
        int x = 0;
        while (p1 >= 0 || p2 >= 0) {
            int a, b;
            if (p1 < 0) {
                a = 0;
                b = t.charAt(p2) - '0';
                p2--;
            } else if (p2 < 0) {
                a = s.charAt(p1) - '0';
                b = 0;
                p1--;
            } else {
                a = s.charAt(p1) - '0';
                b = t.charAt(p2) - '0';
                p1--;
                p2--;
            }
            int sum = a + b + x;
            int val = sum % 10;
            x = sum / 10;
            sb.append(val);
        }
        if (x == 1) sb.append(x);
        sb.reverse();
        return sb.toString();
    }

    @Test
    public void test1() {
        String actual = solve("1", "99");
        Assert.assertEquals("100", actual);
    }

    @Test
    public void test() {
        String actual = solve("9", "99999999999999999999999999999999999999999999999999999999999994");
        Assert.assertEquals("100000000000000000000000000000000000000000000000000000000000003", actual);
    }
}
