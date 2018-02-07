package com.Linked;

/**
 * func desc: 链表的辅助方法
 */
public class LinkedListUtils {

    /**
     * 单链表的反转
     * @param head
     * @param <E>
     */
    public static <E> Node<E> reverse(Node<E> head) {
        Node<E> pre = null, nodeCursor = head, next, result = null;
        while (nodeCursor != null) {
            next = nodeCursor.next;
            if (next == null) {
                result = nodeCursor;
            }
            nodeCursor.next = pre;
            pre = nodeCursor;
            nodeCursor = next;
        }
        return result;
    }

    public static <E> Node<E> recallReverse(Node<E> head) {
        Node<E> newHead = recursionNode(head);
        return newHead;
    }

    private static <E> Node<E> recursionNode(Node<E> p) {
        if (p.next == null) {
            return p;  // 头结点指向尾结点
        }
        Node<E> head = recursionNode(p.next);
        p.next.next = p;  // 将尾结点作为头结点,反向获取结点依次插入
        p.next = null;
        return head;
    }
}
