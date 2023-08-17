package com;

public class HashMapTableSize {

    public static void main(String[] args) {
        int n = 35;
        System.out.println(n);
        System.out.println(Integer.toBinaryString(n));
        System.out.println((n |= n >>> 1));
        System.out.println(Integer.toBinaryString(n));
        System.out.println((n |= n >>> 2));
        System.out.println(Integer.toBinaryString(n));
        System.out.println((n |= n >>> 4));
        System.out.println(Integer.toBinaryString(n));
        System.out.println((n |= n >>> 8));
        System.out.println(Integer.toBinaryString(n));
        System.out.println((n |= n >>> 16));
        System.out.println(Integer.toBinaryString(n));
    }
}
