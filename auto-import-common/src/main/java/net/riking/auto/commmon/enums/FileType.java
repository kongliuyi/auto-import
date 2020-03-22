package net.riking.auto.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description 通过下列类型方式截取数据
 * @Author: kongLiuYi
 * @Date: 2020/3/22 0022 9:28
 */
@Getter
@AllArgsConstructor
public enum FileType {

    TXT_FIXED(1, "文本固定值"),
    TXT_DELIMITER(2, "文本分隔符"),
    EXCEL(3,"Excel类型");

    private int code;
    private String description;



    }
