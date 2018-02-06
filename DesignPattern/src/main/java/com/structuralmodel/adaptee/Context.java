package com.structuralmodel.adaptee;

/**
 * Created by zoujie on 2018/1/24.
 */
public class Context {
    public static void main(String[] args) {
        Target  target = new  Adapter(new Adaptee());
        target.name();
        target.work();
    }
}
