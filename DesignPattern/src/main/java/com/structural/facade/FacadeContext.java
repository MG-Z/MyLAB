package com.structural.facade;

/**
 * 门面(外观模式) 封装多个子系统,提供统一的外界访问接口 有点类似委托
 * 注意:门面模式 中不能耦合多个子系统之间的业务逻辑代码  在必要的时候可以按照不同的接口暴露原则 生成不同的门面接口
 * Created by zoujie on 2018/2/7.
 */
public class FacadeContext {


    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.doSomethingAndCheck();

        facade.doSomethingNoCheck();
    }

    /**
     * 门面提供 提供  doSomething and check 的方法接口
     */
    static class Facade {
        private SubSystemOne one = new SubSystemOne();
        private SubSystemTwo two = new SubSystemTwo();
        private SubSystemChecker checker = new SubSystemChecker();

        public void doSomethingAndCheck() {
            this.one.doSomething();
            this.two.doSomething();
            this.checker.doCheck();
        }

        public void doSomethingNoCheck() {
            this.one.doSomething();
            this.two.doSomething();
        }
    }

    static class SubSystemOne {
        public void doSomething() {
            System.out.println("subsystem do something one.");
        }
    }

    static class SubSystemTwo {
        public void doSomething() {
            System.out.println("subsystem do something two.");
        }
    }

    static class SubSystemChecker {
        public void doCheck() {
            System.out.println("subsystem do check.");
        }
    }
}
