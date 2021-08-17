package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=72 lang=java
 *
 * [72] 编辑距离
 *
 * https://leetcode-cn.com/problems/edit-distance/description/
 *
 * algorithms
 * Hard (55.31%)
 * Likes:    568
 * Dislikes: 0
 * Total Accepted:    32.8K
 * Total Submissions: 58.1K
 * Testcase Example:  '"horse"\n"ros"'
 *
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 示例 1:
 *
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 *
 * 示例 2:
 *
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 *
 */

import com.tukeping.tools.DPHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * string | dynamic-programming
 *
 * @author tukeping
 * @date 2020/2/15
 **/
public class LeetCode72 {

    public int minDistanceV2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(
                            dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1),
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1)
                    );
                }
            }
        }
        return dp[m][n];
    }

    public int minDistance(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= m; i++) f[0][i] = i;
        for (int i = 0; i <= n; i++) f[i][0] = i;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = f[i - 1][j - 1];
                if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
                    f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
                }
            }
        }
        DPHelper.print(f, s1, s2);
        return f[n][m];
    }

    public int minDistance4(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // if one of the strings is empty
        if (n * m == 0)
            return n + m;

        // array to store the convertion history
        int[][] d = new int[n + 1][m + 1];

        // init boundaries
        for (int i = 0; i < n + 1; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            d[0][j] = j;
        }

        // DP compute
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = d[i - 1][j] + 1;
                int down = d[i][j - 1] + 1;
                int left_down = d[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1))
                    left_down += 1;
                d[i][j] = Math.min(left, Math.min(down, left_down));

            }
        }
        return d[n][m];
    }

    public int minDistance3(String word1, String word2) {
        if (word1.isEmpty() && word2.isEmpty()) return 0;
        if (word1.isEmpty() || word2.isEmpty()) return word1.isEmpty() ? word2.length() : word1.length();
        return lwstDP(word1.toCharArray(), word1.length(), word2.toCharArray(), word2.length());
    }

    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; ++j) { // 初始化第0行:a[0..0]与b[0..j]的编辑距离
            if (a[0] == b[j]) minDist[0][j] = j;
            else if (j != 0) minDist[0][j] = minDist[0][j - 1] + 1;
            else minDist[0][j] = 1;
        }
        for (int i = 0; i < n; ++i) { // 初始化第0列:a[0..i]与b[0..0]的编辑距离
            if (a[i] == b[0]) minDist[i][0] = i;
            else if (i != 0) minDist[i][0] = minDist[i - 1][0] + 1;
            else minDist[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) { // 按行填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
                else minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
            }
        }
        return minDist[n - 1][m - 1];
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) minv = x;
        if (y < minv) minv = y;
        if (z < minv) minv = z;
        return minv;
    }

    private int n, m, minDist = Integer.MAX_VALUE;
    private char[] w1, w2;

    public int minDistance2(String word1, String word2) {
        this.w1 = word1.toCharArray();
        this.w2 = word2.toCharArray();
        this.n = word1.length();
        this.m = word2.length();
        lwstBT(0, 0, 0);
        return minDist;
    }

    private void lwstBT(int i, int j, int edist) {
        if (i == n || j == m) {
            if (i < n) edist += n - i;
            if (j < m) edist += m - j;
            if (edist < minDist) minDist = edist;
            return;
        }
        if (w1[i] == w2[j]) {
            lwstBT(i + 1, j + 1, edist);
        } else {
            lwstBT(i + 1, j, edist + 1);
            lwstBT(i, j + 1, edist + 1);
            lwstBT(i + 1, j + 1, edist + 1);
        }
    }

    /**
     * 输入: word1 = "horse", word2 = "ros"
     * 输出: 3
     * 解释:
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     */
    @Test
    public void test1() {
        int n = minDistance("horse", "ros");
        assertThat(n, is(3));
    }

    /**
     * 输入: word1 = "intention", word2 = "execution"
     * 输出: 5
     * 解释:
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     */
    @Test
    public void test2() {
        int n = minDistance("intention", "execution");
        assertThat(n, is(5));
    }

    @Test
    public void test3() {
        int n = minDistance("dinitrophenylhydrazine", "acetylphenylhydrazine");
        assertThat(n, is(6));
    }

    @Test
    public void test4() {
        int n = minDistance("", "");
        assertThat(n, is(0));
    }

    @Test
    public void test5() {
        int n = minDistance("", "a");
        assertThat(n, is(1));
    }

    @Test
    public void test6() {
        int n = minDistance("a", "");
        assertThat(n, is(1));
    }
}
