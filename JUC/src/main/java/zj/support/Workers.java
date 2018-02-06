package zj.support;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * func desc:
 */
public class Workers {

    private ExecutorService executorService;

    public Workers(int workerNum, String name) {
        this.executorService = Executors.newFixedThreadPool(workerNum, new CustomerThreadFactory(name));
    }

    public void execute(Runnable work) {
        executorService.execute(work);
    }

    public Future<?> submit(Runnable work) {
        return executorService.submit(work);
    }

    public void stop(boolean await) {
        executorService.shutdown();
    }
}
