package zj.design.prototype;

/**
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
