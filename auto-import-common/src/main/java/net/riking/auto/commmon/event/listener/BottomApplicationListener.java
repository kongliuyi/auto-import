package net.riking.auto.commmon.event.listener;

import net.riking.auto.commmon.event.ApplicationPreparedEvent;
import net.riking.auto.commmon.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description 应用中后期监听器
 * @Author: kongLiuYi
 * @Date: 2020/2/16 17:21
 */
@Component
public class BottomApplicationListener implements SmartEtlApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationReadyEvent.class.isAssignableFrom(eventType)
                || ApplicationPreparedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            System.out.println("BottomApplicationListener 监听器处理 ApplicationReadyEvent 事件");
        }
        if (event instanceof ApplicationPreparedEvent) {
            System.out.println("BottomApplicationListener 监听器处理 ApplicationPreparedEvent 事件");
        }

    }
}
