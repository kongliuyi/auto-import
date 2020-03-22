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
public @interface Order {

    @AliasFor("order")
    int value() default 0;

    @AliasFor("value")
    int order() default 0;

}