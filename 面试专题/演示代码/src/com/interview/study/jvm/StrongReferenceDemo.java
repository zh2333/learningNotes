package com.interview.study.jvm;

public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;
        System.gc();
        System.out.println(o2);
    }
}
