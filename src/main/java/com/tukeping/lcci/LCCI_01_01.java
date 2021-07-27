package com.tukeping.lcci;

/**
 * @author tukeping
 * @date 2021/7/20
 **/
public class LCCI_01_01 {

    public boolean isUnique(String s) {
        boolean[] b = new boolean[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            int cx = c - 'a';
            if (b[cx]) return false;
            else b[cx] = true;
        }
        return true;
    }

    public boolean isUniqueV2(String s) {
        int checker = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }
}
