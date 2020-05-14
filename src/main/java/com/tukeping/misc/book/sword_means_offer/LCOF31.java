package com.tukeping.misc.book.sword_means_offer;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/3
 **/
public class LCOF31 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        int[] stack = new int[len];
        for (int si = 0, ps = 0, pp = 0; ps < len || si > 0; ) {
            if (si != 0 && stack[si - 1] == popped[pp]) {
                pp++;
                si--;
            } else if (ps < len) {
                stack[si++] = pushed[ps++];
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     */
    @Test
    public void test1() {
        assertTrue(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
    }

    /**
     * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * 输出：false
     * 解释：1 不能在 2 之前弹出。
     */
    @Test
    public void test2() {
        assertFalse(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }

    @Test
    public void test3() {
        assertTrue(validateStackSequences(new int[0], new int[0]));
    }
}
