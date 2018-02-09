package com.creational.builder;

/**
 * func desc:
 */
public class Director {

    private ABSBuilder builder;


    public ABSBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(ABSBuilder builder) {
        this.builder = builder;
    }
}
