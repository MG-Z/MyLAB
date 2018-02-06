package zj.juc.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * func desc:
 */
public class MyCyclicBarrierWork implements Runnable {

    private MyCyclicBarrier cb;

    public MyCyclicBarrierWork(MyCyclicBarrier cb) {
        this.cb = cb;
    }

    public void run() {
        System.out.println("cyclic barrier await  thread :" + Thread.currentThread().getName());
        try {
            cb.await();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("cyclic barrier restarting   thread :" + Thread.currentThread().getName());
    }
}
