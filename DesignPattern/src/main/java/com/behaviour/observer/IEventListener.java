package com.behaviour.observer;


/**
 * func: 事件监听器
 * Created by zj.zou
 */
public interface IEventListener {

    /**
     * 此方法用于 子类实现用
     * @param event
     * @throws Exception
     */
    void eventPerformed(Event event) throws Exception;
}
