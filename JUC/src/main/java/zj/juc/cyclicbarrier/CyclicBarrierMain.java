package zj.juc.cyclicbarrier;

import zj.support.Workers;

import java.util.concurrent.CyclicBarrier;

/**
 * func desc:
 */
public class CyclicBarrierMain {

    public static void main(String[] args) {
        Workers workers = new Workers(4, "cyclic barrier");
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
        MyCyclicBarrier cyclicBarrier = new MyCyclicBarrier(4, new Runnable() {
            public void run() {
                System.out.println(" begin .... Thread:" + Thread.currentThread().getName());
            }
        });
        for (int i = 0; i < 4; i++) {
            workers.execute(new MyCyclicBarrierWork(cyclicBarrier));
        }

//        workers.stop(true);
    }
}
