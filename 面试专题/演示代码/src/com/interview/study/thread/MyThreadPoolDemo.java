package com.interview.study.thread;

import java.util.concurrent.*;

/**
 * 手写线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2, 5, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    public static void threadPoolInit() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
