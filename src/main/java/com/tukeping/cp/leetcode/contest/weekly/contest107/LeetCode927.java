package com.tukeping.cp.leetcode.contest.weekly.contest107;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/8
 **/
public class LeetCode927 {

    public int[] threeEqualParts(int[] A) {
        int len = A.length;

        int i = 0, j = len - 1;
        int head = A[i], tail = A[j], mid = A[i + 1];
        for (int x = i + 2; x <= j - 1; x++) {
            if (A[x] == 0 && mid >= 1) {
                mid <<= 1;
            } else if (A[x] == 1) {
                mid <<= 1;
                mid++;
            }
        }

        while (i < len - 1 && j >= 1) {
            if (head > tail && mid > tail) {
                j--;
                // 扩大tail 只需要关心高位 1
                if (A[j] == 1) {
                    tail += 1 << (len - 1 - j);
                }
                // 同时mid会缩小低位
                mid >>= 1;
            } else if (head < tail && head < mid) {
                i++;
                // 扩大head 相当于低位多了一个bit
                if (A[i] == 0 && head >= 1) {
                    head <<= 1;
                } else if (A[i] == 1) {
                    head <<= 1;
                    head++;
                }
                // 同时mid会缩小高位
                if (A[i] == 1) {
                    mid -= 1 << (j - i - 1);
                }
            } else { // head == tail
                if (mid > tail) {
                    j--;
                    // 扩大tail 只需要关心高位 1
                    if (A[j] == 1) {
                        tail += 1 << (len - 1 - j);
                    }
                    // 同时mid会缩小低位
                    mid >>= 1;
                } else if (mid < tail) {
                    return new int[]{-1, -1};
                } else { // mid == tail
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    @Test
    public void test0() {
        int[] ans = threeEqualParts(new int[]{0, 1, 0, 1, 0, 1, 0});
        assertThat(ans, is(new int[]{2, 5}));
    }

    /**
     * 输入：[1,0,1,0,1]
     * 输出：[0,3]
     */
    @Test
    public void test1() {
        int[] ans = threeEqualParts(new int[]{1, 0, 1, 0, 1});
        assertThat(ans, is(new int[]{0, 3}));
    }

    /**
     * 输出：[1,1,0,1,1]
     * 输出：[-1,-1]
     */
    @Test
    public void test2() {
        int[] ans = threeEqualParts(new int[]{1, 1, 0, 1, 1});
        assertThat(ans, is(new int[]{-1, -1}));
    }

    @Test
    public void test3() {
        int[] ans = threeEqualParts(new int[]{1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0});
        assertThat(ans, is(new int[]{15, 32}));
    }
}
