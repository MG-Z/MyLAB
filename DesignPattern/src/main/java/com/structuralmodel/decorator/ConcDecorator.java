package com.structuralmodel.decorator;

/**
 * Created by zoujie on 2018/1/24.
 */
public class ConcDecorator extends ABSDecorator {

    public ConcDecorator(Component component) {
        super(component);
    }

    public void name() {
        System.out.print("my name is :");
        component.name();

    }

    public void work() {
        System.out.print("my work is:");
        component.work();
    }
}
