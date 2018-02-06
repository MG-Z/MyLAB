package com.createmodel.factorymethod;

import com.createmodel.factorymethod.simplefacroty.SimpleFactory;
import com.createmodel.factorymethod.standard.StandardFactory;

/**
 * Created by zoujie on 2018/1/23.
 */
public class Context {

    public static void main(String[] args) {
        StandardFactory factory = new StandardFactory();
        AbsProduct product = factory.create(ProductOne.class);
        product.whoIamI();

        product = factory.create(ProductTwo.class);
        product.whoIamI();


        ProductTwo _product = SimpleFactory.create(ProductTwo.class);
        _product.whoIamI();
    }
}
