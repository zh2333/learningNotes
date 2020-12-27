package com.interview.study.jvm;

public class HelloGC {
    public static void main(String[] args) {
        System.out.println("helloGC");
        byte[] bytes = new byte[50 * 1024 * 1024];
    }
}
