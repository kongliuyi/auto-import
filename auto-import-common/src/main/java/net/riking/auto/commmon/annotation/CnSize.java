package net.riking.auto.commmon.annotation;


import net.riking.auto.commmon.validator.ValidatorCnSize;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 中英文长度校验
 *
 */
@Target( { FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidatorCnSize.class)
@Documented
public @interface CnSize {

  String message() default "不在限制范围内";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  int min() default 0;

  int max() default 2147483647;

  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    CnSize[] value();
  }
 
}