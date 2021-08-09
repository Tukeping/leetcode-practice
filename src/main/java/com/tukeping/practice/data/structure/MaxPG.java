package com.tukeping.practice.data.structure;

/**
 * @author tukeping
 * @date 2021/8/1
 **/
public class MaxPG {

    private int[] pq;
    private int N;

    public MaxPG(int cap) {
        this.pq = new int[cap];
    }

    public void insert(int x) {
        pq[N++] = x;
    }

    public int delMax() {
        int max = 0;
        for (int i = 1; i < N; i++) {
            if (max < i) max = i;
        }
        swap(pq, max, N - 1);
        return pq[--N];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
