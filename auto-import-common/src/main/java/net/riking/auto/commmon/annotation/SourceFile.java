package net.riking.auto.commmon.annotation;


import net.riking.auto.commmon.enums.FileType;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 */
@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Documented
public @interface SourceFile {


    String value();

    FileType type() default FileType.TXT_FIXED;

    String delimiter() default "";

    int head() default 1;

}