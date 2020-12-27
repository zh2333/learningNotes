package com.interview.study.jvm;

import java.util.Random;

public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String str = "heapSpace";

        while (true) {
            str += str + new Random().nextInt(111111111) + new Random().nextInt(222222);
        }
    }
}
