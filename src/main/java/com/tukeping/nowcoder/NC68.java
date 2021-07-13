package com.tukeping.nowcoder;

/**
 * @author tukeping
 * @date 2021/7/5
 **/
public class NC68 {

    public int jumpFloor(int target) {
        // f(x) = f(x-1) + f(x-2);
        // f(1) = 1
        // f(2) = 2
        // f(3) = f(2) + f(1) = 3
        // f(4) = f(3) + f(2) = 3 + 2 = 5
        // f(5) = f(4) + f(3) = 5 + 3 = 8
        // f(6) = f(5) + f(4) = 8 + 5 = 13
        // f(7) = f(6) + f(5) = 13 + 8 = 21

        if (target == 1) return 1;
        if (target == 2) return 2;

        // 从低向上 + 空间压缩
        int x1 = 1;
        int x2 = 2;
        int x3 = -1;

        for (int i = 3; i <= target; i++) {
            x3 = x1 + x2;
            x1 = x2;
            x2 = x3;
        }

        return x3;

        // 从低向上
//         int[] ret = new int[target + 1];
//         ret[1] = 1;
//         ret[2] = 2;

//         for(int i = 3; i <= target; i++) {
//             ret[i] = ret[i - 1] + ret[i - 2];
//         }

//         return ret[target];

        // 从顶向下
//         return f(target);
    }

    private int f(int x) {
        if (x == 1) return 1;
        if (x == 2) return 2;
        return f(x - 1) + f(x - 2);
    }
}
