package net.riking.auto;


import lombok.extern.slf4j.Slf4j;

import net.riking.auto.commmon.annotation.ComponentScan;
import net.riking.auto.commmon.job.EntityRepo;
import net.riking.auto.commmon.job.EtlApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * 兼容多余的项目
 *
 * @author li.xia
 */
@SpringBootApplication
@ComponentScan(value = "net.riking.auto.pojo.fbds")
@Slf4j
public class EntranceApplication implements ApplicationRunner {


/*    @Autowired
    RabobankRpscService rabobankRpscService;*/


    @Autowired
    EtlApplication etlApplication;

    @Autowired
    EntityRepo entityRepo;


    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();
        SpringApplication.run(EntranceApplication.class, args);
        log.info("总耗时：" + (System.currentTimeMillis() - currentTimeMillis) / 1000 + "秒");
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.etlApplication.extract();
          /*  this.etl.getBaseData().forEach((a, b) -> {
                entityRepo.batchDelete(a, this.etl.getDataDate());
                entityRepo.batchInsert(b);
            });*/
            //this.etl
            log.info("ETL提取总耗时：" + (System.currentTimeMillis() - currentTimeMillis) / 1000 + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
