package zj.design.builder;

/**
 * func desc:
 */
public class Context {

    public static void main(String[] args) {
        Director director = new Director();
        director.setBuilder(new ConcreteBuilder());
        director.getBuilder().config(new ConfigContext()).buildProduct();
    }
}
