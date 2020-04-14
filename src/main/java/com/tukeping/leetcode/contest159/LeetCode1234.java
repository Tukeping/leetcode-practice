package com.tukeping.leetcode.contest159;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/14
 **/
public class LeetCode1234 {

    public int balancedString(String s) {
        int len = s.length();
        int balance = len / 4;
        int[] count = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'A']++;
        }
        int left = 0, right = 0;
        int ans = n;
        while (right < n) {
            count[s.charAt(right) - 'A']--;
            while (left < n &&
                    count['Q' - 'A'] <= balance &&
                    count['W' - 'A'] <= balance &&
                    count['E' - 'A'] <= balance &&
                    count['R' - 'A'] <= balance) {
                ans = Math.min(ans, right - left + 1);
                count[s.charAt(left) - 'A']++;
                left++;
            }
            right++;
        }
        return ans;
    }

    public int balancedString2(String s) {
        int len = s.length();
        int balance = len / 4; // len是4的倍数

        int[] count = new int[4]; // Q, W, E, R
        for (int i = 0; i < len; i++) {
            count[idx(s, i)]++;
        }

        boolean isBalance = true;
        for (int i = 0; i < 4; i++) {
            if (count[i] > balance) {
                count[i] -= balance;
                isBalance = false;
            } else {
                count[i] = 0;
            }
        }
        if (isBalance) return 0;

        int minLen = Integer.MAX_VALUE;
        boolean forward = true;
        int[] tmpCount = new int[4];
        for (int lo = 0, hi = 0; lo <= hi && hi < len; ) {
            if (forward) tmpCount[idx(s, hi)]++;
            else tmpCount[idx(s, lo - 1)]--;

            int x = go(count, tmpCount);
            if (x == 0) {
                minLen = Math.min(minLen, hi - lo + 1);
                forward = false;
            } else {
                forward = x > 0;
            }

            if (forward) hi++;
            else lo++;
        }
        return minLen;
    }

    private int go(int[] c1, int[] c2) {
        for (int i = 0; i < 4; i++) {
            if (c1[i] > 0 && c1[i] != c2[i]) {
                return c1[i] - c2[i];
            }
        }
        return 0;
    }

    private int idx(String s, int i) {
        if (s.charAt(i) == 'Q') {
            return 0;
        } else if (s.charAt(i) == 'W') {
            return 1;
        } else if (s.charAt(i) == 'E') {
            return 2;
        } else if (s.charAt(i) == 'R') {
            return 3;
        }
        return 0;
    }

    @Test
    public void test1() {
        int ans = balancedString("WWEQERQWQWWRWWERQWEQ");
        assertThat(ans, is(4));
    }

    @Test
    public void test2() {
        int ans = balancedString("WQWRQQQW");
        assertThat(ans, is(3));
    }

    /**
     * 输入：s = "QWER"
     * 输出：0
     * 解释：s 已经是平衡的了。
     */
    @Test
    public void test3() {
        int ans = balancedString("QWER");
        assertThat(ans, is(0));
    }

    /**
     * 输入：s = "QQWE"
     * 输出：1
     * 解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
     */
    @Test
    public void test4() {
        int ans = balancedString("QQWE");
        assertThat(ans, is(1));
    }

    /**
     * 输入：s = "QQQW"
     * 输出：2
     * 解释：我们可以把前面的 "QQ" 替换成 "ER"。
     */
    @Test
    public void test5() {
        int ans = balancedString("QQQW");
        assertThat(ans, is(2));
    }

    /**
     * 输入：s = "QQQQ"
     * 输出：3
     * 解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
     */
    @Test
    public void test6() {
        int ans = balancedString("QQQQ");
        assertThat(ans, is(3));
    }

    @Test
    public void test7() {
        int ans = balancedString("WWWEQRQEWWQQQWQQQWEWEEWRRRRRWWQE");
        assertThat(ans, is(5));
    }

    @Test
    public void test8() {
        int ans = balancedString("WWQQRRRRQRQQ");
        assertThat(ans, is(4));
    }

    @Test
    public void test9() {
        int ans = balancedString("QEQRWRRWWWRQQQWQQEQEQREWRQEQRQQRRQEW");
        assertThat(ans, is(6));
    }

    @Test
    public void test10() {
        int ans = balancedString("EQWEEEWERRWERQQQWWQEQWEEWQRWQWWWQWRWEWERWQEWWQWWQRRQWQERWWQRERRRRRWQEQRERRWRREEEERRWERQRQEWREQREWWEWRRRERWRRWEQWQQRRWQEREEEERWQWEWQEWRWREWQEREQWQEQWRQQQWRWWRWERWQWWQQREWREEWRWWQRWQRWWQWWREWWWEWQRWRRWQEWRRRWWQRRQREQRWRRQWREQWEQRWQRWQRWERRREEREEQQEREREWQQWRWEWEQQRWEWEQWEEQEEERWWWEQRRRWRQWWQQEQRWRWRWEQRRQRQRQWWWEQWERWEQRWQRERWQQQWWWQWRWEREWRQWQWERRQQWRQWRWQQQEEQREQWRWWEQRWWWEEERQWQWEWRQQRWQWEQQEWEWRQWWERERQREWWEEREQRRWQRRRWQEEWEWQEEEQRQQEWREQWQWRRWREQQQEEEWWRQEEEWQQEQEQWWREEWRQQQRQQEERQQQEEQWERRQEEQQRQQQWWEQEEQWRQWEQWEEQQEEQQEERQEEWQEEEWQRERRERQRQQQQWRQRRQQWQEEWRRQEWQWREWERWQRQQWEEERREQEEQWQQRWERWRWEEQWEREREWRWREEWRRRQEQQERRRERQRQWRWWREQQWWEQQWQWRRWEQRWEQQEQWWWQWWEWWEEEWEWRQWQWWQQQQEWRQEQWWRWWEQRRRWREREWRQQERQRQEQWWQQWRQRRQRWRQEEEWRRQREWRRERRQEEREERWEQQQWEWWEERWQWQQWWEQWWQWERRRWQWEEEEQEEWQRRWRWEEEWEWRREWWEQQERRQWQEWQQQRWEWRQEQWREWEWEEWWWWWWWWEQRWRRQQEEEERQEWQQEQQEEEWWRWRQQQQWRREEWRWWWQRWRWRWQWWEREQQEEEWREERQEWEERQWQREEQRQWEQEREQWWEWEEWREREWRQQWREEEEQRRWRWRQRRRWRQQQRRQQRQEWEEQQEREWQEEWEWWEQRRQWRQRQQREREQRWWQEEERREWRREWRRRQQQRQEQEWQWEEQQERQRRRWRRWWEEQRWQQQQQQWREWEWWRQREWREQRRQEQEERRQERWEEQRWWQRWQRWWERQEWEQERWRWQRQEEQEQEWWQWWRREEWEWWRRQEQQWEERWEWWWQEWWRRWRWQEERQRQWEQQEWWRRRRWQEWQQQQWWEEE");
        assertThat(ans, is(16));
    }
}
