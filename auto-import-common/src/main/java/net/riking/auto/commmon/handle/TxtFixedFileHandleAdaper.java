package net.riking.auto.commmon.handle;

import net.riking.auto.commmon.annotation.FixedField;
import net.riking.auto.commmon.enums.FileType;
import net.riking.auto.commmon.inject.FieldAnnotationMetadata;

import net.riking.auto.commmon.job.EtlApplication;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class TxtFixedFileHandleAdaper extends TxtFileHandleAdaper {

    public TxtFixedFileHandleAdaper(FieldAnnotationMetadata fieldAnnotationMetadata, EtlApplication etlApplication) {
        super(fieldAnnotationMetadata, etlApplication);
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
