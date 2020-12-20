package com.interview.study.thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retCode;
        while(FLAG) {//只要生产线没有停止就一直生产
            data = atomicInteger.incrementAndGet() + "";
            retCode = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retCode) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 生产结束");
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {//生产者停止生产, 消费者退出
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 消费超时, 消费者结束消费");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
    }

    public void stop() throws Exception {//叫停生产者消费者
        this.FLAG = false;
    }
}


public class ProducerConsumer_BlokingQueueDemo {
    public static void main(String[] args) throws Exception {

        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "producer").start();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("生产结束, 生产线停止运行......");
        myResource.stop();
    }
}
