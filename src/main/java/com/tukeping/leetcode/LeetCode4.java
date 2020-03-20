package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个有序数组的中位数
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | binary-search | divide-and-conquer
 *
 * adobe | apple | dropbox | google | microsoft | yahoo | zenefits
 *
 * @author tukeping
 * @date 2020/3/4
 **/
public class LeetCode4 {

    /** time: O(log(m+n)) space: O(1) **/
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    /** time: O(log(m+n)) space: O(1) **/
    public double findMedianSortedArrays3(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int left = findKth(A, 0, m, B, 0, n, (m + n + 1) / 2);
        int right = findKth(A, 0, m, B, 0, n, (m + n + 2) / 2);
        return (left + right) / 2.0;
    }

    private int findKth(int[] A, int i, int m, int[] B, int j, int n, int k) {
        if (i >= m) return B[j + k - 1];
        if (j >= n) return A[i + k - 1];
        if (k == 1) return Math.min(A[i], B[j]);
        int mid1 = (i + k / 2 - 1 < m) ? A[i + k / 2 - 1] : Integer.MAX_VALUE;
        int mid2 = (j + k / 2 - 1 < n) ? B[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (mid1 < mid2) return findKth(A, i + k / 2, m, B, j, n, k - k / 2);
        else return findKth(A, i, m, B, j + k / 2, n, k - k / 2);
    }

    /** time: O(m+n) space: O(1) **/
    public double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int mid = len / 2;
        int left = 0, right = 0;
        int aIdx = 0, bIdx = 0;
        for (int i = 0; i <= mid; i++) {
            left = right;
            if (aIdx < m && (bIdx >= n || A[aIdx] < B[bIdx])) {
                right = A[aIdx++];
            } else {
                right = B[bIdx++];
            }
        }
        return (len & 1) == 1 ? right : (left + right) / 2.0;
    }

    /** time: O(m+n) space: O(m+n) **/
    public double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (n == 0) {
            if (m % 2 == 1) return A[m / 2];
            else return (A[m / 2] + A[m / 2 - 1]) / 2.0;
        }
        if (m == 0) {
            if (n % 2 == 1) return B[n / 2];
            else return (B[n / 2] + B[n / 2 - 1]) / 2.0;
        }
        int total = m + n;
        int[] nums = new int[total];
        int i = 0, j = 0, idx = 0;
        while (idx != total) {
            if (i == m) {
                while (j != n) nums[idx++] = B[j++];
                break;
            }
            if (j == n) {
                while (i != m) nums[idx++] = A[i++];
                break;
            }
            if (A[i] < B[j]) {
                nums[idx++] = A[i++];
            } else {
                nums[idx++] = B[j++];
            }
        }
        if (total % 2 == 1) {
            return nums[total / 2];
        } else {
            return (nums[total / 2] + nums[total / 2 - 1]) / 2.0;
        }
    }

    /**
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * 则中位数是 2.0
     */
    @Test
    public void test1() {
        double median = findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
        assertThat(median, is(2.0));
    }

    /**
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * 则中位数是 (2 + 3)/2 = 2.5
     */
    @Test
    public void test2() {
        double median = findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        assertThat(median, is(2.5));
    }
}
