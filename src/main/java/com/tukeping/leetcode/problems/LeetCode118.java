package com.tukeping.leetcode.problems;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/12
 **/
public class LeetCode118 {

    public List<List<Integer>> generateV2(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        int cnt = 0;
        while (cnt < n) {
            if (cnt == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                ret.add(list);
            } else if (cnt == 1) {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(1);
                ret.add(list);
            } else {
                List<Integer> list = new ArrayList<>();
                List<Integer> prev = ret.get(cnt - 1);
                for (int i = 0; i <= cnt; i++) {
                    if (i == 0 || i == cnt) {
                        list.add(1);
                    } else {
                        list.add(prev.get(i - 1) + prev.get(i));
                    }
                }
                ret.add(list);
            }
            cnt++;
        }
        return ret;
    }

    public List<List<Integer>> generate(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 1) return res;
        generate(n - 1, res);
        return res;
    }

    private void generate(int n, List<List<Integer>> res) {
        if (n == 0) {
            res.add(Arrays.asList(1));
            return;
        }

        generate(n - 1, res);

        List<Integer> line = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            line.add(f(n, i, res));
        }
        res.add(line);
    }

    // f(i, j) = f(i - 1, j - 1) + f(i - 1, j)
    private int f(int i, int j, List<List<Integer>> res) {
        if (i == 0 || j == 0 || i == j) return 1;
        return res.get(i - 1).get(j - 1) + res.get(i - 1).get(j);
    }

    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        if (numRows >= 1)
            ans.add(Arrays.asList(1));
        if (numRows >= 2)
            ans.add(Arrays.asList(1, 1));

        for (int i = 2; i < numRows; i++) {
            List<Integer> line = new ArrayList<>();
            List<Integer> preLine = ans.get(i - 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    line.add(1);
                } else {
                    int x = preLine.get(j - 1);
                    int y = preLine.get(j);
                    line.add(x + y);
                }
            }
            ans.add(line);
        }
        return ans;
    }

    /**
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     */
    @Test
    public void test1() {
        List<List<Integer>> ans = generate(5);
        int[][] actual = ListHelper.asTwoDimArray(ans);
        int[][] expect = {
                {1},
                {1, 1},
                {1, 2, 1},
                {1, 3, 3, 1},
                {1, 4, 6, 4, 1}
        };
        assertThat(actual, is(expect));
    }
}
