package net.riking.auto.commmon.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 */
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface FixedField {

    @AliasFor("end")
    int value() default 2147483647;

    int start() default 0;

    @AliasFor("value")
    int end() default 2147483647;

}