package com.tukeping.nowcoder;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC103 {

    /**
     * 反转字符串
     * @param str string字符串
     * @return string字符串
     */
    public String solve(String str) {
        char[] chars = str.toCharArray();
        int size = chars.length;
        for (int i = 0, j = size - 1; i < size && i < j; i++, j--) {
            swap(chars, i, j);
        }
        return new String(chars);
    }

    private void swap(char[] chars, int p1, int p2) {
        char tmp = chars[p1];
        chars[p1] = chars[p2];
        chars[p2] = tmp;
    }
}
