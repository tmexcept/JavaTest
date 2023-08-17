package com.test;

public class finallyTest {

    public static void main(String[] args) {
        System.out.println(returnTest());
    }

    public static int returnTest(){
        int a = 10;
        try {

            return a + 10;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a = 20;
//            return a + 30;
        }
        return 2;
    }
}
