package com.behaviourmodel.strategy;

/**
 * func desc:
 * 策略模式：定义一组算法，将每个算法都封装起来,并让他们之间可以互换
 * 策略载体 不能继承策略抽象结构,不然就写成代理模式了. 策略模式主要是可以动态替换具体的策略
 * 优点：算法自由切换  避免使用多重条件判断选择具体执行的策略. 扩展性好,新增策略只需要继承策略接口,策略类似一个可以反复拆卸的插件
 * 缺点：策略对象的增多,类的膨胀. 解决方法：使用组合其他模式修正，一般结合 工厂方法，代理模式和享元模式
 */
public class StrategyContext {

    public static void main(String[] args) {
        StrategyCarrier sc = new StrategyCarrier();
        sc.setStrategy(new ConcreteStrategyOne());
        sc.doStrategy();

        sc.setStrategy(new ConcreteStrategyTwo());
        sc.doStrategy();
    }

    //策略抽象结构
    interface IStrategy {
        void doStrategy();
    }

    //具体策略
    static class ConcreteStrategyOne implements IStrategy {

        @Override
        public void doStrategy() {
            System.out.println(" you are using strategy one.");
        }
    }

    static class ConcreteStrategyTwo implements IStrategy {

        @Override
        public void doStrategy() {
            System.out.println(" you are using strategy two.");
        }
    }

    // 策略载体
    static class StrategyCarrier {
        private IStrategy strategy;

        public StrategyCarrier() {
        }


        public void doStrategy() {
            strategy.doStrategy();
        }

        public IStrategy getStrategy() {
            return strategy;
        }

        public void setStrategy(IStrategy strategy) {
            this.strategy = strategy;
        }
    }
}
