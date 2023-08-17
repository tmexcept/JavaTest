package com;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolExecutor {
    //线程数超过核心线程数，添加到等待队列，等待队列满了则生成非核心线程数
    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> queue =
                new LinkedBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("beforeExecute:" + t.getName()+"  "+r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("afterExecute:  "+r);
            }
        };
        for (int i = 0; i < 15; i++) {
            threadPool.execute(new Thread(new ThreadPoolTest(), "Thread".concat(i + "")));
            System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
            if (queue.size() > 0) {
                System.out.println("----------------队列中阻塞的线程数" + queue.size());
            }
        }
        threadPool.shutdown();
    }

    public static class ThreadPoolTest implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
