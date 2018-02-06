package zj.support;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * func desc:
 */
public class CustomerThreadFactory implements ThreadFactory {
    private String threadName;
    private ThreadGroup threadGroup;
    private AtomicInteger counter;

    public CustomerThreadFactory(String threadName) {
        this.threadName = threadName;
        SecurityManager s = System.getSecurityManager();
        threadGroup = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        counter = new AtomicInteger(0);
    }

    private int getIndex() {
        return counter.incrementAndGet();
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r, threadName +"-"+ getIndex(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
