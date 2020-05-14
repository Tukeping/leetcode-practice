package com.tukeping.cp.leetcode.contest;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/18
 **/
public class Contest4 {

    public int minJump(int[] jump) {
        int[] steps = new int[jump.length];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int last = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u + jump[u] >= jump.length) {
                return steps[u] + 1;
            }
            for (int i = last + 1; i < u; i++) {
                if (steps[i] == 0) {
                    steps[i] = steps[u] + 1;
                    q.add(i);
                }
            }
            last = u;
            int v = u + jump[u];
            if (steps[v] == 0) {
                steps[v] = steps[u] + 1;
                q.add(v);
            }
        }
        return -1;
    }

    public int minJump2(int[] jump) {
        int[] d = new int[1000005];
        Arrays.fill(d, -1);
        d[0] = 0;

        int he, ta;
        he = ta = 0;

        int[] q = new int[1000005];
        q[ta++] = 0;

        int n = jump.length, i = 0, l = 0;
        while (he != ta) {
            i = q[he++];
            if (i + jump[i] >= n) break;
            if (~d[i + jump[i]] != 0)
                d[q[ta++] = i + jump[i]] = d[i] + 1;
            for (; l < i; l++)
                if (~d[l] != 0)
                    d[q[ta++] = l] = d[i] + 1;
        }
        return d[i] + 1;
    }

    @Test
    public void test1() {
        int n = minJump(new int[]{2, 5, 1, 1, 1, 1});
        assertThat(n, is(3));
    }
}
