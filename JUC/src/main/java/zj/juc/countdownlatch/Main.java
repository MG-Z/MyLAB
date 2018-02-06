package zj.juc.countdownlatch;

import zj.juc.cyclicbarrier.MyCyclicBarrierWork;
import zj.support.Workers;

import java.util.concurrent.CountDownLatch;

/**
 * func desc:
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(1);
        Workers workers = new Workers(10, "countDownLatchWorker");
        int workNum = 10;
        CountDownWork[] works = new CountDownWork[workNum];
        for (int i = 0; i < workNum; i++) {
            works[i] = new CountDownWork(cdl, i + 1);
            workers.execute(works[i]);
            System.out.println(" submit work.[" + (i + 1) + "]");
        }
        Thread.sleep(2000);
//        cdl.countDown();
        System.out.println(" begin working .. ");
//            MyCountDownLatch mcdl = new MyCountDownLatch(10);
//        for (int i = 0; i < workNum; i++) {
//            workers.execute(new MyCountDownWork(mcdl,i+1));
//        }
//
//        try {
//            System.out.println(" Main Thread Start Blocking .");
//            mcdl.await();
//            System.out.println(" Main Thread End Blocking.");
//            workers.stop(true);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
