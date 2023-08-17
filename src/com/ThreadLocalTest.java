package com;

public class ThreadLocalTest {

    // 测试代码
    public static void main(String[] args){
        // 新开2个线程用于设置 & 获取 ThreadLoacl的值
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable, "线程1").start();
        Thread thread2 = new Thread(runnable, "线程2"){
            @Override
            public void run() {
                super.run();
                ThreadLocal threadLocal = new ThreadLocal();
                threadLocal.set("haha");
                System.out.println( "：" + threadLocal.get());
            }
        };
        thread2.start();
    }

    // 线程类
    public static class MyRunnable implements Runnable {

        // 创建ThreadLocal & 初始化
        private ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return "初始化值";
            }
        };

        @Override
        public void run() {

            // 运行线程时，分别设置 & 获取 ThreadLoacl的值
            String name = Thread.currentThread().getName();
            threadLocal.set(name + "的threadLocal"); // 设置值 = 线程名
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "：" + threadLocal.get());
        }
    }
}
