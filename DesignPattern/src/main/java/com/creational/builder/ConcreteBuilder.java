package com.creational.builder;

/**
 * func desc:
 */
public class ConcreteBuilder extends ABSBuilder {
    private ConfigContext context;

    public ConcreteBuilder config(ConfigContext context) {
        this.context = context;
        return this;
    }

    public <T extends Product> T buildProduct() {
        return (T) new Product();
    }
}
