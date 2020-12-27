package com.interview.study.thread;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {
    private String lock1;
    private String lock2;

    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有: " + lock1 + ", 尝试获得:" + lock2);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有:" + lock1 + ", 尝试获得: " + lock2);
            }
        }

    }
}
public class DeadLoackDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "AAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "BBB").start();
    }
}
