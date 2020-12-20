package com.interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    volatile int  number = 0;

    /**
     * 验证可见性
     */
    public void addT060() {
        this.number = 60;
    }

    public  void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1.验证volatile的可见性
 *  1.1没加volatile修饰number, 主线程永远不会打印, 因为其感知不到number被修改了
 *  1.2使用volati,e修饰number, 主线程会打印, 因为其能感知到number被修改了
 * 2.验证volatile不保证原子性
 *  2.1 原子性是什么意思?
 *    不可分割, 也即某个线程在处理某个业务时, 中间不可以被被分割.要么全部成功, 要么全部失败
 */
public class VolatileDemo {
    public static void main(String[] args) {
        automic();
    }

    /**
     * 验证原子性.多个线程对一个数进行加法操作
     */
    public static void automic() {
        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        /**
         * 等待全部计算线程计算完毕
         */
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t int type, finally number value: " +  myData.number);
        System.out.println(Thread.currentThread().getName() + "\t Integer type, finally number value: " +  myData.atomicInteger);
    }

    public static void seeable() {
        /** 验证可见性 */
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addT060();
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);
        }, "AAA").start();

        //主线程
        while (myData.number == 0) {
            //只要number的值为0, 主线程就一直等待
        }
        System.out.println(Thread.currentThread().getName() + "\t misson over, get value:"+myData.number);//只要打印出这句话, 说明主线程已经感知到了number被修改
    }
}

