package com.tukeping.lcci;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/16
 **/
public class LCCI_01_06 {

    public String compressStringV2(String s) {
        int n = s.length();
        int m = 0;
        int count = 1;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                m += 1 + String.valueOf(count).length();
                count = 1;
            } else {
                count++;
            }
        }
        if (m >= n) return s;
        StringBuilder sb = new StringBuilder(m);
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                sb.append(s.charAt(i));
                sb.append(count);
                count = 1;
            } else {
                count++;
            }
        }
        return sb.toString();
    }

    public String compressString(String S) {
        if (S.length() == 0) return "";
        StringBuilder compressString = new StringBuilder();
        char[] chars = S.toCharArray();
        int index = 0, count = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            count++;
            if (chars[i] != chars[i + 1]) {
                compressString.append(chars[i]).append(count);
                count = 0;
                index = i + 1;
            }
        }
        compressString.append(chars[index]).append(chars.length - index);
        String res = compressString.toString();
        return res.length() < S.length() ? res : S;
    }

    public String compressString4(String S) {
        int N = S.length();
        if (N <= 2) return S;
        int i = 0, j;
        StringBuilder sb = new StringBuilder();
        while (i < N) {
            j = i;
            while (j < N && S.charAt(j) == S.charAt(i)) j++;
            sb.append(S.charAt(i));
            sb.append(j - i);
            i = j;
        }
        String res = sb.toString();
        return res.length() < S.length() ? res : S;
    }

    public String compressString3(String S) {
        if (S.length() == 0) return S; // 空串处理
        String ans = "";
        int cnt = 1;
        char ch = S.charAt(0);
        for (int i = 1; i < S.length(); ++i) {
            if (ch == S.charAt(i)) cnt++;
            else {
                ans += ch + String.valueOf(cnt); // 注意 cnt 要转为字符串
                ch = S.charAt(i);
                cnt = 1;
            }
        }
        ans += ch + String.valueOf(cnt);
        return ans.length() >= S.length() ? S : ans;
    }

    public String compressString2(String S) {
        if (S == null || S.length() <= 2) return S;
        String compress = "";
        int len = S.length(), count = 1;
        char pre = S.charAt(0);
        for (int i = 1; i < len; i++) {
            if (S.charAt(i) == pre) {
                count++;
            } else {
                compress += pre + "" + count;
                pre = S.charAt(i);
                count = 1;
            }
        }
        compress += pre + "" + count;
        int cpLen = compress.length();
        return cpLen < len ? compress : S;
    }

    /**
     *  输入："aabcccccaaa"
     *  输出："a2b1c5a3"
     */
    @Test
    public void test1() {
        String res = compressString("aabcccccaaa");
        assertThat(res, is("a2b1c5a3"));
    }

    /**
     *  输入："abbccd"
     *  输出："abbccd"
     *  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
     */
    @Test
    public void test2() {
        String res = compressString("abbccd");
        assertThat(res, is("abbccd"));
    }
}
