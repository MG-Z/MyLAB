package com.structural.decorator;

/**
 * Created by zoujie on 2018/1/24.
 */
public class Context {
    public static void main(String[] args) {
        Component component = new ConcDecorator(new ConcComponent());
        component.name();
        component.work();
    }
}
