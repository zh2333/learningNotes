package com.interview.study.jvm;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的最大直接内存: " + sun.misc.VM.maxDirectMemory() / (double)1024 / 1024 + "MB");

        try {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
