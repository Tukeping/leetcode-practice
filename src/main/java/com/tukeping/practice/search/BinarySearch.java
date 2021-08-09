package com.tukeping.practice.search;

/**
 * @author tukeping
 * @date 2021/7/31
 **/
public class BinarySearch {

    public int bsearch(int[] a, int target) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target < a[mid]) hi = mid - 1;
            else if (target > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
