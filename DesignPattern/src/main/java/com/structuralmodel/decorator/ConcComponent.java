package com.structuralmodel.decorator;

/**
 * Created by zoujie on 2018/1/24.
 */
public class ConcComponent implements Component {
    public void name() {
        System.out.println("name");
    }

    public void work() {
        System.out.println("work");
    }
}
