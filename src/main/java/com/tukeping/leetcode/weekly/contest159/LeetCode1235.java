package com.tukeping.leetcode.weekly.contest159;

/*
 * Explanation
 * Sort the jobs by endTime.
 *
 * dp[time] = profit means that within the first time duration,
 * we cam make at most profit money.
 * Intial dp[0] = 0, as we make profit = 0 at time = 0.
 *
 * For each job = [s, e, p], where s,e,p are its start time, end time and profit,
 * Then the logic is similar to the knapsack problem.
 * If we don't do this job, nothing will be changed.
 * If we do this job, binary search in the dp to find the largest profit we can make before start time s.
 * So we also know the maximum cuurent profit that we can make doing this job.
 *
 * Compare with last element in the dp,
 * we make more money,
 * it worth doing this job,
 * then we add the pair of [e, cur] to the back of dp.
 * Otherwise, we'd like not to do this job.
 *
 *
 * Complexity
 * Time O(NlogN) for sorting
 * Time O(NlogN) for binary search for each job
 * Space O(N)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * @author tukeping
 * @date 2020/4/14
 **/
public class LeetCode1235 {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // sort by endTime
        int[][] items = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            items[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(items, (a1, a2) -> a1[1] - a2[1]);
        List<Integer> dpEndTime = new ArrayList<>();
        List<Integer> dpProfit = new ArrayList<>();
        // init value to avoid IndexOutBoundExp
        dpEndTime.add(0);
        dpProfit.add(0);
        for (int[] item : items) {
            int s = item[0], e = item[1], p = item[2];
            // find previous endTime index
            int prevIdx = Collections.binarySearch(dpEndTime, s + 1);
            if (prevIdx < 0) {
                prevIdx = -prevIdx - 1;
            }
            prevIdx--;
            int currProfit = dpProfit.get(prevIdx) + p, maxProfit = dpProfit.get(dpProfit.size() - 1);
            if (currProfit > maxProfit) {
                dpProfit.add(currProfit);
                dpEndTime.add(e);
            }
        }
        return dpProfit.get(dpProfit.size() - 1);
    }

    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] job : jobs) {
            int cur = dp.floorEntry(job[0]).getValue() + job[2];
            if (cur > dp.lastEntry().getValue())
                dp.put(job[1], cur);
        }
        return dp.lastEntry().getValue();
    }
}
