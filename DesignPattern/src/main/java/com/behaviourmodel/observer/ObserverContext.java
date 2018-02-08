package com.behaviourmodel.observer;

/**
 * func desc:观察这模式  订阅发布模型
 * 使用最多： 事件监听 ,具体分为 监听对象的 同步执行和异步执行
 */
public class ObserverContext {

    private IEventDispatcher eventDispatcher;

    public ObserverContext(IEventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }


    public static void main(String[] args) {
        ObserverContext context = new ObserverContext(new SimpleEventDispatcher());
        context.eventDispatcher.registerEventListener(1, new EventListener());
        context.eventDispatcher.fireEvent(new Event(1,1));
    }


    static class EventListener implements IEventListener {

        @Override
        public void eventPerformed(Event event) throws Exception {
            if (event.getEventId() == 1) {
                System.out.println("listener get event info." + "  params:" + event.getParams());
            }
        }
    }


}
