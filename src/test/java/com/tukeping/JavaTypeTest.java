package com.tukeping;

/**
 * 王垠出的题目:
 * 这段代码里面到底哪一行错了？为什么？如果某个 Java 版本能顺利运行这段代码，那么如何让这个错误暴露得更致命一些？
 * http://www.yinwang.org/blog-cn/2020/02/13/java-type-system
 *
 * @author tukeping
 * @date 2020/2/19
 **/
public class JavaTypeTest {

    public static void f() {
        String[] a = new String[2];
        Object[] b = a;
        a[0] = "hi";
        b[1] = Integer.valueOf(42);
    }

    public static void main(String[] args) {
        f();
    }
}
