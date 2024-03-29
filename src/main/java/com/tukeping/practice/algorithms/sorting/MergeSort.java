package com.tukeping.practice.algorithms.sorting;

/**
 * @author tukeping
 * @date 2021/7/29
 **/
public class MergeSort extends Sort {

    /**
     * Basic plan.
     *   Divide array into two halves.
     *   Recursively sort each half.
     *   Merge two halves.
     */
    public void sort(int[] nums) {
        int n = nums.length;
        int[] aux = new int[n];
        sort(nums, aux, 0, n - 1);
    }

    private void sort(int[] nums, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(nums, aux, lo, mid);
        sort(nums, aux, mid + 1, hi);
        merge(nums, aux, lo, mid, hi);
    }

    public void sortV2(int[] nums) {
        int N = nums.length;
        int[] aux = new int[N];
        for (int sz = 1; sz < N; sz = sz + sz)
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                merge(nums, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
    }

    private void merge(int[] nums, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = nums[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) nums[k] = aux[j++];
            else if (j > hi) nums[k] = aux[i++];
            else if (aux[j] < aux[i]) nums[k] = aux[j++];
            else nums[k] = aux[i++];
        }
    }
}
