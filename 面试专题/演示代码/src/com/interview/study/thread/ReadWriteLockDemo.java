package com.interview.study.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache1 {//资源类
    private volatile Map<String, Object> map = new HashMap<>();//多线程写必须使用volatile修饰共享变量
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "\t 正在写入" + key);
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }//模拟网络拥堵的情况
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t 写入完成");
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "\t 正在读取" + key);
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }//模拟网络拥堵的情况
        map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读取完成");
    }
}
class MyCache2 {//资源类
    private volatile Map<String, Object> map = new HashMap<>();//多线程写必须使用volatile修饰共享变量
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void put(String key, Object value) {

        readWriteLock.writeLock().lock();//写锁加锁, 再次期间不能读写
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入" + key);
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }//模拟网络拥堵的情况
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } finally {
            readWriteLock.writeLock().unlock();//写锁释放
        }

    }

    public void get(String key) {
        readWriteLock.readLock().lock();//写锁加锁, 再次期间可读不可写
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取" + key);
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }//模拟网络拥堵的情况
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成");
        } finally {
            readWriteLock.readLock().unlock();//写锁释放
        }
    }
}

/***
 * 验证读写锁
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        //notsafe();
        safe();
    }

    public  static void notsafe() {
        MyCache1 myCache = new MyCache1();
        for (int i = 1; i <= 5 ;i++) {
            final int tmpInt = i;
            new Thread(()->{
                myCache.put(tmpInt+"", tmpInt);
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5 ;i++) {
            final int tmpInt = i;
            new Thread(()->{
                myCache.get(tmpInt+"");
            }, String.valueOf(i)).start();
        }
    }

    public  static void safe() {
        MyCache2 myCache = new MyCache2();
        for (int i = 1; i <= 5 ;i++) {
            final int tmpInt = i;
            new Thread(()->{
                myCache.put(tmpInt+"", tmpInt);
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5 ;i++) {
            final int tmpInt = i;
            new Thread(()->{
                myCache.get(tmpInt+"");
            }, String.valueOf(i)).start();
        }
    }


}



