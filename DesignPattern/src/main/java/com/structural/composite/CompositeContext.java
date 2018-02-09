package com.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * func desc:
 * 组合模式: 一种优化的继承特性实现 主要解决复杂的树形的结构继承结构 扩展 各个分支结构中可以新增父类结点
 * 结构：
 * Component : 抽象构件 抽象 参加组合对象的共有属性和方法,还可以定义默认实现
 * Leaf : 叶子构件：其下没有其他分支,组合对象的最小构件
 * Composite: 树枝构件：作用组合树枝构件和叶子构件形成组合结构
 *
 * 组合模式优点：
 * 1.高层模块调用简单,只需要调用抽象构件操作
 * 2.结点的增删自由
 */
public class CompositeContext {


    public static void main(String[] args) {
        //创建一个组合根对象
        Composite root = new Composite("root", 1);
        // 创建一个分支对象
        Composite branchOne = new Composite("branchOne", 2);
        Composite branchTwo = new Composite("branchTwo", 2);

        Leaf leafOne = new Leaf("Leaf One ", 3);

        Leaf leafTwo = new Leaf("Leaf Two", 3);

        //组合各级对象

        root.addChildren(branchOne);
        root.addChildren(branchTwo);

        branchOne.addChildren(leafOne);
        branchTwo.addChildren(leafTwo);

        display(root);
    }

    public static void display(Composite root) {
        root.getInfo();
        for (Component component : root.getChildrens()) {
            if (component instanceof Composite) {
                display((Composite) component);
            } else if (component instanceof Leaf) {
                component.getInfo();
            }
        }
    }

    /**
     * 抽象构件
     */
    static abstract class Component {
        /** 此属性为所有构件都拥有的 */
        protected String name;
        protected int age;


        public Component(String name, int age) {
            this.name = name;
            this.age = age;
        }

        /** 所有构件共有的方法 */
        public void getInfo() {
            StringBuilder sb = new StringBuilder(" ");
            sb.append("name:").append(name).append(" age:").append(age).append(" ");
            System.out.println(sb.toString());
        }

        public abstract void doSomething();

    }

    /** 树枝构件 定义树枝才具有的特殊行为或者属性 */
    static class Composite extends Component {
        //树枝构件包含子构件
        private List<Component> childrens = new ArrayList<>();

        public Composite(String name, int age) {
            super(name, age);
        }

        public void addChildren(Component component) {
            childrens.add(component);
        }

        public void removeChildren(Component component) {
            childrens.remove(component);
        }

        public List<Component> getChildrens() {
            return childrens;
        }

        @Override
        public void doSomething() {
            System.out.println("composite do somethings.");
        }
    }

    // 组合构件的最小结点
    static class Leaf extends Component {

        public Leaf(String name, int age) {
            super(name, age);
        }

        @Override
        public void doSomething() {
            System.out.println("Leaf do somethings.");
        }
    }
}
