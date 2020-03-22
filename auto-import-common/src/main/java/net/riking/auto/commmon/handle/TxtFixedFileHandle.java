package net.riking.auto.commmon.handle;

import net.riking.auto.commmon.annotation.FixedField;
import net.riking.auto.commmon.enums.FileType;
import net.riking.auto.commmon.inject.FieldAnnotationMetadata;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class TxtFixedFileHandle extends TxtFileHandle {


    public TxtFixedFileHandle(FieldAnnotationMetadata fieldAnnotationMetadata) {
        super(fieldAnnotationMetadata);
    }

    @Override
    public boolean supports() {
        return super.supports() && sourceFile.type() == FileType.TXT_FIXED;
    }

    @Override
    String parseLine(String line, Field field, int i) {
        FixedField fixedField = field.getAnnotation(FixedField.class);
        return line.substring(fixedField.start(), fixedField.end());
    }
}
