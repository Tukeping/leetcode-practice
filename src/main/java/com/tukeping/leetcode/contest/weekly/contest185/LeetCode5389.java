package com.tukeping.leetcode.contest.weekly.contest185;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author tukeping
 * @date 2020/4/19
 **/
public class LeetCode5389 {

    static class Pair<K, V> {
        public K first;
        public V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public static <K, V> Pair<K, V> of(K key, V value) {
            return new Pair<>(key, value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) &&
                    Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<Integer> tableSet = new HashSet<>();
        Set<String> foodSet = new HashSet<>();
        Map<Pair<Integer, String>, Integer> foodCnt = new HashMap<>();

        for (List<String> order : orders) {
            String idx = order.get(1);
            String food = order.get(2);

            int idxInt = Integer.parseInt(idx);
            tableSet.add(idxInt);
            foodSet.add(food);
            Pair pair = Pair.of(idxInt, food);
            foodCnt.put(pair, foodCnt.getOrDefault(pair, 0) + 1);
        }

        List<Integer> tableIdx = new ArrayList<>(tableSet);
        Collections.sort(tableIdx);

        List<String> foods = new ArrayList<>(foodSet);
        Collections.sort(foods);

        List<List<String>> res = new ArrayList<>();

        List<String> title = new ArrayList<>();
        title.add("Table");
        title.addAll(foods);
        res.add(title);

        for (Integer idx : tableIdx) {
            List<String> line = new ArrayList<>();
            line.add(String.valueOf(idx));

            for (String food : foods) {
                Integer fCnt = foodCnt.getOrDefault(Pair.of(idx, food), 0);
                line.add(String.valueOf(fCnt));
            }

            res.add(line);
        }
        return res;
    }

    /**
     * Table,Beef Burrito,Ceviche,Fried Chicken,Water
     * 3    ,0           ,2      ,1            ,0
     * 5    ,0           ,1      ,0            ,1
     * 10   ,1           ,0      ,0            ,0
     */
    @Test
    public void test1() {
        String[][] orders = {
                {"David", "3", "Ceviche"}, {"Corina", "10", "Beef Burrito"}, {"David", "3", "Fried Chicken"}, {"Carla", "5", "Water"}, {"Carla", "5", "Ceviche"}, {"Rous", "3", "Ceviche"}
        };
        ListHelper.asDoubleList(orders);
        List<List<String>> orderList = ListHelper.asDoubleList(orders);
        List<List<String>> ans = displayTable(orderList);
        for (List<String> x : ans) {
            System.out.println(x);
        }
    }
}
