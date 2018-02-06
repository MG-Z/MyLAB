package com.clone;

/**
 * func desc:
 */
public class Children extends Parent implements Cloneable {
    private String sex;

    private Children friend = null;

    public Children(String name) {
        super(name);
        this.sex = "man";
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Children getFriend() {
        return friend;
    }

    public void setFriend(Children friend) {
        this.friend = friend;
    }

    @Override
    protected Children clone() {
        Children copy = null;
        try {
            copy = (Children) super.clone();
            if (copy.friend != null) {
                copy.friend = friend.clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Children{");
        sb.append("sex='").append(sex).append('\'');
        sb.append(", friend=").append(friend);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
