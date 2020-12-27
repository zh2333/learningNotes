package com.interview.study.jvm;


/**
 * -XX:MetaspaceSize=8m  -XX:MaxMetaspaceSize=8m
 */
public class MetaspaceOOMDemo {
    static  class OOMTest {

    }

    public static void main(String[] args) {
        try {

        } catch (Throwable e) {
            System.out.println("**********************多少次后发生异常:");
        }
    }
}
