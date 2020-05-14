package com.tukeping.cp.leetcode.contest.weekly.contest187;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/3
 **/
public class LeetCode5400 {

    String city = null;

    public String destCity(List<List<String>> paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> path : paths) {
            if (!map.containsKey(path.get(0))) {
                map.put(path.get(0), new ArrayList<>());
            }
            map.get(path.get(0)).add(path.get(1));
        }
        String start = paths.get(0).get(0);
        dfs(start, map);
        return city;
    }

    private void dfs(String start, Map<String, List<String>> map) {
        List<String> toPaths = map.get(start);
        if (toPaths == null || toPaths.isEmpty()) {
            this.city = start;
            return;
        }
        for (String path : toPaths) {
            dfs(path, map);
            if (city != null) return;
        }
    }

    /**
     * 输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
     * 输出："Sao Paulo"
     * 解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao Paulo" 。
     */
    @Test
    public void test1() {
        String[][] pathArr = {
                {"London", "New York"}, {"New York", "Lima"}, {"Lima", "Sao Paulo"}
        };
        List<List<String>> paths = ListHelper.asDoubleList(pathArr);
        String s = destCity(paths);
        assertThat(s, is("Sao Paulo"));
    }

    /**
     * 输入：paths = [["B","C"],["D","B"],["C","A"]]
     * 输出："A"
     * 解释：所有可能的线路是：
     * "D" -> "B" -> "C" -> "A".
     * "B" -> "C" -> "A".
     * "C" -> "A".
     * "A".
     * 显然，旅行终点站是 "A" 。
     */
    @Test
    public void test2() {
        String[][] pathArr = {
                {"B", "C"}, {"D", "B"}, {"C", "A"}
        };
        List<List<String>> paths = ListHelper.asDoubleList(pathArr);
        String s = destCity(paths);
        assertThat(s, is("A"));
    }

    /**
     * 输入：paths = [["A","Z"]]
     * 输出："Z"
     */
    @Test
    public void test3() {
        String[][] pathArr = {
                {"A", "Z"}
        };
        List<List<String>> paths = ListHelper.asDoubleList(pathArr);
        String s = destCity(paths);
        assertThat(s, is("Z"));
    }
}
