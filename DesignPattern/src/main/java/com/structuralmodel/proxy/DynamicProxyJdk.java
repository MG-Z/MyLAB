package com.structuralmodel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * func desc:
 */
public class DynamicProxyJdk {
    public static void main(String[] args) {
        ITarget target = new ConcreteOperator();

        InvocationHandler proxyHandler = new TargetProxyHandlerByJdk(target);

        ITarget proxy = DynamicProxy.newProxyInstance(target,proxyHandler);

        proxy.doSomeThingOne();
        proxy.doSomeThingTwo();

    }

    public static class TargetProxyHandlerByJdk implements InvocationHandler {
        private Object target = null;

        public TargetProxyHandlerByJdk(Object target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(this.target, args);
        }
    }

    public static class DynamicProxy {
        public static <T> T newProxyInstance(Object target, InvocationHandler handler) {
            return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces()
                    , handler);
        }
    }

}
