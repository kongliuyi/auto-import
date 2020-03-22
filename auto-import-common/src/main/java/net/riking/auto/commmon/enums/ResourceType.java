package net.riking.auto.commmon.enums;

import lombok.Getter;

/**
 * @Description 通过下列类型方式截取数据
 * @Author: kongLiuYi
 * @Date: 2020/3/22 0022 9:28
 */
@Getter
public enum ResourceType {
    JAR("jar"),
    FILE("file"),

    CLASS_FILE(".class");

    private String typeString;

    ResourceType(String type) {
        this.typeString = type;
    }

}
