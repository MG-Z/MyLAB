package com.structural.proxy;

/**
 * func desc:  普通代理模式
 */
public class GeneralProxy {

    public static void main(String[] args) {
        ITarget target = new OperatorProxy(new ConcreteOperator());
        target.doSomeThingOne();
        target.doSomeThingTwo();
    }


    public static class OperatorProxy implements ITarget, IProxyEnhance {
       private   ITarget target;

        public OperatorProxy(ITarget target) {
            this.target = target;
        }

        public void doSomeThingOne() {
            enhanceDoSomeThingOne();
            target.doSomeThingOne();
        }

        public void doSomeThingTwo() {
            enhanceDoSomeThingTwo();
            target.doSomeThingTwo();
        }

        public void enhanceDoSomeThingOne() {
            System.out.println("enhance do some thing one");
        }

        public void enhanceDoSomeThingTwo() {
            System.out.println("enhance do some thing two");
        }
    }

}
