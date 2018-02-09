package com.structural.decorator;

/**
 * Created by zoujie on 2018/1/24.
 */
public abstract class ABSDecorator implements Component {
   protected Component component;

    public ABSDecorator(Component component) {
        this.component = component;
    }

}
