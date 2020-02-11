package com.tukeping.jdk;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/11
 **/
public class IntegerToBinary {

    public String toBinaryString(int i) {
        char[] buf = new char[32];
        Arrays.fill(buf, '0');

        boolean isNegative = i < 0;

        if (isNegative) {
            buf[31] = '1';
            i = -i;
        }

        boolean isOdd = (i % 2 == 1);
        if (isOdd) {
            buf[0] = '1';
            i--;
        }

        int n = i / 2; // 偶数一定能被2整除

        for (int k = 0; k < n; k++) {
            // 进位
            fireCarry(buf, 1);
        }

        if (isNegative) {
            // 先反码 再加1
            inverseBytes(buf);
            if (buf[0] == '0') {
                buf[0] = '1';
            } else {
                fireCarry(buf, 0);
            }
        }

        int lastOneIndex = lastOne(buf);

        String str = new String(buf);
        String outputStr = str.substring(0, lastOneIndex + 1);

        return new StringBuilder(outputStr).reverse().toString();
    }

    private int lastOne(char[] buf) {
        for (int i = buf.length - 1; i >= 0; i--) {
            if (buf[i] == '1') return i;
        }
        return 0;
    }

    private void fireCarry(char[] buf, int start) {
        for (int x = start; x <= 30; x++) {
            if (buf[x] == '0') {
                buf[x] = '1';
                break;
            } else {
                buf[x] = '0';
            }
        }
    }

    private void inverseBytes(char[] buf) {
        for (int i = 0; i <= 30; i++) {
            if (buf[i] == '0') {
                buf[i] = '1';
            } else if (buf[i] == '1') {
                buf[i] = '0';
            }
        }
    }

    @Test
    public void test1() {
        assertThat(toBinaryString(0), is("0"));
        assertThat(toBinaryString(1), is("1"));
        assertThat(toBinaryString(2), is("10"));
        assertThat(toBinaryString(3), is("11"));
        assertThat(toBinaryString(4), is("100"));
        assertThat(toBinaryString(5), is("101"));
        assertThat(toBinaryString(6), is("110"));
        assertThat(toBinaryString(7), is("111"));
        assertThat(toBinaryString(8), is("1000"));
        assertThat(toBinaryString(9), is("1001"));
        assertThat(toBinaryString(-8), is("11111111111111111111111111111000"));
    }
}
