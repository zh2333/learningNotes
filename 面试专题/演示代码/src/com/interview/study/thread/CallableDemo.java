package com.interview.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable {
    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        //try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName() + "*************come in callable***************");
        return 1024;
    }
}

/**
 * callable和runnable
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        Thread t1 = new Thread(futureTask, "AAA");
        Thread t2 = new Thread(futureTask, "BBB");
        t1.start();
        t2.start();
        Integer result2 = 1014;
        while (!futureTask.isDone()) {

        }
        Integer result1 = futureTask.get();
        System.out.println("result: " + (result1 + result2));
    }
}
