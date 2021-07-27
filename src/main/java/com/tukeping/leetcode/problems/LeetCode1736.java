package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/24
 **/
public class LeetCode1736 {

    public String maximumTime(String time) {
        char[] times = time.toCharArray();
        char h1 = times[0];
        char h2 = times[1];
        char m1 = times[3];
        char m2 = times[4];
        if (h1 == '?') {
            if (h2 == '0' || h2 == '1' || h2 == '2' || h2 == '3' || h2 == '?') {
                h1 = '2';
            } else {
                h1 = '1';
            }
        }
        if (h2 == '?') {
            if (h1 == '0' || h1 == '1') {
                h2 = '9';
            } else {
                h2 = '3';
            }
        }
        if (m1 == '?') {
            m1 = '5';
        }
        if (m2 == '?') {
            m2 = '9';
        }
        StringBuilder sb = new StringBuilder();
        sb.append(h1);
        sb.append(h2);
        sb.append(":");
        sb.append(m1);
        sb.append(m2);
        return sb.toString();
    }
}
