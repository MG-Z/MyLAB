package com.createmodel.builder;

/**
 * func desc:
 */
public abstract class ABSBuilder {
    public abstract <T extends ABSBuilder> T config(ConfigContext context);
    public abstract <T extends Product> T buildProduct();
}
