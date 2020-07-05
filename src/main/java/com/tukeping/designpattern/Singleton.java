package com.tukeping.designpattern;

/**
 * CPU: 2.3 GHz 4 core Intel Core i7
 * MEM: 16 GB 1600 MHz DDR3
 *
 * @author tukeping
 * @date 2020/6/19
 **/
public class Singleton {

    // 饿汉、懒汉、静态内部类、枚举、双检锁

    private Singleton() {
    }

    private static volatile Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);
    }
}
