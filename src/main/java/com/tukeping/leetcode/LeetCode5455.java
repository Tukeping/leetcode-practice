package com.tukeping.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tukeping
 * @date 2020/7/5
 **/
public class LeetCode5455 {

    public String minInteger(String num, int k) {
        int n = num.length();
        char[] nums = num.toCharArray();
        List<Pair> numList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numList.add(new Pair(nums[i], i));
        }

        numList.sort((o1, o2) -> {
            if (o1.value == o2.value) {
                return o1.index - o2.index;
            } else {
                return o1.value - o2.value;
            }
        });

        StringBuilder best = new StringBuilder();
        for (Pair pair : numList) {
            best.append(pair.value);
        }

        if (best.toString().equals(num)) {
            return best.toString();
        }

        if (k >= (n * (n - 1) / 2)) {
            return best.toString();
        }

        StringBuilder sorted = new StringBuilder();
        Set<Integer> swapNum = new HashSet<>();

        while (k > 0) {
            Pair p = null;
            int removeIndex = -1;
            int nowMove = -1;
            for (int i = 0; i < numList.size(); i++) {
                Pair pair = numList.get(i);
                int offset = 0;
                for (Integer ix : swapNum) {
                    if (ix < pair.index) {
                        offset++;
                    }
                }
                nowMove = pair.index - offset;
                if (nowMove <= k) {
                    p = pair;
                    removeIndex = i;
                    swapNum.add(pair.index);
                    break;
                }
            }
            if (p == null) break;
            sorted.append(p.value);
            numList.remove(removeIndex);
            k -= nowMove;
        }

        for (int i = 0; i < n; i++) {
            if (!swapNum.contains(i)) {
                sorted.append(nums[i]);
            }
        }
        return sorted.toString();
    }

    class Pair {
        char value;
        int index;

        public Pair(char value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
