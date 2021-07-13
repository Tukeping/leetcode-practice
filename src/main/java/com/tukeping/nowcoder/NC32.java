package com.tukeping.nowcoder;

/**
 * @author tukeping
 * @date 2021/7/8
 **/
public class NC32 {

    /**
     *
     * @param x int整型
     * @return int整型
     */
    public int sqrt(int x) {
        if (x <= 1) return x;
        long r = x;
        while (x / r < r) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

    /**
     *
     * @param x int整型
     * @return int整型
     */
    public int sqrtV2(int x) {
        int i = 1;
        int res = 0;
        while (x >= 0) {
            x -= i;
            res++;
            i += 2;
        }
        return res - 1;
    }
}
