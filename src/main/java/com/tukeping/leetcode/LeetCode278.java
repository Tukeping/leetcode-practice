package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=278 lang=java
 *
 * [278] 第一个错误的版本
 *
 * https://leetcode-cn.com/problems/first-bad-version/description/
 *
 * algorithms
 * Easy (37.37%)
 * Likes:    147
 * Dislikes: 0
 * Total Accepted:    40K
 * Total Submissions: 106.9K
 * Testcase Example:  '5\n4'
 *
 *
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 *
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 *
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version
 * 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 *
 * 示例:
 *
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 *
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 *
 * 所以，4 是第一个错误的版本。 
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * binary-search
 *
 * facebook
 *
 * @author tukeping
 * @date 2020/3/20
 **/
public class LeetCode278 {

    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (isBadVersion(mid)) end = mid;
            else start = mid + 1;
        }
        return start;
    }

    public int firstBadVersion3(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int firstBadVersion2(int n) {
        return bsearch(1, n);
    }

    private int bsearch(long low, long high) {
        if (low > high) return (int) low;
        long mid = (low + high) / 2;
        if (isBadVersion((int) mid)) {
            return bsearch(low, mid - 1);
        } else {
            return bsearch(mid + 1, high);
        }
    }

    private int badVersion = 4;

    private boolean isBadVersion(int n) {
        return n >= badVersion;
    }

    /**
     * 给定 n = 5，并且 version = 4 是第一个错误的版本。
     *
     * 调用 isBadVersion(3) -> false
     * 调用 isBadVersion(5) -> true
     * 调用 isBadVersion(4) -> true
     *
     * 所以，4 是第一个错误的版本。
     */
    @Test
    public void test1() {
        badVersion = 4;
        int n = firstBadVersion(5);
        assertThat(n, is(4));
    }

    @Test
    public void test2() {
        badVersion = 1702766719;
        int n = firstBadVersion(2126753390);
        assertThat(n, is(1702766719));
    }
}
