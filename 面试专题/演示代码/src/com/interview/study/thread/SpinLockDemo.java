package com.interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现一个自旋锁
 * 自旋锁的好处: 循环比较知道成功为止, 没有类似wait的阻塞
 * 实现:
 * 通过CAS操作完成自旋锁, A线程先进来调用MyLock方法自己持有锁5秒中, B随后进来后发现当前有线程持有锁, 不是null, 所以只能通过自旋等待, 知道A释放
 * 锁后B随后抢到
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t invoke lock");
        while(!atomicReference.compareAndSet(null, thread)) {
            //System.out.println(Thread.currentThread().getName() + "\t 获取失败, 自旋");
        }
        System.out.println(Thread.currentThread().getName() + "\t 获取成功");
    }

    public void muUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoke unlock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.muUnlock();
        }, "AA").start();


        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.muUnlock();
        }, "BB").start();
    }
}
