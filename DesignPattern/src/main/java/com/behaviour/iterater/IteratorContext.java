package com.behaviour.iterater;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * func desc: 迭代器模式, 基本很少使用,此处用来了解原理
 */
public class IteratorContext {

    public static void main(String[] args) {
        Components components = new Components();
        components.addComponent(new Component("1"));
        components.addComponent(new Component("2"));
        components.addComponent(new Component("3"));
        components.addComponent(new Component("4"));

        Iterator<Component> iterator = components.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

    static class Component {
        public Component(String name) {
            this.name = name;
        }

        public String name;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Component{");
            sb.append("name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    static class Components {
        private List<Component> components = new ArrayList<>();

        public Iterator iterator() {
            return new ComponentIterator(components);
        }

        public void addComponent(Component component) {
            this.components.add(component);
        }
    }

    interface IComponentIterator<E> extends Iterator {

    }

    static class ComponentIterator implements IComponentIterator {
        private List<Component> allElements;
        private int cursor = 0;

        public ComponentIterator(List<Component> allElements) {
            this.allElements = allElements;
        }

        @Override
        public boolean hasNext() {
            return cursor < allElements.size();
        }

        @Override
        public Object next() {
            return allElements.get(cursor++);
        }

        @Override
        public void remove() {

        }
    }
}
