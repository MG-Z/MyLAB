package com.Linked;


/**
 * 包含头尾结点的单向链表
 * func desc:
 */
public class LinkedContext {


    public static void main(String[] args) {
        Linked<Integer> linked = new Linked<>();
        linked.addFirst(1).addFirst(2).addFirst(3).addFirst(4);
        linked.addLast(-1).addLast(-2).addLast(-3).addLast(-4);
        System.out.println(linked.toString());
        transferToHead(linked, 2, 3);
        System.out.println(linked.toString());

        reverse(linked);
        System.out.println(linked.toString());
    }

    public static <E> void reverse(Linked<E> list) {
        Node<E> temp, h = list.head;
        while (h.next != null) {
            temp = h.next;
            h.next = h.next.next;
            temp.next = list.head;
            list.head = temp;
        }
    }

    /**
     * 移动指定开始到结束节点到链表头   A->B->C->D->E  => C->D->A->B->E
     *
     * @param list 给定节点
     * @param from 开始移动的节点  start with zero
     * @param to   结束的节点      start with zero
     */
    public static <E> void transferToHead(Linked<E> list, int from, int to) {
        if (from > to) throw new RuntimeException(" from can not greater than to");
        if (from == 0) return;  // 处理头结点问题
        Node<E> h = list.head;
        int size = 0; //统计链表长度
        while (h != null) {
            size++;
            h = h.next;
        }

        if (size == 0 || from >= size) return;

        if (to >= size) to = size - 1;

        Node<E> fromNode = null, toNode = null, search = list.head;
        int cursor = 0;

        while (search != null) {
            if (cursor + 1 == from) fromNode = search;
            if (cursor == to) {
                toNode = search;
                break;
            }
            search = search.next;
            cursor++;
        }

        if (fromNode == null || toNode == null) throw new RuntimeException("can not location nodes");
        Node<E> tempH = list.head;
        Node<E> tempNewH = fromNode.next;
        fromNode.next = toNode.next;
        //处理tail
        if (fromNode.next == null) {
            list.tail = fromNode;
        }
        toNode.next = tempH;
        list.head = tempNewH;
    }

    static class Linked<E> {

        private Node<E> head;
        private Node<E> tail;

        public Linked() {
        }

        public Linked<E> addFirst(E e) {
            final Node<E> h = head;
            final Node<E> newNode = new Node<>(e, null);
            head = newNode;
            if (h != null) {
                newNode.next = h;
            } else {
                tail = newNode;
            }
            return this;
        }

        public Linked<E> addLast(E e) {
            final Node<E> t = tail;
            final Node<E> newNode = new Node<>(e, null);
            tail = newNode;
            if (t != null) {
                t.next = newNode;
            } else {
                head = newNode;
            }
            return this;
        }

        public E remove(E e) {
            if (head == null) return null;
            E item = null;
            //单独处理头结点
            if (head.item == e) {
                item = head.item;
                head.item = null;
                // if( head.next == null)  此处只有一个结点
                if (head == tail) {
                    head = tail = null;
                } else {
                    head = head.next;
                }
                return item;
            }
            Node<E> curNode = head;
            while (curNode.next != null) {
                if (curNode.next.item == e) {
                    item = curNode.next.item;
                    Node<E> find = curNode.next;
                    curNode.next = find.next;
                    // 遍历到尾结点， 要处理tail 结点
                    if (curNode.next == null) {
                        tail = curNode;
                    }
                    find.item = null;
                    find.next = null;
                    break;
                }
                curNode = curNode.next;     //后移结点
            }
            return item;
        }

        public E removeFirst() {
            if (head == null) return null;
            final Node<E> h = head;
            E e = h.item;
            h.item = null;
            head = h.next;
            if (head == null) {
                tail = null;
            }
            h.next = null;
            return e;
        }

        public E removeLast() {
            if (head == null) return null;
            Node<E> findNode = head;
            while (findNode.next != tail) {
                findNode = findNode.next;
            }
            E item = tail.item;
            findNode.next = null;
            tail = findNode;
            return item;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Linked{");
            Node<E> p = head;
            while (p != null) {
                sb.append(p.toString());
                if (p.next != null) {
                    sb.append("->");
                }
                p = p.next;
            }
            sb.append('}');

            sb.append("\nhead:").append(head.toString()).append("tail:").append(tail.toString());
            return sb.toString();
        }
    }
}
