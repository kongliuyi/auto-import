package net.riking.auto.commmon.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description
 * @Author kongLiuYi
 * @Date 2020/3/4 0004 12:00
 */
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface Ignore {
    boolean value() default true;
}
