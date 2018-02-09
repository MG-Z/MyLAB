package com.structural.bridge;

/**
 * 桥接模式:将抽象和实现解耦,使两者可以独立的变化  将类间的继承转变为 聚合关系,实现弱关联,安需暴露接口,
 * 继承有强侵入性,子类拥有父类的行为方法,避免子类拥有父类不应该暴露的接口
 *
 * 将被桥接对象的访问通过桥接对象代理 ,访问被桥接对象通过桥接对象访问,当被桥接对象需要暴露不同的接口时,
 * 可以通过新建桥接对象,或者扩展当前桥接对象实现
 *
 * 优点: 相对于访问端,访问桥接对象,不直接和被桥接对象交互,实现对于访问者透明
 *
 * 模式使用: 当类的继承层次太多,同时 并不是所有父类的方法都想暴露给子类时,可以通过桥接对象暴露指定的接口
 * Created by zoujie on 2018/2/7.
 */
public class BridgeContext {

    public static void main(String[] args) {
        ABSBridge bridge = new ConcurrentBridge(new ConcurrentImplement());
        bridge.request();
        ABSBridge otherBridge = new ConcurrentOtherBridge(new ConcurrentImplement());
        otherBridge.request();
    }


    static abstract class ABSBridge {

        protected Implementor  implementor;

        public ABSBridge(Implementor implementor) {
            this.implementor = implementor;
        }

        /**
         * 桥接方法
         */
        public void request(){
            implementor.ImplementorOne();
        }

    }

    /** 具体的桥接对象*/
    static class ConcurrentBridge extends ABSBridge{

        public ConcurrentBridge(Implementor implementor) {
            super(implementor);
        }

        /**
         * 桥接方法的修正
         */
        @Override
        public void request(){
            super.request();
            implementor.ImplementorTwo();
        }
    }

    /** 另外的桥接对象 */
    static class ConcurrentOtherBridge extends ABSBridge{

        public ConcurrentOtherBridge(Implementor implementor) {
            super(implementor);
        }
        //桥接新的方法接口
        @Override
        public void request(){
            implementor.ImplementorTwo();
            implementor.ImplementorOne();
        }
    }

    /**
     * 被桥接的对象
     */
    interface Implementor {
        void ImplementorOne();

        void ImplementorTwo();
    }
    /** 被桥接对象的具体实现*/
    static class ConcurrentImplement implements Implementor {

        @Override
        public void ImplementorOne() {
            System.out.println("concurrent implementor do one.");
        }

        @Override
        public void ImplementorTwo() {
            System.out.println("concurrent implementor do two.");
        }
    }
}
