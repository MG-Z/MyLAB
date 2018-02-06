package com.structuralmodel.adaptee;

/**
 * Created by zoujie on 2018/1/24.
 */
public class Adapter implements Target {

    Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void name() {
        System.out.println(adaptee.myName());
    }

    public void work() {
        System.out.println(adaptee.myWork());
    }
}
