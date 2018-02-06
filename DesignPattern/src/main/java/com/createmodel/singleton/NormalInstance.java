package com.createmodel.singleton;

/**
 * Created by zoujie on 2018/1/23.
 */
public class NormalInstance {
    private final static NormalInstance INSTANCE = new NormalInstance();

    private NormalInstance() {
    }

    public static NormalInstance getInstance() {
        return INSTANCE;
    }
}
