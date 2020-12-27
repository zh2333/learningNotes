package com.interview.study.jvm;

public class SoftReference {
    public static void main(String[] args) {
        //softref_Memory_Enough();
        softref_Memory_NotEnough();
    }

    public static void softref_Memory_Enough() {
        Object o1 = new Object();
        java.lang.ref.SoftReference<Object> softReference = new java.lang.ref.SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get());
    }
    public static void softref_Memory_NotEnough() {
        Object o1 = new Object();
        java.lang.ref.SoftReference<Object> softReference = new java.lang.ref.SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;


        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        }catch(Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }
}
