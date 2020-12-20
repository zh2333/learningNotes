package com.interview.study.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//三个车位

        for (int i = 1; i <= 6;i++) {//6个车抢3个车位
            new Thread(()->{
                try {
                    semaphore.acquire();//尝试占用车位
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName() + "\t 停车3秒后离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放被占用的资源
                }
            }, String.valueOf(i)).start();
        }
    }
}
