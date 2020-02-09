package com.tukeping.cs.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author tukeping
 * @date 2020/2/6
 **/
public class ForkJoinCalculator implements Calculator {
    private ForkJoinPool pool;

    public ForkJoinCalculator() {
        // 公共的ForkJoin线程池
        // pool = ForkJoinPool.commonPool()
        pool = new ForkJoinPool();
    }

    @Override
    public long sum(long[] numbers) {
        SumTask task = new SumTask(numbers, 0, numbers.length - 1);
        long result = pool.invoke(task);
        System.out.println("ForkJoinPool thread size is " + pool.getPoolSize());
        return result;
    }

    private static class SumTask extends RecursiveTask<Long> {
        private long[] numbers;
        private int start;
        private int end;

        public SumTask(long[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start < 6) {
                long total = 0L;
                for (int i = start; i <= end; i++) {
                    total += numbers[i];
                }
                return total;
            } else {
                int middle = (start + end) / 2;
                SumTask task1 = new SumTask(numbers, start, middle);
                SumTask task2 = new SumTask(numbers, middle + 1, end);
                task1.fork();
                task2.fork();
                return task1.join() + task2.join();
            }
        }
    }
}
