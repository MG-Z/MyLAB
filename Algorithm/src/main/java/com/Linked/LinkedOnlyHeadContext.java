package com.Linked;

/**
 * func desc: 只包含头结点的单向链表
 */
public class LinkedOnlyHeadContext {

    public static void main(String[] args) {
        LinkedOnlyHead<Integer> list = new LinkedOnlyHead<>();
        list.addFirst(1).addFirst(2).addFirst(3).addFirst(4);
        list.addLast(0).addLast(-1).addLast(-2).addLast(-3);
        System.out.println("size:" + list.size());
        System.out.println("origin:" + list);
        list.head = LinkedListUtils.recallReverse(list.head);
        System.out.println("reverse:" + list);
        list.removeByIndex(2);
        System.out.println("removeIndex:" + list);
        list.removeAll(2);
        System.out.println("removeAllElement:" + list);
        list.removeFirst(4);
        System.out.println("removeFirst:" + list);
    }


    static class LinkedOnlyHead<E> {
        Node<E> head;

        public LinkedOnlyHead() {
        }

        public LinkedOnlyHead addFirst(E e) {
            final Node<E> h = head;
            Node<E> newNode = new Node<>(e, null);
            head = newNode;
            if (h != null) {
                newNode.next = h;
            }
            return this;
        }

        public LinkedOnlyHead addLast(E e) {
            Node<E> newNode = new Node<>(e, null);
            Node<E> h = head;
            if (h == null)
                head = newNode;
            else {
                while (h.next != null) {
                    h = h.next;
                }
                h.next = newNode;
            }
            return this;
        }

        public Node<E> findNode(E e) {
            Node<E> h = head, find = null;
            while (h != null) {
                if (h.item == e) {
                    find = h;
                    break;
                }
                h = h.next;
            }
            return find;
        }

        public int size() {
            Node<E> h = head;
            int size = 0;
            while (h != null) {
                h = h.next;
                size++;
            }
            return size;
        }

        private void freeNode(Node<E> node) {
            node.item = null;
            node.next = null;
        }


        public E removeByIndex(int index) {
            int size = size();
            if (index >= size) throw new IllegalArgumentException(" index out of lists bounds");
            Node<E> nodeCursor = head, pre = null;
            E item = null;
            int indexCursor = 0;
            while (nodeCursor != null) {
                if (indexCursor == index) {
                    Node<E> find = nodeCursor;
                    item = find.item;
                    if (pre == null) {            // head
                        head = find.next;
                    } else {
                        pre.next = find.next;
                    }
                    freeNode(find);
                    break;
                }
                indexCursor++;
                pre = nodeCursor;
                nodeCursor = nodeCursor.next;
            }
            return item;
        }


        public void removeFirst(E e) {
            remove(e, false);
        }

        public void removeAll(E e) {
            remove(e, true);
        }

        private void remove(E e, boolean removeRepeated) {
            Node<E> findNode, p = head, pre = null;
            while (p != null) {
                if (p.item == e) {
                    findNode = p;
                    if (pre == null) {  // 头结点
                        head = findNode.next;
                    } else {
                        pre.next = findNode.next;
                    }
                    freeNode(findNode);
                    if (!removeRepeated) break;
                    p = pre.next;       // 继续查找所有 pre不变 p前移
                    continue;
                }
                pre = p;
                p = p.next;
            }
        }

        public void reverse() {
            Node<E> pre = null, cur = head, curNext;
            while (cur != null) {
                curNext = cur.next;
                if (curNext == null) {
                    head = cur;
                }
                cur.next = pre;
                pre = cur;
                cur = curNext;
            }
        }


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("LinkedOnlyHead{");
            Node<E> p = head;
            while (p != null) {
                sb.append(p.toString());
                if (p.next != null) {
                    sb.append("->");
                }
                p = p.next;
            }
            sb.append('}');
            return sb.toString();
        }
    }

}
