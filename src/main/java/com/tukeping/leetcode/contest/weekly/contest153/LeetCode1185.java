package com.tukeping.leetcode.contest.weekly.contest153;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tukeping
 * @date 2020/4/10
 **/
public class LeetCode1185 {

    /**
     * （1）闰年规则：四年一闰，百年不闰，四百年再闰，闰年是366天，平年是365天；
     * （2）闰月规则：闰年时，多的一天都加到二月中，平年二月28天，闰年二月29天；
     */
    public String dayOfTheWeek(int day, int month, int year) {
        int y = year % 100;
        int c = year / 100;
        int m = month;
        int d = day;
        if (m == 1 || m == 2) {
            if (y == 0) {
                c--;
                y = 99;
            } else {
                y--;
            }
            m += 12;
        }
        int w = y + (y / 4) + (c / 4) - 2 * c + (26 * (m + 1) / 10) + d - 1;
        while (w < 0) {
            w += 7;
        }
        w = w % 7;
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Sunday");
        map.put(1, "Monday");
        map.put(2, "Tuesday");
        map.put(3, "Wednesday");
        map.put(4, "Thursday");
        map.put(5, "Friday");
        map.put(6, "Saturday");
        return map.get(w);
    }
}
