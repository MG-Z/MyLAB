package com.juc.countdownlatch;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * func desc:
 */
public class CountDownWork implements Runnable {
    private CountDownLatch cdl;
    private int seq = 0;

    public CountDownWork(CountDownLatch cdl, int seq) {
        this.cdl = cdl;
        this.seq = seq;
    }

    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format(" CountDownWork [ %s ] start working ...  ", seq));
        System.out.println(" Worker Thread [" + Thread.currentThread().getName() + "]");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
//        cdl.countDown();
        System.out.println(String.format(" CountDownWork [ %s ] end work , surplus work  %s", seq, cdl.getCount()));
    }
}
