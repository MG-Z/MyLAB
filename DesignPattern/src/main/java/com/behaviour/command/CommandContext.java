package com.behaviour.command;

/**
 * 命令模式:实现类间解耦 ,行为发起者和行为执行者解耦,并且行为发起者不关注行为执行者<可以通过命令自解释行为执行者>
 * 命令模式缺点: 类数量的膨胀 <一个命令一个类>
 * 命令模式适合结合 责任链模式 模板方法模式一起使用
 * Created by zoujie on 2018/1/26.
 */
public class CommandContext {

    public static void main(String[] args) {
        //1 .
        CommandDispacher.dispacher(new BaseCommand(1) {
            public void action() {
                System.out.println(" I'm first Command");
            }
        });

        CommandDispacher.dispacher(new BaseCommand(2) {
            public void action() {
                System.out.println("I'm second Command");
            }
        });
    }



    public interface ICommand {
        void action();
    }

    public static abstract class BaseCommand implements ICommand {
        protected int invokeId;

        public BaseCommand(int invokeId) {
            this.invokeId = invokeId;
        }
    }

    public interface IInvoker {
        void doCommand(ICommand command);
    }

    public abstract static class BaseInvoker implements IInvoker {
        protected final int INVOKER_ID;

        public BaseInvoker(int id) {
            this.INVOKER_ID = id;
        }

        public void addCommand(BaseCommand command) {
            System.out.println("Invoker:"+ INVOKER_ID);
            doCommand(command);
        }

        public void doCommand(ICommand command) {
            command.action();
        }
    }

    public static class InvokerOne extends BaseInvoker{
        private static final InvokerOne INSTANCE = new InvokerOne();
        private InvokerOne() {
            super(1);
        }

        public static InvokerOne getInstance(){
            return INSTANCE;
        }
    }

    public static class InvokerTwo extends BaseInvoker{
        private static final InvokerTwo INSTANCE = new InvokerTwo();
        private InvokerTwo() {
            super(2);
        }

        public static InvokerTwo getInstance(){
            return INSTANCE;
        }
    }

    public static class CommandDispacher{
        public static void dispacher(BaseCommand command){
            if(command.invokeId == 1){
                InvokerOne.getInstance().addCommand(command);
            }else if (command.invokeId == 2){
                InvokerTwo.getInstance().addCommand(command);
            }else {
                System.out.println("Can Not Identify");
            }
        }
    }
}
