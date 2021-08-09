package com.tukeping.practice.data.structure;

/**
 * @author tukeping
 * @date 2021/8/3
 **/
public class Heap {

    private int[] pq;
    private int N;

    public Heap(int cap) {
        this.pq = new int[cap + 1];
    }

    public void sort(int[] pq) {
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(pq, k, N);
        }
        while (N > 1) {
            swap(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

    /**
     * 向上 堆化
     * parent: k / 2
     */
    private void swim(int k) {
        while (k > 1 && pq[k / 2] < pq[k]) {
            swap(pq, k, k / 2);
            k = k / 2;
        }
    }

    /**
     * 向下 堆化
     * children:
     *  left: 2 * k
     *  right: 2 * k + 1
     */
    private void sink(int k) {
        sink(this.pq, k, N);
    }

    private void sink(int[] pq, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && pq[j] < pq[j + 1]) j++;
            if (pq[k] >= pq[j]) break;
            swap(pq, k, j);
            k = j;
        }
    }

    /**
     * Insert. Add node at end, then swim it up.
     * Cost. At most 1 + logN compares.
     */
    public void insert(int x) {
        pq[++N] = x;
        swim(N);
    }

    /**
     * Delete max. Exchange root with node at end, then sink it down.
     * Cost. At most 2*logN compares.
     */
    public int delMax() {
        int max = pq[1];
        swap(pq, 1, N--);
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return N == 0;
    }
}
