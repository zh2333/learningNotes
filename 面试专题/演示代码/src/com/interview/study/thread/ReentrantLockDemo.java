package com.interview.study.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone {
    public synchronized static void sendSms() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invokr sendSms");
        sendMail();
    }

    public synchronized static void sendMail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invoke sendMail");
    }
}

class Phone2 implements Runnable {

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoke get");
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoke set");
        } finally {
            lock.unlock();
        }
    }
}
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone2 phone2 = new Phone2();
//        new Thread(()->{
//            try {
//                phone.sendSms();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, "t1").start();
//
//        new Thread(()->{
//            try {
//                phone.sendMail();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, "t2").start();
        Thread t3 = new Thread(phone2, "t3");
        Thread t4 = new Thread(phone2, "t4");
        t3.start();
        t4.start();
    }
}
