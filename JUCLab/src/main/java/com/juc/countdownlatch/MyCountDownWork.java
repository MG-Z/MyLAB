package com.juc.countdownlatch;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * func desc:
 */
public class MyCountDownWork implements Runnable {
    private MyCountDownLatch cdl;
    private int seq = 0;

    public MyCountDownWork(MyCountDownLatch cdl, int seq) {
        this.cdl = cdl;
        this.seq = seq;
    }

    public void run() {
        System.out.println(String.format(" CountDownWork [ %s ] start working ...  ", seq));
        System.out.println(" Worker Thread [" + Thread.currentThread().getName() + "]");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        cdl.countDown();
        System.out.println(String.format(" CountDownWork [ %s ] end work , surplus work", seq));
    }
}
