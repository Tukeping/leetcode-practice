package com.tukeping.leetcode.contest;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/25
 **/
public class TeamContest4 {

    /**
     * 令 f[i] 表示为 数组前i个数字 可以被划分的最少子数组数量。 Min
     **/
    public int splitArray(int[] a) {
//        const int SIZE=(1<<20);
//        VI p(SIZE);
//        for(i,2,SIZE) if (p[i]==0)
//            for (int j=i;j<SIZE;j+=i)
//                p[j]=i;
//        VI g(SIZE,INF);
//        int last_f=0;
//        REP(i,SIZE(a))
//        {
//            int current_f=INF;
//            for (int m=a[i];m>1;)
//            {
//                int x=p[m];
//                ckmin(g[x],last_f);
//                ckmin(current_f,g[x]+1);
//                for (;m%x==0;m/=x);
//            }
//            last_f=current_f;
//        }
//        return last_f;

        int size = 1 << 20;
        int[] p = new int[size];
        for (int i = 2; i < size; i++) {
            if (p[i] == 0) {
                for (int j = i; j < size; j += i) {
                    p[j] = i;
                }
            }
        }
        int[] g = new int[size];
        Arrays.fill(g, Integer.MAX_VALUE);
        int lastF = 0;
        for (int i = 0; i < a.length; i++) {
            int currentF = Integer.MAX_VALUE;
            for (int m = a[i]; m > 1; ) {
                int x = p[m];
                g[x] = Math.min(g[x], lastF);
                currentF = Math.min(currentF, g[x] + 1);
                for (; m % x == 0; m /= x) ;
            }
            lastF = currentF;
        }
        return lastF;

//        int n = nums.length;
//        int[] f = new int[n + 1];
//        f[n] = n - 1;
//        int min = n;
//        for (int i = n; i >= 1; i--) { // i = end
//            for (int j = i - 1; j >= 1; j--) { // j = start
//                f[j] = f[i];
//                if (gcd(nums[i - 1], nums[j - 1]) <= 1) continue;
//                f[i] = f[j] - 1;
//                min = Math.min(min, f[i]);
//            }
//            //System.out.println(String.format("f[%d] = %d", i, f[i]));
//        }
//        return min;
    }

    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    @Test
    public void test1() {
        int n = splitArray(new int[]{2, 3, 3, 2, 3, 3});
        assertThat(n, is(2));
    }

    @Test
    public void test2() {
        int n = splitArray(new int[]{2, 3, 5, 7});
        assertThat(n, is(4));
    }
}
