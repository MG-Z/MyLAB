package com.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * func desc:
 */
public class Context {
    private static List<Integer> arrays = new ArrayList<Integer>();
    public static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());
        producer.start();
        consumer.start();
    }


    public static class Producer implements Runnable {

        public void run() {
            while (true) {
                synchronized (arrays) {
                    while (arrays.isEmpty()) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        arrays.add(counter.incrementAndGet());
                        arrays.notify();
                    }
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        public void run() {
            while (true) {
                synchronized (arrays) {
                    while (arrays.isEmpty()) {
                        try {
                            arrays.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(arrays.remove(0));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
