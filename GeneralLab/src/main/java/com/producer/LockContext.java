package com.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * func desc:
 */
public class LockContext {
    private static List<Integer> arrays = new ArrayList<Integer>();
    private static ReentrantLock LOCK = new ReentrantLock(false);
    private static Condition notFull = LOCK.newCondition();
    private static Condition notEmpt = LOCK.newCondition();
    private static int MAX_SIZE = 10;

    public static void main(String[] args) {
//        Producer producer = new Producer();
        Thread pro1 = new Thread(new Producer(),"pro-1");
        Thread pro2 = new Thread(new Producer(),"pro-2");
        Thread pro3 = new Thread(new Producer(),"pro-3");
        Thread consumer = new Thread(new Consumer(),"consumer");
        pro1.start();
        pro2.start();
        pro3.start();
        consumer.start();
    }


    public static class Producer implements Runnable {
        public void run() {
            while (true) {
                final ReentrantLock lock = LOCK;
                try {
                    lock.lock();
                    if (arrays.size() >= MAX_SIZE) {
                        System.out.println("bao cang  ....");
                        notFull.await();
                    }
                    if (arrays.size() < MAX_SIZE) {
                        int num = new Random().nextInt();
                        Thread.sleep(2000);
                        arrays.add(num);
                        System.out.println("producer ==>" + Thread.currentThread().getName() + "  Product :" + num);
                        notEmpt.signalAll();
                    }
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        public void run() {
            while (true) {
                final ReentrantLock lock = LOCK;
                try {
                    lock.lock();

                    if (arrays.size() == 0) {
                        notEmpt.await();
                    }

                    System.out.println("consumer  ==>" + Thread.currentThread().getName() + " Product: " + arrays.remove(0));
                    Thread.sleep(1000);
                    notFull.signalAll();

                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
