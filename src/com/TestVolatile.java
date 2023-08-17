package com;

import java.util.concurrent.atomic.AtomicInteger;

public class TestVolatile {
    public AtomicInteger num3;
    public volatile int num2;
    public static volatile int num;

    public static void runThread2(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 100000; i++) {
                    num += 1;
                }
                System.out.println("runThread2:  "+num);
            }
        }.start();
    }

    public static void runThread1(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 100000; i++) {
                    num += 1;
                }
                System.out.println("runThread1:  "+num);
            }
        }.start();
    }

    public static void main(String[] args) {
//        TestVolatile testVolatile = new TestVolatile();
//        testVolatile.num3 = new AtomicInteger();
        new Thread(){
            @Override
            public void run() {
                super.run();
//                for (int i = 0; i < 100000; i++) {
//                    testVolatile.num2 = testVolatile.num2 + 1;
//                    testVolatile.num3.addAndGet(1);
//                }
//                System.out.println("runThread1:  num2="+testVolatile.num2 + "  num3="+testVolatile.num3.get());
                for (int i = 0; i < 5; i++) {
                    System.out.println("runThread1:  getInstance()="+getInstance());
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
//                for (int i = 0; i < 100000; i++) {
//                    testVolatile.num2 = testVolatile.num2 + 1;
//                    testVolatile.num3.getAndAdd(1);
//                }
//                System.out.println("runThread2:  num2="+testVolatile.num2 + "  num3="+testVolatile.num3.get());
                for (int i = 0; i < 5; i++) {
                    System.out.println("runThread1:  getInstance()="+getInstance());
                }
            }
        }.start();
    }

    private static TestVolatile singleTon;

    private TestVolatile() {}

    public static TestVolatile getInstance() {

        if (singleTon == null) {
//            synchronized (TestVolatile.class) {
//                if (singleTon == null) {
                    //非原子操作
                    singleTon = new TestVolatile();
//                }
//            }
        }
        return singleTon;
    }
}
