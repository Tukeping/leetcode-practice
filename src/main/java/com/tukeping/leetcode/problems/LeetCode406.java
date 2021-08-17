package com.tukeping.leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tukeping
 * @date 2021/8/15
 **/
public class LeetCode406 {

    /**
     * 解题思路：先排序再插入
     * 1.排序规则：按照先H高度降序，K个数升序排序
     * 2.遍历排序后的数组，根据K插入到K的位置上
     *
     * 核心思想：高个子先站好位，矮个子插入到K位置上，前面肯定有K个高个子，矮个子再插到前面也满足K的要求
     */
    public int[][] reconstructQueue(int[][] people) {
        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        // ----------------------------------------
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[0][0]);
    }

    /**
     * 先对输入数组排序，h升序，k降序 从头循环遍历 当前这个人就是剩下未安排的人中最矮的人，
     * 他的k值就代表他在剩余空位的索引值 如果有多个人高度相同，要按照k值从大到小领取索引值
     * 示例：
     * [ 0, 1, 2, 3, 4, 5 ] [ 4, 4 ] 4
     * [ 0, 1, 2, 3, 5 ]    [ 5, 2 ] 2
     * [ 0, 1, 3, 5 ]       [ 5, 0 ] 0
     * [ 1, 3, 5 ]          [ 6, 1 ] 3
     * [ 1, 5 ]             [ 7, 1 ] 5
     * [ 1 ]                [ 7, 0 ] 1
     * [ [ 5, 0 ], [ 7, 0 ], [ 5, 2 ], [ 6, 1 ], [ 4, 4 ], [ 7, 1 ] ]
     */
    public int[][] reconstructQueueV2(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        int n = people.length;
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) arr.add(i);
        int[][] ret = new int[n][2];
        for (int[] p : people) {
            int idx = arr.remove(p[1]);
            ret[idx] = p;
        }
        return ret;
    }
}
