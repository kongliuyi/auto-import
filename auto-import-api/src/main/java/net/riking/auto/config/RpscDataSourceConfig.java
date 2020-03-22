package net.riking.auto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "net.riking.auto.repository.rpsc",
        entityManagerFactoryRef = "rpscEntityManagerFactory",
        transactionManagerRef = "rpscTransactionManager"
)
@EnableTransactionManagement
@EnableConfigurationProperties(PropertiesConfig.class)
public class RpscDataSourceConfig {


    @Autowired
    private PropertiesConfig propertiesConfig;


    @Bean("rpscDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.rpsc")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "rpscEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }


    @Bean(name = "rpscEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource())
                .properties(propertiesConfig.getJpaProperties())
                //设置实体类所在位置
                .packages("net.riking.auto.pojo.rpsc")
                .persistenceUnit("rpscPersistenceUnit")
                .build();
    }

    @Bean(name = "rpscTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }
}
