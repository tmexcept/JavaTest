package com.sort;

import java.util.Arrays;

public class ShellSort {
    /**
     * 直接插入排序的一般形式
     *
     * @param dk 缩小增量，如果是直接插入排序，dk=1
     */

    void ShellInsertSort(int a[], int n, int dk) {
        for (int i = dk; i < n; ++i) {
            if (a[i] < a[i - dk]) {          //若第i个元素大于i-1元素，直接插入。小于的话，移动有序表后插入
                int j = i - dk;
                int x = a[i];           //复制为哨兵，即存储待排序元素
                a[i] = a[i - dk];         //首先后移一个元素
                j -= dk;
                while (j > -1 && x < a[j]) {     //查找在有序表的插入位置
                    a[j + dk] = a[j];
                    j -= dk;             //元素后移
                }
                a[j + dk] = x;            //插入到正确位置
            }
            System.out.println(Arrays.toString(a));
        }
    }

    void ShellInsertSort2(int a[], int n, int dk) {
        for (int i = 0; i < dk; ++i) {
            for (int j = i + dk; j < n; j += dk) {
                if (a[j - dk] > a[j]) {
                    int x = a[j];
                    int m = j;
                    a[j] = a[j - dk];
                    m -= dk;
                    while (m > -1 && x < a[m]) {
                        a[m + dk] = a[j];
                        m -= dk;
                    }
                    a[m + dk] = x;            //插入到正确位置
                }
            }
            System.out.println(Arrays.toString(a));
        }
    }

    /**
     * 先按增量d（n/2,n为要排序数的个数进行希尔排序
     */
    void shellSort(int a[], int n) {

        int dk = n / 2;
        while (dk >= 1) {
            System.out.println("dk = " + dk);
            ShellInsertSort(a, n, dk);
            dk = dk / 2;
        }
        dk = n / 2;
        while (dk >= 1) {
            System.out.println("dk = " + dk);
            ShellInsertSort2(a, n, dk);
            dk = dk / 2;
        }
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 55, 4, 3};
        ShellSort shellSort = new ShellSort();
        //ShellInsertSort(a,8,1); //直接插入排序
        shellSort.shellSort(a, a.length);           //希尔插入排序
        System.out.println(Arrays.toString(a));
    }
}
