package com.tukeping.cs.algorithms.hash;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author tukeping
 * @date 2020/3/23
 **/
public class HashPractice {

    static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'};

    private int horner_hash(String str) {
        int h = 1;
        for (int i = 0; i < str.length(); i++) {
            h = h * 31 + str.charAt(i);
        }
        return h;
    }

    private HashCode murmur_hash_hashcode(String str) {
        HashFunction hf = Hashing.murmur3_32();
        return hf.hashString(str, StandardCharsets.UTF_8);
    }

    private int murmur_hash_int(String str) {
        HashCode hc = murmur_hash_hashcode(str);
        return hc.asInt();
    }

    private static String to62RadixString(long seq) {
        StringBuilder sBuilder = new StringBuilder();
        do {
            int remainder = (int) (seq % 62);
            sBuilder.append(DIGITS[remainder]);
            seq = seq / 62;
        } while (seq != 0);
        return sBuilder.reverse().toString();
    }

    @Test
    public void test1() {
        System.out.println(horner_hash("abc"));
        System.out.println(horner_hash("abd"));

        System.out.println(murmur_hash_int("abc"));
        System.out.println(murmur_hash_int("abd"));

        System.out.println(murmur_hash_int("https://github.com/wangzheng0822/ratelimiter4j"));
        System.out.println(to62RadixString(murmur_hash_int("https://github.com/wangzheng0822/ratelimiter4j")));
    }
}
