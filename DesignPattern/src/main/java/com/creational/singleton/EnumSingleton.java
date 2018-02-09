package com.creational.singleton;

/**
 * Created by zoujie on 2018/1/23.
 */
public class EnumSingleton {

    /***
     *
     * 为什么使用枚举模式:
     * 枚举的的实现方式: 枚举在编译后生成的 一个类,
     * 并且枚举中的每个属性是作为生成后类的一个常量字段,初始化时在类加载过程初始化的,线程安全有JVM保证
     * 这种模式不能做到延迟加载。
     * @return
     */

    public static EnumSingleton getInstance() {
        return Singlton.SINGLTON.getInstance();
    }

    private enum Singlton {
        SINGLTON;
        private EnumSingleton instance;

        Singlton() {
            instance = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return instance;
        }
    }
}
