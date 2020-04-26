package com.tukeping.leetcode.contest;

import java.util.TreeSet;

/**
 * @author tukeping
 * @date 2020/4/25
 **/
public class TeamContest1 {

    public int expectNumber(int[] scores) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int s : scores) {
            set.add(s);
        }
        return set.size();
    }
}
