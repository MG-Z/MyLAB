package com.creational.singleton;

/**
 * Created by zoujie on 2018/1/23.
 */
public class StaticInnerClassSingleton {

    /**
     * 能够做到延迟加载,在需要调用地方触发类的初始化,加载对象
     * 通过类加载机制来实现 单例模式:
     * 静态内部类是一个独立的类,  调用静态内部类常量字段,常量在类编译后确定,在类加载期间初始化,
     * 并且此过程有JVM保证线程安全,也做到了延迟加载
     */
    private static class SingtlonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return SingtlonHolder.INSTANCE;
    }


    private StaticInnerClassSingleton() {

    }
}
