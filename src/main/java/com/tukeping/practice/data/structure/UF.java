package com.tukeping.practice.data.structure;

import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/31
 **/
public class UF {

    static class QuickFind {
        private final int[] id;

        public QuickFind(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }

        public void union(int p, int q) {
            int pid = id[p];
            int qid = id[q];
            for (int i = 0; i < id.length; i++) {
                if (id[i] == pid) id[i] = qid;
            }
        }

        public boolean connected(int p, int q) {
            return id[p] == id[q];
        }
    }

    static class QuickUnion {
        private final int[] id;

        public QuickUnion(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }

        private int root(int i) {
            while (i != id[i]) i = id[i];
            return i;
        }

        public void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            id[i] = j;
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }
    }

    static class WeightedUnionFind {
        private final int[] id;
        private final int[] sz;

        public WeightedUnionFind(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }

        private int root(int i) {
            while (i != id[i]) i = id[i];
            return i;
        }

        public void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            if (i == j) return;
            if (sz[i] < sz[j]) {
                id[i] = j;
                sz[j] += sz[i];
            } else {
                id[j] = i;
                sz[i] += sz[j];
            }
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }
    }

    static class PathCompressionUnionFind {
        private final int[] id;

        public PathCompressionUnionFind(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }

        private int root(int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }

        public void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            id[i] = j;
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }
    }

    static class WeightedPathCompressionUnionFind {
        private final int[] id;
        private final int[] sz;

        public WeightedPathCompressionUnionFind(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }

        private int root(int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }

        public void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            if (i == j) return;
            if (sz[i] < sz[j]) {
                id[i] = j;
                sz[j] += sz[i];
            } else {
                id[j] = i;
                sz[i] += sz[j];
            }
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }
    }

    @Test
    public void test() {
//        QuickFind UF = new QuickFind(10);
//        QuickUnion UF = new QuickUnion(10);
//        WeightedUnionFind UF = new WeightedUnionFind(10);
//        PathCompressionUnionFind UF = new PathCompressionUnionFind(10);
        WeightedPathCompressionUnionFind UF = new WeightedPathCompressionUnionFind(10);

        UF.union(4, 3);
        UF.union(3, 8);
        UF.union(6, 5);
        UF.union(9, 4);
        UF.union(2, 1);
        assert !UF.connected(0, 7);
        assert UF.connected(8, 9);
        UF.union(5, 0);
        UF.union(7, 2);
        UF.union(6, 1);
        UF.union(1, 0);
        assert UF.connected(0, 7);
    }
}
