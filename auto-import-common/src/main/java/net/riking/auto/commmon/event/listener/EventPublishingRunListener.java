
package net.riking.auto.commmon.event.listener;


import net.riking.auto.commmon.event.ApplicationPreparedEvent;
import net.riking.auto.commmon.event.ApplicationReadyEvent;
import net.riking.auto.commmon.event.ApplicationStartingEvent;
import net.riking.auto.commmon.job.EtlApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Component;

/**
 * @Description 事件发布运行时期监听器
 * @Author: kongLiuYi
 * @Date: 2020/2/16 17:24
 */
@Component
public class EventPublishingRunListener implements ETLApplicationRunListener {

    private final EtlApplication etlApplicationSource;

    private final SimpleApplicationEventMulticaster initialMulticaster;

    /**
     *
     */
    public EventPublishingRunListener(EtlApplication applicationSource) {
        this.etlApplicationSource = applicationSource;
        this.initialMulticaster = new SimpleApplicationEventMulticaster();
        for (ApplicationListener<?> listener : etlApplicationSource.getListeners()) {
            this.initialMulticaster.addApplicationListener(listener);
        }
    }


    @Override
    public void starting() {
        this.initialMulticaster.multicastEvent(new ApplicationStartingEvent(this.etlApplicationSource));
    }


    @Override
    public void contextLoaded() {
        this.initialMulticaster.multicastEvent(new ApplicationPreparedEvent(this.etlApplicationSource));
    }


    @Override
    public void running() {
        this.initialMulticaster.multicastEvent(new ApplicationReadyEvent(this.etlApplicationSource));
    }


}
