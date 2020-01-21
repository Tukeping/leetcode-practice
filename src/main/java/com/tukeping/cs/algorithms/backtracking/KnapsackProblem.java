package com.tukeping.cs.algorithms.backtracking;

import com.tukeping.tools.Strings;
import org.junit.Test;

/**
 * 0-1背包问题
 *
 * 问题可以描述为：给定一组物品，每种物品都有自己的重量和价格，
 * 在限定的总重量内，我们如何选择，才能使得物品的总价格最高。
 *
 * @author tukeping
 * @date 2020/1/21
 **/
public class KnapsackProblem {

    /**
     * 回溯算法 - 解决 背包问题中最基础的 物品没有价格只有重量，只需要考虑物品装还是不装
     * 装或不装 表示有2种选择，那么n件物品，就有2^n次方种选择，回溯方法利用了递归把所有的情况全部的枚举出来了。
     *
     * i 当前第几个物品
     * cw 当前背包的重量
     * items 物品列表 (物品重量列表)
     * n 物品个数
     * w 背包能够承受的重量
     */
    private void f(int i, int cw, int[] items, int n, int w, int[] pickedItems, int pickedCount) {
        // 找终止条件: 当前重量 == 承受重量时 或者 拿起的当前物品 在最后一个物品后。
        if (cw == w || i == n) {
            return;
        }
        // 主逻辑: 拿起物品放入背包内
        f(i + 1, cw, items, n, w, pickedItems, pickedCount);
        if (w >= cw + items[i]) { // 如果放入的物品重量 还在 承受重量 之内的话 则继续放入物品，并更新目前已经有的重量之和
            pickedItems[pickedCount++] = items[i];
            f(i + 1, cw + items[i], items, n, w, pickedItems, pickedCount);
        }
    }

    @Test
    public void test() {
        int[] items = new int[]{2, 2, 4, 6, 3};
        int[] pickedItems = new int[items.length];
        int bearWeight = 9;
        f(0, 0, items, 5, bearWeight, pickedItems, 0);
        System.out.println("物品列表: " + Strings.arrayToString(items));
        System.out.println("放入背包的物品(承受重量=" + bearWeight + ", 物品数量尽量的多): " + Strings.arrayToString(pickedItems, 0));
    }
}
