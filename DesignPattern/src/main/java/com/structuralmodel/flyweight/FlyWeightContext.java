package com.structuralmodel.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 享元模式：
 * Flyweight模式的设计意图是避免大量相似对象的创建。一般会先创建一个或一组具有相同属性的元对象，使客户端可以共享使用这个元对象。由于元对象被大量的共享使用，因此对内存的占用有很大的改善。
 * Flyweight对象能够做到共享的关键是区分内部状态（Internal State）和外部状态（External State）。
 * 内部状态存储在Flyweight对象内部，并且不会随环境的改变而有改变。因此，一个Flyweight对象可以具有内部状态并可以共享.
 * 内部对象是所属类型的固有属性,不随使用环境改变而变,共享也只共享内存状态
 * 外部状态是随环境改变而改变的、不可共享的状态。Flyweight对象的外部状态必须由客户端保存，并在对象被创建后，在需要使用的时候再传给Flyweight对象。
 * 外部状态不可以影响Flyweight对象的内部状态。换句话说，它们是相互独立的
 * func desc:
 * 优缺点
 * Flyweight模式的优点在于它大幅度的降低了内存中对象的数量。但是，它做到这一点所付出的代价也很高：
 * Flyweight模式使得系统更加复杂。为了使对象可以共享，需要将一些状态外部化，这使得程序的逻辑复杂化。
 * Flyweight模式将共享对象的状态外部化，而读取外部状态使得运行时间稍微变长，算是以时间换取了空间。
 * 注意点： ObjectPool 对象池,池中对象是不共享的，一旦被某个客户端使用，其他客户端不能使用 主要解决创建耗时的对象
 * 享元模式：对象是多个客户端共享对象 解决多对象内存膨胀问题
 */
public class FlyWeightContext {
    public static void main(String[] args) {
        List<ClientContext> allClients = new ArrayList<>(20);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int age = 1 + random.nextInt(3);
            allClients.add(new ClientContext(FlyWeightFactory.getInstance().getFlyWeight("con", age)));
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(allClients.get(i).toString());
        }

        System.out.println(" pools size :" + FlyWeightFactory.getInstance().pools.size());
    }

    // 需要使用共享对象客户端环境类
    static class ClientContext {
        private AbsFlyWeight concreteFlyWeight;

        public ClientContext(AbsFlyWeight concreteFlyWeight) {
            this.concreteFlyWeight = concreteFlyWeight;
        }

        public AbsFlyWeight getConcreteFlyWeight() {
            return concreteFlyWeight;
        }

        public void setConcreteFlyWeight(AbsFlyWeight concreteFlyWeight) {
            this.concreteFlyWeight = concreteFlyWeight;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ClientContext{");
            sb.append(concreteFlyWeight);
            sb.append('}');
            return sb.toString();
        }
    }

    static class FlyWeightFactory {
        private final Map<String, AbsFlyWeight> pools = new ConcurrentHashMap<>();

        private static class InstanceHolder {
            private static final FlyWeightFactory INSTANCE = new FlyWeightFactory();
        }

        public static FlyWeightFactory getInstance() {
            return InstanceHolder.INSTANCE;
        }

        /**
         * 获取共享对象的工厂类
         */
        private FlyWeightFactory() {
            //init pools
            pools.put("con_1", new ConcreteFlyWeight("con", 1));
            pools.put("con_2", new ConcreteFlyWeight("con", 2));
            pools.put("con_3", new ConcreteFlyWeight("con", 3));
        }

        public AbsFlyWeight getFlyWeight(String name, int age) {
            String key = name + "_" + age;
            if (!pools.containsKey(key)) {
                pools.put(key, new ConcreteFlyWeight(name, age));
            }
            return pools.get(key);
        }

    }

    /**
     * 共享对象的抽象 细粒度的不变类对象
     */
    static abstract class AbsFlyWeight {
        /** inner state  一旦创建就不可改变,需要共享 */
        private String name;
        private int age;
        //作为唯一标记享元对象的标记
        public final String key;

        public AbsFlyWeight(String name, int age) {
            this.name = name;
            this.age = age;
            key = name + "_" + age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("AbsFlyWeight{");
            sb.append("name='").append(name).append('\'');
            sb.append(", age=").append(age);
            sb.append(", key='").append(key).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    static class ConcreteFlyWeight extends AbsFlyWeight {

        public ConcreteFlyWeight(String name, int age) {
            super(name, age);
        }
    }


}
