package com.tukeping.tools;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

/**
 * @author tukeping
 * @date 2020/2/18
 **/
public class ListHelper {

    public static List<Integer> asList(int[] a) {
        return Arrays.stream(a).boxed().collect(Collectors.toList());
    }

    public static int[] asArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[][] asTwoDimArray(List<List<Integer>> list) {
        int[][] a = new int[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            List<Integer> subList = list.get(i);
            int[] b = new int[subList.size()];
            for (int j = 0; j < subList.size(); j++) {
                b[j] = subList.get(j);
            }
            a[i] = b;
        }

        return a;
    }

    public static int[][] asTwoDimArray(ArrayList<ArrayList<Integer>> list) {
        int[][] a = new int[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            List<Integer> subList = list.get(i);
            int[] b = new int[subList.size()];
            for (int j = 0; j < subList.size(); j++) {
                b[j] = subList.get(j);
            }
            a[i] = b;
        }

        return a;
    }

    public static void assertThat(int[] actual, int[] expect) {
        int n = actual.length;
        int m = expect.length;
        if (n != m) {
            throw new AssertionError("actual size not equals expect size");
        }
        for (int i = 0; i < n; i++) {
            if (actual[i] != expect[i]) {
                throw new AssertionError("iTh element not equals, i = " + i + ", actual = " + actual[i] + ", expect = " + expect[i]);
            }
        }
    }

    public static void assertThatTwoDim(int[][] actual, int[][] expect) {
        int aD1Len = actual.length;
        int eD1Len = expect.length;

        Assert.assertThat(aD1Len, is(eD1Len));

        for (int i = 0; i < expect.length; i++) {
            for (int j = 0; j < expect[i].length; j++) {
                Assert.assertThat(actual[i][j], is(expect[i][j]));
            }
        }
    }

    public static void checkInAnyOrder(int[][] actual, int[][] expect) {
        Assert.assertThat(toDoubleList(actual), containsInAnyOrder(toDoubleList(expect).toArray()));
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

    public static List<List<Integer>> asIntegerList(int[][] a) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] line : a) {
            List<Integer> lineList = new ArrayList<>();
            for (int val : line) {
                lineList.add(val);
            }
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
                    Assert.assertThat(acLine, containsInAnyOrder(exLine.toArray()));
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

        assertThatTwoDim(actual, expect);
    }
}
