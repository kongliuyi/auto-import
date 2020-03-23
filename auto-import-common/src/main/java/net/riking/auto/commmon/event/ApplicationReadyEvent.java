package net.riking.auto.commmon.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Description
 * @Author: kongLiuYi
 * @Date: 2020/2/16 23:26
 */
public class ApplicationReadyEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ApplicationReadyEvent(Object source) {
        super(source);
    }
}
