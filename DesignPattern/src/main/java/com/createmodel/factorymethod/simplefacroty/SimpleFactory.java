package com.createmodel.factorymethod.simplefacroty;


import com.createmodel.factorymethod.AbsProduct;

/**
 * Created by zoujie on 2018/1/24.
 */
public class SimpleFactory {
    /** 简单工厂又称为静态工厂 */


    public static <T extends AbsProduct> T  create(Class< T > clazz){
        AbsProduct product = null;
        try {
            product = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
