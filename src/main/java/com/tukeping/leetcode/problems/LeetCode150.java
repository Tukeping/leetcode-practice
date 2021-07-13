package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=150 lang=java
 *
 * [150] 逆波兰表达式求值
 *
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/description/
 *
 * algorithms
 * Medium (47.97%)
 * Likes:    86
 * Dislikes: 0
 * Total Accepted:    22.2K
 * Total Submissions: 46.2K
 * Testcase Example:  '["2","1","+","3","*"]'
 *
 * 根据逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 *
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 *
 *
 * 示例 2：
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 *
 *
 * 示例 3：
 *
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 * ⁠ ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 */

import com.tukeping.tools.annotation.TimeLimit;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.IntBinaryOperator;

/**
 * 逆波兰表达式 又称后缀表达式
 *
 * @author tukeping
 * @date 2019/12/27
 **/
public class LeetCode150 {

    /*
     * 算法实现：
     *
     * 首先需要分配2个栈，一个作为临时存储运算符的栈S1（含一个结束符号），一个作为输入逆波兰式的栈S2（空栈），S1栈可先放入优先级最低的运算符#，注意，中缀式应以此最低优先级的运算符结束。可指定其他字符，不一定非#不可。从中缀式的左端开始取字符，逐序进行如下步骤：
     * （1）若取出的字符是操作数，则分析出完整的运算数，该操作数直接送入S2栈
     * （2）若取出的字符是运算符，则将该运算符与S1栈栈顶元素比较，如果该运算符优先级(不包括括号运算符)大于S1栈栈顶运算符优先级，则将该运算符进S1栈，否则，将S1栈的栈顶运算符弹出，送入S2栈中，直至S1栈栈顶运算符低于（不包括等于）该运算符优先级，最后将该运算符送入S1栈。
     * （3）若取出的字符是“（”，则直接送入S1栈顶。
     * （4）若取出的字符是“）”，则将距离S1栈栈顶最近的“（”之间的运算符，逐个出栈，依次送入S2栈，此时抛弃“（”。
     * （5）重复上面的1~4步，直至处理完所有的输入字符
     * （6）若取出的字符是“#”，则将S1栈内所有运算符（不包括“#”），逐个出栈，依次送入S2栈。
     * 完成以上步骤，S2栈便为逆波兰式输出结果。不过S2应做一下逆序处理。便可以按照逆波兰式的计算方法计算了！
     */

    /*
     * 测试举例：
     *
     * (a+b)*c的逆波兰式为ab+c*，假设计算机把ab+c*按从左到右的顺序压入栈中，并且按照遇到运算符就把栈顶两个元素出栈，执行运算，得到的结果再入栈的原则来进行处理，那么ab+c*的执行结果如下：
     * 1）a入栈（0位置）
     * 2）b入栈（1位置）
     * 3）遇到运算符“+”，将a和b出栈，执行a+b的操作，得到结果d=a+b，再将d入栈（0位置）
     * 4）c入栈（1位置）
     * 5）遇到运算符“*”，将d和c出栈，执行d*c的操作，得到结果e，再将e入栈（0位置）
     * 经过以上运算，计算机就可以得到(a+b)*c的运算结果e了。
     * 逆波兰式除了可以实现上述类型的运算，它还可以派生出许多新的算法，数据结构，这就需要灵活运用了。逆波兰式只是一种序列体现形式。
     */

    /**
     * 输入: ["2", "1", "+", "3", "*"]
     * 输出: 9
     * 解释: ((2 + 1) * 3) = 9
     */
    @Test
    public void test1() {
        String[] tokens = new String[]{"2", "1", "+", "3", "*"};
        int ret = evalRPN(tokens);
        Assert.assertEquals(9, ret);
    }

    /**
     * 输入: ["4", "13", "5", "/", "+"]
     * 输出: 6
     * 解释: (4 + (13 / 5)) = 6
     */
    @Test
    public void test2() {
        String[] tokens = new String[]{"4", "13", "5", "/", "+"};
        int ret = evalRPN(tokens);
        Assert.assertEquals(6, ret);
    }

    /**
     * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
     * 输出: 22
     * 解释:
     *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     */
    @Test
    public void test3() {
        String[] tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int ret = evalRPN(tokens);
        Assert.assertEquals(22, ret);
    }

    @TimeLimit
    public int evalRPN(String[] tokens) {
        return evalRPN_OOP(tokens);
    }

    private int evalRPN_Violence(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int a;

        for (String token : tokens) {
            if ("+".equals(token)) {
                stack.push(stack.pop() + stack.pop());
            } else if ("-".equals(token)) {
                a = stack.pop();
                stack.push(stack.pop() - a);
            } else if ("*".equals(token)) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(token)) {
                a = stack.pop();
                stack.push(stack.pop() / a);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private final static Map<String, IntBinaryOperator> OPERATORS = new HashMap<>(4);

    static {
        OPERATORS.put("+", (a, b) -> a + b);
        OPERATORS.put("-", (a, b) -> a - b);
        OPERATORS.put("*", (a, b) -> a * b);
        OPERATORS.put("/", (a, b) -> a / b);
    }

    private static boolean isOp(String s) {
        return OPERATORS.containsKey(s);
    }

    private int evalRPN_OOP(String[] tokens) {
        // 栈内只保存操作数, 不保存任何操作符或者类似括号之类的
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (!isOp(token)) {
                // leetcode 题目里 只会是 正整数
                stack.push(Integer.parseInt(token));
            } else { // is operation symbol
                if (stack.size() >= 2) {
                    // 第一个被栈弹出来的是 被除数/被减数 要放在b里
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    IntBinaryOperator operatorFn = OPERATORS.get(token);
                    if (null != operatorFn) {
                        Integer c = operatorFn.applyAsInt(a, b);
                        // 计算完成后 把结果 继续放入栈中
                        stack.push(c);
                    } else {
                        // 不能存在这种testcase
                        throw new IllegalArgumentException("not found operate symbol");
                    }
                } else {
                    // 不能存在这种testcase
                    throw new IllegalArgumentException("test case illegal");
                }
            }
        }

        if (stack.size() != 1) {
            throw new RuntimeException("my bad, wrong answer");
        }

        return stack.pop();
    }
}
