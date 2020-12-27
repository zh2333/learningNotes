package com.interview.study.jvm;

import java.util.ArrayList;
import java.util.List;

public class GCOverHeadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true) {
                list.add(String.valueOf(++i));
            }
        } catch (Throwable e) {
            System.out.println("*******************i: " + i);
            e.printStackTrace();
            throw e;
        }
    }
}
