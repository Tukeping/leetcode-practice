package com.tukeping.leetcode.problems;

import java.util.PriorityQueue;

/**
 * @author tukeping
 * @date 2021/8/8
 **/
public class LeetCode5839 {

    public int minStoneSum(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            pq.add(num);
        }
        while (k-- > 0) {
            int val = pq.poll();
            if (val % 2 == 0) {
                pq.offer(val / 2);
            } else {
                pq.offer(val / 2 + 1);
            }
        }
        int ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        return ans;
    }
}
