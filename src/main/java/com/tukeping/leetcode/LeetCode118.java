package com.tukeping.leetcode;

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

    public List<List<Integer>> generate(int numRows) {
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
