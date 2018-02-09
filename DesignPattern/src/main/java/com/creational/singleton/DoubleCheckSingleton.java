package com.creational.singleton;

/**
 * Created by zoujie on 2018/1/23.
 */
public class DoubleCheckSingleton {

    /**
     * 使用双重检查 double-check 机制实现 注意使用 Volatile 修饰对象 防止指令重排序 发布未初始化对象
     * (  synchronized 能保证可见性,但是不能保证重排序 )
     */
    private static volatile DoubleCheckSingleton INSTANCE;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckSingleton();
                }
                return INSTANCE;
            }
        }
        return INSTANCE;
    }
}
