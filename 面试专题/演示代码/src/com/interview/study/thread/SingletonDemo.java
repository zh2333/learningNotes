package com.interview.study.thread;

public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private  SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 实例化");
    }

//    private static SingletonDemo getInstance() {
//        if (instance == null) {
//            instance = new SingletonDemo();
//        }
//        return instance;
//    }

    /**
     * 锁太重了
     */
//    private static synchronized SingletonDemo getInstance() {
//        if (instance == null) {
//            instance = new SingletonDemo();
//        }
//        return instance;
//    }

    private static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        for (int i = 1; i <= 10; i++) {
            new Thread(()-> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }

    }
}
