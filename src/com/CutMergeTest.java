package com;

import java.util.LinkedList;
import java.util.List;

/**
 * ⼒扣第 241 题「为运算表达式设计优先级」
 * 分治算法，
 *
 * 是给你输⼊⼀个算式，你可以给它随意加括号，请你穷举出所有可能的加括号⽅式，并计算出对
 * 应的结果。
 *
 * 1 + 2 * 3 - 4 * 5
 */
public class CutMergeTest {

    public static void main(String[] args) {
        String str = "2*3-4*5";

        List<Integer> list = diffWaysToCompute(str);
        for (Integer integer : list) {
            System.out.print(integer +"  ");
        }
        System.out.println();
        list = diffWaysToCompute3(str);//有问题，会少一部分结果
        for (Integer integer : list) {
            System.out.print(integer +"  ");
        }
    }

    static List<Integer> diffWaysToCompute3(String input) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // 扫描算式 input 中的运算符
            if (c == '-' || c == '*' || c == '+') {
                /****** 分 ******/
                // 以运算符为中⼼，分割成两个字符串，分别递归计算
                int left = diffWaysToCompute2(input.substring(0, i));
                int right = diffWaysToCompute2(input.substring(i + 1));
                /****** 治 ******/
                // 通过⼦问题的结果，合成原问题的结果
                if (c == '+')
                    res.add (left + right);
                else if (c == '-')
                    res.add (left - right);
                else if (c == '*')
                    res.add (left * right);
            }
        }
        // base case
        // 如果 res 为空，说明算式是⼀个数字，没有运算符
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }

    static int diffWaysToCompute2(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // 扫描算式 input 中的运算符
            if (c == '-' || c == '*' || c == '+') {
                /****** 分 ******/
                // 以运算符为中⼼，分割成两个字符串，分别递归计算
                int left = diffWaysToCompute2(input.substring(0, i));
                int right = diffWaysToCompute2(input.substring(i + 1));
                /****** 治 ******/
                // 通过⼦问题的结果，合成原问题的结果
                if (c == '+')
                    return (left + right);
                else if (c == '-')
                    return(left - right);
                else if (c == '*')
                    return(left * right);
            }
        }
        // base case
        // 如果 res 为空，说明算式是⼀个数字，没有运算符
        return (Integer.parseInt(input));
    }

    static List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // 扫描算式 input 中的运算符
            if (c == '-' || c == '*' || c == '+') {
                /****** 分 ******/
                // 以运算符为中⼼，分割成两个字符串，分别递归计算
                List<Integer>
                        left = diffWaysToCompute(input.substring(0, i));
                List<Integer>
                        right = diffWaysToCompute(input.substring(i + 1));
                /****** 治 ******/
                // 通过⼦问题的结果，合成原问题的结果
                for (int a : left)
                    for (int b : right)
                        if (c == '+')
                            res.add(a + b);
                        else if (c == '-')
                            res.add(a - b);
                        else if (c == '*')
                            res.add(a * b);
            }
        }
        // base case
        // 如果 res 为空，说明算式是⼀个数字，没有运算符
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }
}
