package com.LeetCode;

public class NumberCaculateWithoutSign {
    /**
     * 不使用+/-符号计算两个integer的结果
     */
    public int getSum(int a, int b) {

        while(b!=0){
            int c = a&b;
            a=a^b;
            b=c<<1;
        }

        return a;
    }
    public static int sum(int a, int b) {
        while (b != 0) {
            if (b < 0) {
                a--;
                b++;
            } else {
                a++;
                b--;
            }
        }
        return a;
    }

    public static void main(String[] args){
        NumberCaculateWithoutSign numberCaculateWithoutSign = new NumberCaculateWithoutSign();
        System.out.println("sum(5,7) = "+sum(5,7));
        System.out.println("sum(7,7) = "+sum(7,7));
        System.out.println("sum(57,7) = "+sum(57,7));
        System.out.println("getSum(5,7) = "+ numberCaculateWithoutSign.getSum(5,7));
        System.out.println("getSum(7,7) = "+ numberCaculateWithoutSign.getSum(7,7));
        System.out.println("getSum(57,7) = "+ numberCaculateWithoutSign.getSum(57,7));
        System.out.println("getSum(57,-7) = "+ numberCaculateWithoutSign.getSum(57,-7));
    }
}
