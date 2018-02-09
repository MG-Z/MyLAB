package com.creational.abstractfactory;

/**
 * Created by zoujie on 2018/1/24.
 */
public class Context {

    public static void main(String[] args) {
        FactoryProduct1 factoryProduct1 = new FactoryProduct1();
        factoryProduct1.createA().whoImI();
        factoryProduct1.createB().whoImI();

        FactoryProduct2 factoryProduct2 = new FactoryProduct2();
        factoryProduct2.createA().whoImI();
        factoryProduct2.createB().whoImI();
    }
}
