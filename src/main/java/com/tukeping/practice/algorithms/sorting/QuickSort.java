package com.tukeping.practice.algorithms.sorting;

/**
 * @author tukeping
 * @date 14-7-13
 */
public class QuickSort extends Sort {

    public int topK(int[] a, int k) {
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }
        return a[k];
    }

    /**
     * duplicate keys
     */
    public void sortWithDupKey(int[] nums, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = nums[lo];
        int i = lo;
        while (i <= gt) {
            if (nums[i] < v) swap(nums, lt++, i++);
            else if (nums[i] > v) swap(nums, i, gt--);
            else i++;
        }
        sortWithDupKey(nums, lo, lt - 1);
        sortWithDupKey(nums, gt + 1, hi);
    }

    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    /**
     * Basic plan.
     *   Partition so that, for some j
     *     entry a[j] is in place
     *     no larger entry to the left of j
     *     no smaller entry to the right of j
     *   Sort each piece recursively
     *
     * Phase 1. Repeat until i and j pointers cross.
     *   Scan i from left to right so long as (a[i] < a[lo]).
     *   Scan j from right to left so long as (a[j] > a[lo]).
     *   Exchange a[i] with a[j].
     */
    private void sort(int[] nums, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(nums, lo, hi);
        sort(nums, lo, j - 1);
        sort(nums, j + 1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (nums[++i] < nums[lo]) {
                if (i == hi) break;
            }

            while (nums[--j] > nums[lo]) {
                if (j == lo) break;
            }

            if (i >= j) break;
            swap(nums, i, j);
        }

        swap(nums, lo, j);
        return j;
    }
}
