package com.interview.study.thread;

import com.interview.enums.CountyEnum;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5 ;i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 被灭");
                countDownLatch.countDown();
            }, CountyEnum.forEach_CountryEnum(i).getRetMsg()).start();
        }
        countDownLatch.await();//在这个位置等待所有的线程执行完成, 主线程才继续向下走
        System.out.println(Thread.currentThread().getName() + "\t 秦国一统天下");
    }
}
