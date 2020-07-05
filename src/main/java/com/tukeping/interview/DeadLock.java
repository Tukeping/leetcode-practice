package com.tukeping.interview;

/**
 * jps
 *
 * jstack pid
 *
 * found 1 deadlock
 *
 * @author tukeping
 * @date 2020/6/20
 **/
public class DeadLock {

    static class DeadLockThread extends Thread {
        String s1, s2;

        public DeadLockThread(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public void run() {
            synchronized (s1) {
                System.out.println(Thread.currentThread().getName() + " locked " + s1);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {}
                synchronized (s2) {
                    System.out.println(Thread.currentThread().getName() + " locked " + s2);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new DeadLockThread("A", "B");
        Thread t2 = new DeadLockThread("B", "A");
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("done");
    }
}
