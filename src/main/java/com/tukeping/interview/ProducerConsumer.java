package com.tukeping.interview;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者/消费者 模式
 *
 * @author tukeping
 * @date 2020/6/20
 **/
public class ProducerConsumer {

    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    private int size;
    private Queue<String> queue;

    public ProducerConsumer(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

    public void produce(String element) {
        lock.lock();
        try {
            while (isFull()) notFull.await();
            queue.offer(element);
            System.out.println(Thread.currentThread().getName() + " offer " + element);
            notEmpty.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String consume() {
        String res = null;
        lock.lock();
        try {
            while (isEmpty()) notEmpty.await();
            res = queue.poll();
            System.out.println(Thread.currentThread().getName() + " poll " + res);
            notFull.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return res;
    }

    private boolean isEmpty() {
        return queue.size() == 0;
    }

    private boolean isFull() {
        return queue.size() == size;
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer(5);
        Runnable p = () -> {
            for (int i = 0; i < 10; i++) {
                pc.produce("A" + i);
            }
        };
        Runnable c = () -> {
            for (int i = 0; i < 10; i++) {
                pc.consume();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(p);
        es.submit(c);
    }
}
