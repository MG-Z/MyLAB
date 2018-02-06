package zj.thread;

/**
 * func desc:
 */
public class Context {

    public static void main(String[] args) throws InterruptedException {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                int count = 0;
                while (true) {
                    try {
                        count++;
                        Thread.sleep(1000);
                        System.out.println("......" + count);

//                        if (count == 5) {
//                            throw new RuntimeException("test");
//                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("I'm dead");
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
    }
}
