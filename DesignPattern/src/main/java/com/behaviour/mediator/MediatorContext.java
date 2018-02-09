package com.behaviour.mediator;

/**
 * note: 中介模式主要用于解耦对象自己的互相引用 造成的网状结构图。 所以没有达到网状的互相依赖也不要随便使用
 * 因为本身中介者 就是膨胀到难以处理
 * Created by zoujie on 2018/1/26.
 */
public class MediatorContext {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();

        ColleagueOne one = new ColleagueOne(mediator);
        ColleagueTwo two = new ColleagueTwo(mediator);

        mediator.setOne(one);
        mediator.setTwo(two);

        one.requestTwoDo();

        two.requestOneSay();
    }



    public static class Mediator{
        //需要中介管理的 同事类
        private ColleagueOne one;
        private ColleagueTwo two;
        /** 同事类不使用构造函数注入对象,因为不是每个需要管理的同事类都必须存在 */
        public Mediator() {
        }

        public void requestColleagueTwoDoSomeThing(){
            two.doSomeThing();
        }

        public void requestColleagueOneSaySomeThing(){
            one.saySomeThing();
        }

        public Colleague getOne() {
            return one;
        }

        public void setOne(ColleagueOne one) {
            this.one = one;
        }

        public Colleague getTwo() {
            return two;
        }

        public void setTwo(ColleagueTwo two) {
            this.two = two;
        }
    }

    //每个同事类 行为不尽相同,所以没得必要抽象相关的公共方法
    public static abstract class Colleague{
        protected Mediator mediator;
        // 同事类 持有的 中介对象使用 构造方法注入,每个需要中介交互的对象,都必须要持有中介对象
        public Colleague(Mediator mediator) {
            this.mediator = mediator;
        }
    }

    public static class ColleagueOne extends Colleague{
        public ColleagueOne(Mediator mediator) {
            super(mediator);
        }

        public void saySomeThing(){
            System.out.println("I'm Colleague one");
        }

        public void requestTwoDo(){
            mediator.requestColleagueTwoDoSomeThing();
        }
    }

    public static class ColleagueTwo extends Colleague{
        public ColleagueTwo(Mediator mediator) {
            super(mediator);
        }

        public void doSomeThing (){
            System.out.println("I'm Colleague two. I'm do Something");
        }

        public void requestOneSay(){
            mediator.requestColleagueOneSaySomeThing();
        }
    }
}
