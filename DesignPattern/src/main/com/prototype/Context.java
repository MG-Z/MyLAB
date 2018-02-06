package main.com.prototype;

/**
 * func desc:
 */
public class Context {
    public static void main(String[] args) {
        PrototypeClass prototype = new PrototypeClass("template","template");
        int count = 20;
        while (count > 0){
            PrototypeClass copy = prototype.clone();
            copy.setDiffValue("diff"+ count--);
            System.out.println(Integer.toHexString(copy.hashCode()));
        }
    }
}
