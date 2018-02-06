package com.createmodel.factorymethod.standard;

import com.createmodel.factorymethod.AbsProduct;

/**
 * Created by zoujie on 2018/1/23.
 */
public class StandardFactory  extends AbsStandardFactory{
    @Override
    public <T extends AbsProduct> T create(Class<T> clazz) {
        try {
            T  t = clazz.newInstance();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
