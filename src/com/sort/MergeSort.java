package com.sort;

import java.util.Arrays;

public class MergeSort {

    //将r[i…m]和r[m +1 …n]归并到辅助数组rf[i…n]
    static void Merge(int[] r, int[] rf, int i, int m, int n) {
        int j, k;
        for (j = m + 1, k = i; i <= m && j <= n; ++k) {
            if (r[j] < r[i]) rf[k] = r[j++];
            else rf[k] = r[i++];
        }
        while (i <= m) rf[k++] = r[i++];
        while (j <= n) rf[k++] = r[j++];
        System.out.println(Arrays.toString(r));
    }

    static void MergeSort(int[] r, int[] rf, int lenght) {
        int len = 1;
        int[] tmp;
        while (len < lenght) {
            int s = len;
            len = 2 * s;
            int i = 0;
            while (i + len < lenght) {
                Merge(r, rf, i, i + s - 1, i + len - 1); //对等长的两个子表合并
                i = i + len;
            }
            if (i + s < lenght) {
                Merge(r, rf, i, i + s - 1, lenght - 1); //对不等长的两个子表合并
            }
            tmp = r;
            r = rf;
            rf = tmp; //交换q,rf，以保证下一趟归并时，仍从q 归并到rf
        }
    }


    public static void main(String[] args) {
        int a[] ={ 3, 1, 5, 7, 2, 4, 9, 6, 10, 8  } ;
        int b[] = new int[10];
        MergeSort(a, b, a.length);
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(a));
    }
}
