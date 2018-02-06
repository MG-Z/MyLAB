package com.createmodel.prototype;

/**
 * 原型模式: 需要创建对象的原型通过 clone 方式实现   clone方式调用的native方法 效率比new操作高
 * Note:浅拷贝和深拷贝<深拷贝需要单独处理 引用对象的拷贝>
 * <需要参与clone的对象必须实现  Cloneable 接口  java 实现clone的一个标记>
 * func desc:
 */
public class PrototypeClass implements Cloneable {

    private String templateValue_1;
    private String templateValue_2;

    private String diffValue;

    public PrototypeClass(String templateValue_1, String templateValue_2) {
        this.templateValue_1 = templateValue_1;
        this.templateValue_2 = templateValue_2;
    }

    @Override
    protected PrototypeClass clone() {
        PrototypeClass copy = null;
        try {
            copy = (PrototypeClass) super.clone();
            //  如果包含 引用对象 此处单独处理 进行深拷贝
        } catch (Exception e) {
            e.printStackTrace();
            //TODO  容错处理
        }
        return copy;
    }

    public String getDiffValue() {
        return diffValue;
    }

    public void setDiffValue(String diffValue) {
        this.diffValue = diffValue;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PrototypeClass{");
        sb.append("templateValue_1='").append(templateValue_1).append('\'');
        sb.append(", templateValue_2='").append(templateValue_2).append('\'');
        sb.append(", diffValue='").append(diffValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
