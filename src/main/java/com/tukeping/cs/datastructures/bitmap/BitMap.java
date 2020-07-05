package com.tukeping.cs.datastructures.bitmap;

import java.util.Random;

/**
 * @author tukeping
 * @date 2020/6/10
 **/
public class BitMap {
    private char[] bytes;
    private int n;

    public BitMap(int n) {
        this.n = n;
        this.bytes = new char[n / 8 + 1];
    }

    public void set(int k) {
        if (k > n) return;
        int byteIndex = k / 8;
        int bitIndex = k % 8;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean get(int k) {
        if (k > n) return false;
        int byteIndex = k / 8;
        int bitIndex = k % 8;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int n = 10_0000;

        BitMap bitMap = new BitMap(n);

        for (int i = 1; i <= n; i++) {
            int k = random.nextInt(n);
            bitMap.set(k);
        }

        while(true) {
            int k = random.nextInt(n);
            boolean exist = bitMap.get(k);
            System.out.println(k + " : " + exist);
            if(exist) break;
        }
    }
}
