package com.tukeping.tools;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/18
 **/
public class ListHelper {

    public static int[][] asTwoDimArray(List<List<Integer>> list) {
        int maxD2Len = Integer.MIN_VALUE;

        for (int i = 0; i < list.size(); i++) {
            maxD2Len = Math.max(maxD2Len, list.get(i).size());
        }

        int[][] a = new int[list.size()][maxD2Len];

        for (int i = 0; i < list.size(); i++) {
            List<Integer> innerList = list.get(i);
            for (int j = 0; j < innerList.size(); j++) {
                a[i][j] = innerList.get(j);
            }
        }

        return a;
    }

    public static void check(int[][] actual, int[][] expect) {
        int aD1Len = actual.length;
        int eD1Len = expect.length;

        assertThat(aD1Len, is(eD1Len));

        for (int i = 0; i < expect.length; i++) {
            for (int j = 0; j < expect[i].length; j++) {
                assertThat(actual[i][j], is(expect[i][j]));
            }
        }
    }

    @Test
    public void test() {
        List<List<Integer>> list = new ArrayList<>();

        List<Integer> innerList = new ArrayList<>();
        innerList.add(3);
        list.add(innerList);

        innerList = new ArrayList<>();
        innerList.add(9);
        innerList.add(20);
        list.add(innerList);

        innerList = new ArrayList<>();
        innerList.add(15);
        innerList.add(7);
        list.add(innerList);

        int[][] actual = asTwoDimArray(list);
        int[][] expect = {
                {3},
                {9, 20},
                {15, 7}
        };

        check(actual, expect);
    }
}
