package com.LeetCode;

import java.util.Arrays;

public class TooArrayMid {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] result = new int[len1 + len2];
        int i=0,j=0;
        while(i < len1 || j < len2){
            if(j==len2){
                result[i + j] = nums1[i];
                i++;
            } else if(i == len1){
                result[i + j] = nums2[j];
                j++;
            } else if(nums1[i] > nums2[j]){
                result[i + j] = nums2[j];
                j++;
            } else {
                result[i + j] = nums1[i];
                i++;
            }
        }
        System.out.println(Arrays.toString(result));
        int tmp= (len1+ len2)%2;
        int mid = (len1+ len2)/2;
        if(tmp == 1){
            return result[mid];
        } else {
            return (result[mid-1] + result[mid])/2.0;
        }

    }
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int mid = (len1+ len2)/2 + 1;
        int[] result = new int[mid];
        int i=0,j=0;
        while(i < mid || j < mid){
            if(i+j == mid){
                break;
            }
            if(j==len2){
                result[i + j] = nums1[i];
                i++;
            } else if(i == len1){
                result[i + j] = nums2[j];
                j++;
            } else if(nums1[i] > nums2[j]){
                result[i + j] = nums2[j];
                j++;
            } else {
                result[i + j] = nums1[i];
                i++;
            }
        }
        System.out.println(Arrays.toString(result));
        int tmp= (len1+ len2)%2;
        if(tmp == 1){
            return result[mid-1];
        } else {
            return (result[mid-2] + result[mid-1])/2.0;
        }
    }
    public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int mid = (len1+ len2)/2 + 1;
        int tmp= (len1+ len2)%2;
        int i=0,j=0;

        int result = 0, left=0, right=0;
        boolean flag = false;
        while (i < mid || j < mid) {
            if (i + j == mid) {
                break;
            }
            flag = !flag;
            if (j == len2) {
                result = nums1[i];
                i++;
            } else if (i == len1) {
                result = nums2[j];
                j++;
            } else if (nums1[i] > nums2[j]) {
                result = nums2[j];
                j++;
            } else {
                result = nums1[i];
                i++;
            }
            if(flag){
                left = result;
            } else {
                right = result;
            }
        }
        System.out.println(tmp+"  "+flag+"  "+left+"   "+right);

        if(tmp == 1) {
            return flag ? left : right;
        } else {
            return (left+right)/2.0;
        }
    }

    public static void main(String[] args){
        System.out.println("result = "+findMedianSortedArrays3(new int[]{2,3},new int[]{1,4}));
    }
}
