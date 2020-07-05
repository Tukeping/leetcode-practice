package com.tukeping.interview;

/**
 * @author tukeping
 * @date 2020/6/19
 **/
public class StringTest {

    public static void main(String[] args) {
        String a = "ab";
        String b = "a" + "b";
        System.out.println(a == b);

        String c = "ab";
        String d = new String("a" + "b");
        System.out.println(c == d);
    }
}
