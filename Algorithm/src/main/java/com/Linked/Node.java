package com.Linked;

/**
 * func desc:
 */
public class Node<E> {
    E item;
    Node<E> next;

    public Node(E item, Node<E> next) {
        this.item = item;
        this.next = next;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        sb.append("item=").append(item);
        sb.append(' ');
        return sb.toString();
    }
}