package net.riking.auto.commmon.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author kongLiuYi
 * @Date 2020/3/4 0004 14:11
 */
@Component
public class TableNameStrategy extends SpringPhysicalNamingStrategy {
    /**
     * 自定义表名转换
     *
     * @param name
     * @param jdbcEnvironment
     * @return
     */
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String tableName = name.getText();
        return Identifier.toIdentifier(tableName);
    }


    /**
     * 自定义列转换
     * @param identifier
     * @param jdbcEnvironment
     * @return
     */
/*    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return Identifier.toIdentifier(identifier.getText());
    }*/


}
