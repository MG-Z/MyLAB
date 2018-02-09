package com.structural.proxy;

/**
 * func desc:
 */
public  class ConcreteOperator implements ITarget {

    public void doSomeThingOne() {
        System.out.println("I'm  Operator. do one");
    }

    public void doSomeThingTwo() {
        System.out.println("I'm Operator . do two");
    }
}