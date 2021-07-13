package com.tukeping.leetcode.problems;

/*
 * 1389. 按既定顺序创建目标数组
 *
 * 给你两个整数数组 nums 和 index。你需要按照以下规则创建目标数组：
 *
 * 目标数组 target 最初为空。
 * 按从左到右的顺序依次读取 nums[i] 和 index[i]，在 target 数组中的下标 index[i] 处插入值 nums[i] 。
 * 重复上一步，直到在 nums 和 index 中都没有要读取的元素。
 * 请你返回目标数组。
 *
 * 题目保证数字插入位置总是存在。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [0,1,2,3,4], index = [0,1,2,2,1]
 * 输出：[0,4,1,3,2]
 * 解释：
 * nums       index     target
 * 0            0        [0]
 * 1            1        [0,1]
 * 2            2        [0,1,2]
 * 3            2        [0,1,3,2]
 * 4            1        [0,4,1,3,2]
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4,0], index = [0,1,2,3,0]
 * 输出：[0,1,2,3,4]
 * 解释：
 * nums       index     target
 * 1            0        [1]
 * 2            1        [1,2]
 * 3            2        [1,2,3]
 * 4            3        [1,2,3,4]
 * 0            0        [0,1,2,3,4]
 * 示例 3：
 *
 * 输入：nums = [1], index = [0]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length, index.length <= 100
 * nums.length == index.length
 * 0 <= nums[i] <= 100
 * 0 <= index[i] <= i
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/24
 **/
public class LeetCode1389 {

    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> h = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            h.add(index[i], nums[i]);
        }
        int[] ret = new int[h.size()];
        for (int i = 0; i < h.size(); i++) {
            ret[i] = h.get(i);
        }
        return ret;
    }

    public int[] createTargetArray2(int[] nums, int[] index) {
        int len = nums.length;
        int[] ret = new int[len];
        int size = 0;
        for (int i = 0; i < len; i++) {
            if (index[i] >= size) {
                ret[size] = nums[i];
            } else {
                System.arraycopy(ret, index[i], ret, index[i] + 1, size - index[i]);
                ret[index[i]] = nums[i];
            }
            size++;
        }
        return ret;
    }

    /**
     * 输入：nums = [0,1,2,3,4], index = [0,1,2,2,1]
     * 输出：[0,4,1,3,2]
     * 解释：
     * nums       index     target
     * 0            0        [0]
     * 1            1        [0,1]
     * 2            2        [0,1,2]
     * 3            2        [0,1,3,2]
     * 4            1        [0,4,1,3,2]
     */
    @Test
    public void test1() {
        int[] ret = createTargetArray(new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 2, 2, 1});
        assertThat(ret, is(new int[]{0, 4, 1, 3, 2}));
    }

    /**
     * 输入：nums = [1,2,3,4,0], index = [0,1,2,3,0]
     * 输出：[0,1,2,3,4]
     * 解释：
     * nums       index     target
     * 1            0        [1]
     * 2            1        [1,2]
     * 3            2        [1,2,3]
     * 4            3        [1,2,3,4]
     * 0            0        [0,1,2,3,4]
     */
    @Test
    public void test2() {
        int[] ret = createTargetArray(new int[]{1, 2, 3, 4, 0}, new int[]{0, 1, 2, 3, 0});
        assertThat(ret, is(new int[]{0, 1, 2, 3, 4}));
    }

    /**
     * 输入：nums = [1], index = [0]
     * 输出：[1]
     */
    @Test
    public void test3() {
        int[] ret = createTargetArray(new int[]{1}, new int[]{0});
        assertThat(ret, is(new int[]{1}));
    }
}
