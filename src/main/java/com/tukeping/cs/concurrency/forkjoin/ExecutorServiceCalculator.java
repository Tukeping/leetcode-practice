package com.tukeping.cs.concurrency.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author tukeping
 * @date 2020/2/6
 **/
public class ExecutorServiceCalculator implements Calculator {
    private int parallelism;
    private ExecutorService pool;

    public ExecutorServiceCalculator() {
        parallelism = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(parallelism);
    }

    @Override
    public long sum(long[] numbers) {
        List<Future<Long>> results = new ArrayList<>();

        int part = numbers.length / parallelism;
        for (int i = 0; i < parallelism; i++) {
            int start = i * part;
            int end = (i == parallelism - 1) ?
                    numbers.length - 1 : (i + 1) * part - 1;
            SumTask task = new SumTask(numbers, start, end);
            Future<Long> result = pool.submit(task);
            results.add(result);
        }

        long total = 0L;
        for (Future<Long> result : results) {
            try {
                total += result.get();
            } catch (InterruptedException | ExecutionException ignore) {
            }
        }

        return total;
    }

    private static class SumTask implements Callable<Long> {

        private long[] numbers;
        private int start;
        private int end;

        public SumTask(long[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() {
            long total = 0;
            for (int i = start; i <= end; i++) {
                total += numbers[i];
            }
            return total;
        }
    }
}
