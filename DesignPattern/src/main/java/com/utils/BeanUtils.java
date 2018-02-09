package com.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * func desc:
 * bean 操作相关的工具类
 */
public class BeanUtils {

    public static Map<String, Object> backUpFieldFromBean(Object bean) {
        Map<String, Object> result = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                String fieldName = descriptor.getName();
                if (fieldName.equalsIgnoreCase("class")) continue;
                Method getter = descriptor.getReadMethod();
                boolean ac = getter.isAccessible();
                if (!ac) {
                    getter.setAccessible(true);
                }
                Object fieldValue = getter.invoke(bean, new Object[]{});
                result.put(fieldName, fieldValue);
                getter.setAccessible(ac);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void coverFiledToBean(Object bean, Map<String, Object> fields) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                String fieldName = descriptor.getName();
                if (fieldName.equalsIgnoreCase("class")) continue;
                if (fields.containsKey(fieldName)) {
                    Method setter = descriptor.getWriteMethod();
                    boolean ac = setter.isAccessible();
                    if (!ac) setter.setAccessible(true);
                    setter.invoke(bean, new Object[]{fields.get(fieldName)});
                    setter.setAccessible(ac);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
