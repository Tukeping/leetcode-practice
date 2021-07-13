package com.tukeping.nowcoder;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/5
 **/
public class NC22 {

    public void merge(int A[], int m, int B[], int n) {
        int p1 = 0;
        int p2 = 0;
        int pos = m;
        while (p1 < m || p2 < n) {
            if (p2 == n) {
                break;
            } else if (p1 == pos) {
                A[p1++] = B[p2++];
                pos++;
            } else {
                if (A[p1] > B[p2]) {
                    for (int i = pos - 1; i >= p1; i--) {
                        A[i + 1] = A[i];
                    }
                    A[p1++] = B[p2++];
                    pos++;
                } else {
                    p1++;
                }
            }
        }
    }

    @Test
    public void test() {
        int[] actual = new int[]{4, 5, 6, 0, 0, 0};
        merge(actual, 3, new int[]{1, 2, 3}, 3);
        ListHelper.assertThat(actual, new int[]{1, 2, 3, 4, 5, 6});
    }

    @Test
    public void test2() {
        int[] actual = new int[]{1, 2, 3, 0, 0, 0};
        merge(actual, 3, new int[]{2, 5, 6}, 3);
        ListHelper.assertThat(actual, new int[]{1, 2, 2, 3, 5, 6});
    }
}
