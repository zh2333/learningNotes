package com.interview.study.juc;

public class ReEnterLockDemo {
    public static Object objectLockA = new Object();

    public  static void m1() {
        synchronized (objectLockA) {
            System.out.println("外层调用");
            synchronized (objectLockA) {
                System.out.println("中层调用");
                synchronized (objectLockA) {
                    System.out.println("内层调用");
                }
            }
        }
    }
    public static void main(String[] args) {
        //m1();
        new ReEnterLockDemo().m2();
    }

    public  synchronized void m2(){
        System.out.println("外层调用");
        m3();
    }

    public  synchronized void m3(){
        System.out.println("中层调用");
        m4();
    }
    public  synchronized void m4(){
        System.out.println("内层调用");
    }
}
