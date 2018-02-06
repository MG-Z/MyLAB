package com.clone;

/**
 * func desc:
 */
public class Context {
    public static void main(String[] args) {
        Children friend = new Children("friend");

        Children my = new Children("my");
        my.setFriend(friend);

        System.out.println(my.hashCode());
        System.out.println(my.toString());
        System.out.println(my.getFriend().hashCode());

        Children copy = my.clone();
        System.out.println(copy);
        System.out.println(copy.hashCode());
        System.out.println(copy.getFriend().hashCode());
    }
}
