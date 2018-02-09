package com.behaviour.observer;


/**
 * 事件对象
 * Created by zj.zou
 */
public class Event {
    /**
     * 事件ID
     */
    private int eventId;
    /**
     * 事件执行需要的参数对象
     */
    private Object params;

    /**
     * @param eventId 事件ID
     * @param params 事件参数
     */
    public Event(int eventId, Object params) {
        this.eventId = eventId;
        this.params = params;
    }

    private Event() {
    }

    /**
     * 事件ID
     * @return
     */
    public int getEventId() {
        return eventId;
    }

    /**
     * 执行事件的事件对象
     * @return
     */
    public Object getParams() {
        return params;
    }
}
