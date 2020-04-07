package com.tukeping.leetcode.contest183;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/5
 **/
public class LeetCode5379 {

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            sum += stoneValue[i];
            for (int j = i; j < i + 3 && j < n; j++) {
                dp[i] = Math.max(dp[i], sum - dp[j + 1]);
            }
        }
        int alice = dp[0];
        int bob = sum - dp[0];
        if (alice == bob) return "Tie";
        if (alice > bob) return "Alice";
        return "Bob";
    }

    public String stoneGameIII2(int[] stoneValue) {
        int n = stoneValue.length;
        int[] f = new int[n + 1];
        f[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            f[i] = Integer.MIN_VALUE;
            int s = 0;
            for (int j = i; j < n && j < i + 3; j++) {
                s += stoneValue[j];
                f[i] = Math.max(f[i], s - f[j + 1]);
            }
        }
        return f[0] > 0 ? "Alice" : f[0] == 0 ? "Tie" : "Bob";
    }

    /**
     * 输入：values = [1,2,3,7]
     * 输出："Bob"
     * 解释：Alice 总是会输，她的最佳选择是拿走前三堆，得分变成 6 。但是 Bob 的得分为 7，Bob 获胜。
     */
    @Test
    public void test1() {
        String winner = stoneGameIII(new int[]{1, 2, 3, 7});
        assertThat(winner, is("Bob"));
    }

    /**
     * 输入：values = [1,2,3,-9]
     * 输出："Alice"
     * 解释：Alice 要想获胜就必须在第一个回合拿走前三堆石子，给 Bob 留下负分。
     * 如果 Alice 只拿走第一堆，那么她的得分为 1，接下来 Bob 拿走第二、三堆，得分为 5 。之后 Alice 只能拿到分数 -9 的石子堆，输掉比赛。
     * 如果 Alice 拿走前两堆，那么她的得分为 3，接下来 Bob 拿走第三堆，得分为 3 。之后 Alice 只能拿到分数 -9 的石子堆，同样会输掉比赛。
     * 注意，他们都应该采取 最优策略 ，所以在这里 Alice 将选择能够使她获胜的方案。
     */
    @Test
    public void test2() {
        String winner = stoneGameIII(new int[]{1, 2, 3, -9});
        assertThat(winner, is("Alice"));
    }

    /**
     * 输入：values = [1,2,3,6]
     * 输出："Tie"
     * 解释：Alice 无法赢得比赛。如果她决定选择前三堆，她可以以平局结束比赛，否则她就会输。
     */
    @Test
    public void test3() {
        String winner = stoneGameIII(new int[]{1, 2, 3, 6});
        assertThat(winner, is("Tie"));
    }

    /**
     * 输入：values = [1,2,3,-1,-2,-3,7]
     * 输出："Alice"
     */
    @Test
    public void test4() {
        String winner = stoneGameIII(new int[]{1, 2, 3, -1, -2, -3, 7});
        assertThat(winner, is("Alice"));
    }

    /**
     * 输入：values = [-1,-2,-3]
     * 输出："Tie"
     */
    @Test
    public void test5() {
        String winner = stoneGameIII(new int[]{-1, -2, -3});
        assertThat(winner, is("Tie"));
    }
}
