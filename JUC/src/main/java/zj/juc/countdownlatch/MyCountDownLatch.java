package zj.juc.countdownlatch;


import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * func desc:
 */
public class MyCountDownLatch {


    private Sync sync;


    public MyCountDownLatch(int count) {
        sync = new Sync(count);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public void countDown() {
        sync.releaseShared(1);
    }

    // CountDownLatch 使用的共享锁
    private static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            setState(count);
        }

        int getCount() {
            return getState();
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return getState() == 0 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int c = getState();
                if (c == 0) return false;
                int nextc = c - 1;
                if (compareAndSetState(c, nextc)) return nextc == 0;
            }
        }
    }
}
