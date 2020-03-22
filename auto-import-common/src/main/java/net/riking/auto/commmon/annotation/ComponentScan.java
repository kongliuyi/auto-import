package net.riking.auto.commmon.annotation;

import net.riking.auto.commmon.config.FileScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Documented
@Import(FileScannerRegistrar.class)
public @interface ComponentScan {
    String[] value() default "";
}
