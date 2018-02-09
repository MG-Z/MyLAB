package com.creational.factorymethod;

/**
 * Created by zoujie on 2018/1/23.
 */
public class ProductTwo extends AbsProduct {
    @Override
    protected void whoIamI() {
        System.out.println("I'm  Product Two.");
    }
}
