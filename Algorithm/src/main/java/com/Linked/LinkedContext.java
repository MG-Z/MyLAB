package com.Linked;

/**
 * func desc:
 */
public class LinkedContext {


    public static void main(String[] args) {
        Linked<Integer> linked = new Linked<>();
        linked.addFirst(1).addFirst(2).addFirst(3).addFirst(4);
        linked.addLast(-1).addLast(-2).addLast(-3).addLast(-4);
//        linked.addLast(1).addLast(2);
        linked.remove(-4);
        linked.removeFirst();
        linked.removeLast();
        System.out.println(linked.toString());
    }


    static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("[");
            sb.append(item);
            sb.append(']');
            return sb.toString();
        }
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
                if(p.next != null){
                    sb.append("->");
                }
                p = p.next;
            }
            sb.append('}');
            return sb.toString();
        }
    }
}
