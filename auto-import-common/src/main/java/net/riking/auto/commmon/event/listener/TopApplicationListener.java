package net.riking.auto.commmon.event.listener;

import lombok.extern.slf4j.Slf4j;
import net.riking.auto.commmon.config.ETLProperties;
import net.riking.auto.commmon.event.ApplicationPreparedEvent;
import net.riking.auto.commmon.event.ApplicationStartingEvent;
import net.riking.auto.commmon.handle.FileHandle;
import net.riking.auto.commmon.job.EtlApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @Description 应用前中期监听器
 * @Author: kongLiuYi
 * @Date: 2020/2/16 17:21
 */
@Component
@Slf4j
@EnableConfigurationProperties({ ETLProperties.class})
public class TopApplicationListener implements SmartEtlApplicationListener {

    @Autowired
    private ETLProperties etlProperties;

    @Autowired
    private List<FileHandle> handles;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationStartingEvent.class.isAssignableFrom(eventType)
                || ApplicationPreparedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {
            try {
                extract();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        if (event instanceof ApplicationPreparedEvent) {
            System.out.println("TopApplicationListener 监听器处理 ApplicationPreparedEvent 事件");
        }

    }



    public void extract() throws Throwable {
        File directoryFile = new File(etlProperties.getSourcePath());
        if (directoryFile.exists() && directoryFile.isDirectory()) {
            File[] files = directoryFile.listFiles();
            if(files.length == 0){
                log.error("数据源不存在，请核查...");
                return;
            }
            for (File file : files) {
                FileHandle<File> fileHandle = getFileHandle(file);
                if(fileHandle == null){
                    log.error(file.getName()+":找不到对应 FileHandle，请核查...");
                    continue;
                }
                fileHandle.parse(file);
                fileHandle.handles();
            }

        }
    }


    protected FileHandle<File> getFileHandle(File file) {

        for (FileHandle<File> handle : handles) {
            if (handle.supports(file))
                return handle;
        }
        return null;
    }
}
