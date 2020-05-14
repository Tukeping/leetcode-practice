package com.tukeping.cp.leetcode.contest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tukeping
 * @date 2020/4/25
 **/
public class TeamContest2 {

    public int minTime2(int[] time, int m) {
        int n = time.length;
        if(m >= n) return 0;

        // 前缀和
        int[] s = new int[n];
        s[0] = time[0];
        for(int i = 1; i < n; i++) {
            s[i] = time[i];
            s[i] += s[i - 1];
        }

        // 分m个桶，每个桶间 差值最小

        Map<Integer, Integer> bucket = new HashMap<>(m, 1.0F);
        int size = n / m;

        return 0;
    }

    public int minTime(int[] time, int m) {
        int n = time.length;
        IntBinarySearch ibs = new IntBinarySearch() {
            @Override
            public boolean check(int mid) {
                int day = 0;
                for (int i = 0; i < n; i++) {
                    int j = i;
                    int sum = time[j];
                    int max = time[j];
                    while (j + 1 < n &&
                            sum + time[j + 1] - Math.max(max, time[j + 1]) <= mid) {
                        sum += time[j + 1];
                        max = Math.max(max, time[j + 1]);
                        j++;
                    }
                    i = j;
                    day++;
                }
                return day <= m;
            }
        };

        int ans = ibs.binarySearch(0, (int) 1e9 + 10);
        return ans;
    }

    abstract class IntBinarySearch {
        public abstract boolean check(int mid);

        public int binarySearch(int l, int r) {
            if (l > r) {
                throw new IllegalArgumentException();
            }
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (check(mid)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }
    }
}
