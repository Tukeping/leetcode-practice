package com.tukeping.leetcode.lc;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author tukeping
 * @date 2020/4/18
 **/
public class Contest3 {

    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int reqLen = requirements.length;
        int len = increase.length;

        int[] minHeapC = new int[reqLen];
        int[] minHeapR = new int[reqLen];
        int[] minHeapH = new int[reqLen];

        int idx = 0;
        for (int[] requirement : requirements) {
            minHeapC[idx] = requirement[0];
            minHeapR[idx] = requirement[1];
            minHeapH[idx] = requirement[2];
            idx++;
        }

        Arrays.sort(minHeapC);
        Arrays.sort(minHeapR);
        Arrays.sort(minHeapH);

        int[] ans = new int[reqLen];
        Arrays.fill(ans, -1);
        int[] status = new int[3];

        int mc = 0, mr = 0, mh = 0;
        for (int j = 0; j < reqLen; j++) {
            if (ans[j] != -1 ||
                    status[0] < minHeapC[mc] ||
                    status[1] < minHeapR[mr] ||
                    status[2] < minHeapH[mh])
                continue;

            if (status[0] < requirements[j][0])
                continue;

            if (status[1] < requirements[j][1])
                continue;

            if (status[2] < requirements[j][2])
                continue;

            ans[j] = 0;

            while (requirements[j][0] > minHeapC[mc]) {
                mc++;
            }
            while (requirements[j][1] > minHeapR[mr]) {
                mr++;
            }
            while (requirements[j][2] > minHeapH[mh]) {
                mh++;
            }
        }

        for (int i = 0; i < len; i++) {
            status[0] += increase[i][0];
            status[1] += increase[i][1];
            status[2] += increase[i][2];

            for (int j = 0; j < reqLen; j++) {
                if (ans[j] != -1 ||
                        status[0] < minHeapC[mc] ||
                        status[1] < minHeapR[mr] ||
                        status[2] < minHeapH[mh])
                    continue;

                if (status[0] < requirements[j][0])
                    continue;

                if (status[1] < requirements[j][1])
                    continue;

                if (status[2] < requirements[j][2])
                    continue;

                ans[j] = i + 1;

                while (requirements[j][0] > minHeapC[mc]) {
                    mc++;
                }
                while (requirements[j][1] > minHeapR[mr]) {
                    mr++;
                }
                while (requirements[j][2] > minHeapH[mh]) {
                    mh++;
                }
            }
        }
        return ans;
    }

    @Test
    public void test1() {
        int[][] i = {
                {2, 8, 4}, {2, 5, 0}, {10, 9, 8}
        };
        int[][] r = {
                {2, 11, 3}, {15, 10, 7}, {9, 17, 12}, {8, 1, 14}
        };
        int[] ans = getTriggerTime(i, r);
        for (int x : ans) {
            System.out.print(x + " ");
        }
    }

    /**
     * 输入： increase = [[0,4,5],[4,8,8],[8,6,1],[10,10,0]]
     *       requirements = [[12,11,16],[20,2,6],[9,2,6],[10,18,3],[8,14,9]]
     * 输出: [-1,4,3,3,3]
     */
    @Test
    public void test2() {
        int[][] i = {
                {0, 4, 5}, {4, 8, 8}, {8, 6, 1}, {10, 10, 0}
        };
        int[][] r = {
                {12, 11, 16}, {20, 2, 6}, {9, 2, 6}, {10, 18, 3}, {8, 14, 9}
        };
        int[] ans = getTriggerTime(i, r);
        for (int x : ans) {
            System.out.print(x + " ");
        }
    }
}
