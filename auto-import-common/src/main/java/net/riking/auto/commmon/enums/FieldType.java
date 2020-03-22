package net.riking.auto.commmon.enums;

import lombok.Getter;
import net.riking.auto.commmon.annotation.FieldFormat;
import net.riking.auto.commmon.utils.EncodingUtil;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * 策略枚举
 *
 * @Description
 * @Author: kongLiuYi
 * @Date: 2020/3/22 0022 9:42
 */
@Getter
public enum FieldType {


    DATE(Date.class) {
        @Override
        public Object parseValue(Field field, String value) throws Exception {
            Object pojoValue;
            FieldFormat fieldFormat = field.getAnnotation(FieldFormat.class);
            try {
                String format = null == fieldFormat ? "yyyy-MM-dd" : fieldFormat.value();
                if ("".equalsIgnoreCase(value.trim())) {

                    pojoValue = DateUtils.parseDate("1970-01-01", "yyyy-MM-dd");
                } else {
                    pojoValue = DateUtils.parseDate(value, format);
                }
            } catch (Exception e) {
                throw new Exception("Date类型转换失败");
            }
            return pojoValue;
        }
    },
    STRING(String.class) {
        @Override
        public Object parseValue(Field field, String value) throws Exception {
            return EncodingUtil.toGb2312(value.trim());
        }
    },
    LONG(Long.class) {
        @Override
        public Object parseValue(Field field, String value) throws Exception {
            try {
                return Long.parseLong(value);
            } catch (Exception e) {
                throw new Exception("Long类型转换失败");
            }
        }
    },
    DOUBLE(Double.class) {
        @Override
        public Object parseValue(Field field, String value) throws Exception {
            Object pojoValue;
            FieldFormat fieldFormat = field.getAnnotation(FieldFormat.class);
            if ("".equalsIgnoreCase(value.trim())) {
                pojoValue = 0.00;
            } else {
                pojoValue = Double.parseDouble(value);
                if (null != fieldFormat && !"".equals(fieldFormat)) {
                    try {
                        DecimalFormat format = new DecimalFormat(fieldFormat.value());
                        pojoValue = format.format(value);
                    } catch (Exception e) {
                        throw new Exception("Double类型格式化失败");
                    }
                }
            }
            return pojoValue;
        }
    },
    BIGDECIMAL(BigDecimal.class) {
        @Override
        public Object parseValue(Field field, String value) throws Exception {
            Object pojoValue;
            FieldFormat fieldFormat = field.getAnnotation(FieldFormat.class);
            try {
                return new BigDecimal(value);
            } catch (Exception e) {
                throw new Exception("BigDecimal类型转换失败");
            }
        }
    };


    private final Class<?> type;


    FieldType(Class<?> type) {
        this.type = type;
    }

    public abstract Object parseValue(Field field, String value) throws Exception;

    /**
     * 根据类型的名称，返回类型的枚举实例。
     *
     * @param type 类型名称
     */
    public static FieldType fromTypeName(Class<?> type) {
        for (FieldType fieldType : FieldType.values()) {
            if (fieldType.type.equals(type)) {
                return fieldType;
            }
        }
        return null;
    }

}
