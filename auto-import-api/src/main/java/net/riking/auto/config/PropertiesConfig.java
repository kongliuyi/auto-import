package net.riking.auto.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.jpa")
public class PropertiesConfig {

    private Map<String, Object> jpaProperties = new HashMap<>();

    private String databasePlatform;

    private String ddlAuto;

    private boolean showSql;


    public Map<String, Object> getJpaProperties() {
        jpaProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
        jpaProperties.put("hibernate.dialect", databasePlatform);
        jpaProperties.put("hibernate.show_sql", showSql);
        jpaProperties.put("hibernate.physical_naming_strategy", "net.riking.auto.commmon.strategy.TableNameStrategy");
        // jpaProperties.put("hibernate.implicit_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        return jpaProperties;
    }
}
