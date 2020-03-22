package net.riking.auto.commmon.annotation;


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
public @interface FieldFormat {

    String value() default "";

}