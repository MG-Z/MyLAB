package com.behaviour.chainofresponsibility;

/**
 * 责任链模式:
 * 类似命令模式:请求处理解耦  结合命令模式 和模板方法模式
 * 缺点:责任链长度的影响
 * Created by zoujie on 2018/1/27.
 */
public class CORContext {

    public static void main(String[] args) {
        Command command1 = new Command() {
            @Override
            public int getResponser() {
                return 1;
            }

            @Override
            public void action() {
                System.out.println(" command 1 done.");
            }
        };

        Command command2 = new Command() {
            @Override
            public int getResponser() {
                return 2;
            }

            @Override
            public void action() {
                System.out.println(" command 2 done.");
            }
        };

        IHandler handler = new BaseHandler(1) {
            @Override
            public void response() {
                System.out.println("I'm do this command. My Number is :" + this.responsibility);
            }
        }.addNext(new BaseHandler(2) {
            @Override
            public void response() {
                System.out.println("I'm do this command. My Number is :" + this.responsibility);
            }
        });

        handler.execute(command1);
        handler.execute(command2);
    }


    public static abstract class Command {
        public abstract int getResponser();

        public abstract void action();
    }

    public interface IHandler {
        void execute(Command command);
    }

    public abstract static class BaseHandler implements IHandler {
        private IHandler next;
        public final int responsibility;

        public BaseHandler(int responsibility) {
            this.responsibility = responsibility;
        }

        public void execute(Command command) {
            if (command.getResponser() == responsibility) {
                command.action();
                response();
            } else {
                next.execute(command);
            }
        }

        public abstract void response();

        public BaseHandler addNext(IHandler handler) {
            this.next = handler;
            return this;
        }
    }
}
