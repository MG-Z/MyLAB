package com.creational.abstractfactory;

/**
 * Created by zoujie on 2018/1/24.
 */
public abstract class ABSFactory {
    public abstract <T extends ABSProductA> T createA();
    public abstract <T extends ABSProductB> T createB();
}
