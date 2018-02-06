package com.createmodel.abstractfactory;

/**
 * Created by zoujie on 2018/1/24.
 */
public class FactoryProduct2 extends ABSFactory {
    public <T extends ABSProductA> T createA() {
        return (T) new ConProductA2();
    }

    public <T extends ABSProductB> T createB() {
        return (T) new ConProductB2();
    }
}
