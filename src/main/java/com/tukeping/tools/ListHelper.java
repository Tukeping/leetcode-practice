package com.tukeping.tools;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/18
 **/
public class ListHelper {

    public static List<Integer> asList(int[] a) {
        return Arrays.stream(a).boxed().collect(Collectors.toList());
    }

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

    public static void checkInAnyOrder(int[][] actual, int[][] expect) {
        assertThat(toDoubleList(actual), containsInAnyOrder(toDoubleList(expect).toArray()));
    }

    public static List<List<Integer>> toDoubleList(int[][] matrix) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] line : matrix) {
            List<Integer> lineList = new ArrayList<>();
            for (int n : line) {
                lineList.add(n);
            }
            list.add(lineList);
        }
        return list;
    }

    public static List<List<String>> asDoubleList(String[][] matrix) {
        List<List<String>> list = new ArrayList<>();
        for (String[] line : matrix) {
            List<String> lineList = new ArrayList<>(Arrays.asList(line));
            list.add(lineList);
        }
        return list;
    }

    public static void checkDoubleListInAnyOrder(List<List<String>> actual, List<List<String>> expect) {
        boolean b = false;
        int k = 0;
        for (List<String> exLine : expect) {
            for (; k < actual.size(); k++) {
                List<String> acLine = actual.get(k);
                try {
                    assertThat(acLine, containsInAnyOrder(exLine.toArray()));
                    actual.remove(k);
                    b = true;
                    break;
                } catch (AssertionError ignore) {
                }
            }
            if (!b && k == actual.size()) {
                throw new AssertionError(String.format("can't find expect data: %s", exLine));
            }
            b = false;
            k = 0;
        }
    }

    @Test
    public void test1() {
        int[][] actual = {
                {1, 6},
                {1, 2},
                {1, 4}
        };
        int[][] expect = {
                {1, 2},
                {1, 4},
                {1, 6}
        };
        checkInAnyOrder(actual, expect);
    }

    @Test
    public void test2() {
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
