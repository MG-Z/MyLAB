package com.juc.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * func desc:
 */
public class MyCyclicBarrier {

    private int count = 0;
    private int parties = 0;
    private ReentrantLock lock = new ReentrantLock(false);
    private Runnable cmd = null;

    private Condition condition = lock.newCondition();

//    CountDownLatch

    public MyCyclicBarrier(int parties, Runnable cmd) {
        this.parties = parties;
        this.count = parties;
        this.cmd = cmd;
    }

    public MyCyclicBarrier(int parties) {
        this(parties, null);
    }


    public void await() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            int currentCount = --count;
            if (currentCount == 0) {
                if (cmd != null) {
                    cmd.run();
                }
                condition.signalAll();
                return;
            }
            condition.await();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }
}

