package com.creational.factorymethod.standard;

import com.creational.factorymethod.AbsProduct;

/**
 * Created by zoujie on 2018/1/23.
 */
public abstract class AbsStandardFactory {

    public abstract  <T extends AbsProduct> T create(Class<T> clazz);
}
