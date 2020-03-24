package net.riking.auto.commmon.job;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.riking.auto.commmon.event.listener.EventPublishingRunListener;
import net.riking.auto.commmon.event.listener.SmartEtlApplicationListener;
import net.riking.auto.commmon.handle.FileHandleAdaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component

@Getter
public class EtlApplication {

    private Date dataDate;

    @Autowired
    private EntityRepo entityRepo;

    @Autowired
    private List<FileHandleAdaper> handles;

    @Autowired
    private List<SmartEtlApplicationListener> listeners;

    @Autowired
    private EventPublishingRunListener runListener;

    private Map<Class, List<Object>> baseData = new HashMap<>();





    public void extract()  {
        runListener.starting();

        runListener.running();

        runListener.contextLoaded();

        // 更新数据
    //    updateDate();

        // 备份文件
      //  backupsFile();

    }

/*    private void updateDate() {

        handles.stream().forEach(a->{
                a.getBaseData().forEach((b, c) -> {
                    entityRepo.batchDelete((Class) b, this.dataDate);
                    entityRepo.batchInsert((List) c);
                });
        });*/

/*        FileHandleAdaper.forEach((a, b) -> {
            entityRepo.batchDelete(a, this.dataDate);
            entityRepo.batchInsert(b);
        });
    }*/

/*    private void backupsFile() {
        try {
            File srcDir = new File(sourcePath);
          //  File destDir = new File(backupsPath + File.separator + DateUtil.dataToStr(this.dataDate, "yyyy-MM-dd"));
            if (destDir.exists()) {
                FileUtils.cleanDirectory(destDir);
            }
            FileUtils.copyDirectory(srcDir, destDir);
            FileUtils.cleanDirectory(srcDir);
        } catch (IOException e) {
            log.error("备份文件出错...", e);
        }
    }*/

}
