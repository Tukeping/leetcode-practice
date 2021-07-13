package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=1103 lang=java
 *
 * [1103] 分糖果 II
 *
 * https://leetcode-cn.com/problems/distribute-candies-to-people/description/
 *
 * algorithms
 * Easy (61.11%)
 * Likes:    13
 * Dislikes: 0
 * Total Accepted:    5.2K
 * Total Submissions: 8.5K
 * Testcase Example:  '7\n4'
 *
 * 排排坐，分糖果。
 *
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 *
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 *
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
 *
 *
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 *
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i
 * 个小朋友分到的糖果数）。
 *
 * 示例 1：
 *
 * 输入：candies = 7, num_people = 4
 * 输出：[1,2,3,1]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
 * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
 *
 * 示例 2：
 *
 * 输入：candies = 10, num_people = 3
 * 输出：[5,2,3]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3]。
 * 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
 *
 * 提示：
 *
 * 1 <= candies <= 10^9
 * 1 <= num_people <= 1000
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/10
 **/
public class LeetCode1103 {

    /**
     * 27/27 cases passed (1 ms)
     * Your runtime beats 93.08 % of java submissions
     * Your memory usage beats 8.85 % of java submissions (34.1 MB)
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] arr = new int[num_people];

        int give = 1, index = 0;

        // 如果剩下的糖果数量 大于0 则可以继续分糖果
        while (candies > 0) {
            // 如果要给的糖果数大于了手里的糖果数, 则把手里的糖果全部给予最后一个小朋友
            if (candies - give < 0) {
                give = candies;
            }

            // 小朋友拿到give个糖果
            arr[index] += give;

            // 手里的糖果 减去 给出去的糖果 就是剩下的糖果数量
            candies -= give;

            // 每次循环 则要对下一位小朋友的给予糖果数 +1
            give++;

            // 切换到下一个小朋友
            index++;

            // 小朋友是围成一个小圆圈的 所以 所有小朋友都拿到糖果后就从第一个小朋友再开始拿
            if (index == num_people) {
                index = 0;
            }
        }

        return arr;
    }

    /**
     * 输入：candies = 7, num_people = 4
     * 输出：[1,2,3,1]
     * 解释：
     * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
     * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
     * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
     * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
     */
    @Test
    public void test1() {
        int[] ret = distributeCandies(7, 4);
        assertThat(ret, is(new int[]{1, 2, 3, 1}));
    }

    /**
     * 输入：candies = 10, num_people = 3
     * 输出：[5,2,3]
     * 解释：
     * 第一次，ans[0] += 1，数组变为 [1,0,0]。
     * 第二次，ans[1] += 2，数组变为 [1,2,0]。
     * 第三次，ans[2] += 3，数组变为 [1,2,3]。
     * 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
     */
    @Test
    public void test2() {
        int[] ret = distributeCandies(10, 3);
        assertThat(ret, is(new int[]{5, 2, 3}));
    }
}
