package com.tukeping.leetcode.problems;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/29
 **/
public class LeetCode690 {

    int n, sum = 0;
    List<Employee> employees;
    Map<Integer, Integer> imp;
    Map<Integer, List<Integer>> graph;

    public int getImportance(List<Employee> employees, int id) {
        this.employees = employees;
        this.n = employees.size();

        initial();
        dfs(id);
        return sum;
    }

    private void initial() {
        this.imp = new HashMap<>();
        this.graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Employee emp = employees.get(i);
            imp.put(emp.id, emp.importance);

            if (!graph.containsKey(emp.id)) {
                graph.put(emp.id, new ArrayList<>());
            }
            graph.get(emp.id).addAll(emp.subordinates);
        }
    }

    private void dfs(int target) {
        sum += imp.get(target);
        List<Integer> subs = graph.get(target);
        if (subs == null || subs.isEmpty()) return;
        for (int sub : subs) {
            dfs(sub);
        }
    }

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int imp, List<Integer> subs) {
            this.id = id;
            this.importance = imp;
            this.subordinates = subs;
        }
    }

    /**
     * 输入: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
     * 输出: 11
     * 解释:
     * 员工1自身的重要度是5，他有两个直系下属2和3，而且2和3的重要度均为3。因此员工1的总重要度是 5 + 3 + 3 = 11。
     */
    @Test
    public void test1() {
        Employee e1 = new Employee(1, 5, Arrays.asList(2, 3));
        Employee e2 = new Employee(2, 3, Arrays.asList());
        Employee e3 = new Employee(3, 3, Arrays.asList());

        int n = getImportance(Arrays.asList(e1, e2, e3), 1);
        assertThat(n, is(11));
    }
}
