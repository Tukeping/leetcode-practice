package com.tukeping.lcci;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/21
 **/
public class LCCI_01_03 {

    public String replaceSpaces(String s, int size) {
        int spaceCount = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') spaceCount++;
        }
        int totalSize = size + spaceCount * 2;
        char[] chars = new char[totalSize];
        int index = totalSize - 1;
        for (int i = size - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                chars[index--] = '0';
                chars[index--] = '2';
                chars[index--] = '%';
            } else {
                chars[index--] = s.charAt(i);
            }
        }
        return new String(chars);
    }

    @Test
    public void test() {
        String actual = replaceSpaces("ds sdfs afs sdfa dfssf asdf             ", 27);
        Assert.assertEquals("ds%20sdfs%20afs%20sdfa%20dfssf%20asdf", actual);
    }
}
