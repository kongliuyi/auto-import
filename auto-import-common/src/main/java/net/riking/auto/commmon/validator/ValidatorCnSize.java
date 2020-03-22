package net.riking.auto.commmon.validator;


import net.riking.auto.commmon.annotation.CnSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.UnsupportedEncodingException;

public class ValidatorCnSize implements ConstraintValidator<CnSize, String> {

    private int min;

    private int max;

    private String message;


    @Override
    public void initialize(CnSize constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return true;
        }
        boolean isValid = true;
        try {
            int size = new String(object.getBytes("GB2312"), "iso-8859-1").length();
            isValid = min <= size && size <= max;
            if (!isValid) {
                constraintContext.disableDefaultConstraintViolation();
                String message = this.message;
                message = "'" + object + "'长度为" + size + "," + message;
                constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return isValid;
    }

}