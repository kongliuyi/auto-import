package net.riking.auto.commmon.handle;

import net.riking.auto.commmon.enums.FileType;
import net.riking.auto.commmon.inject.FieldAnnotationMetadata;

import org.springframework.stereotype.Component;

@Component
public class TxtDelimiterFileHandle extends TxtFileHandle {


    public TxtDelimiterFileHandle(FieldAnnotationMetadata fieldAnnotationMetadata) {
        super(fieldAnnotationMetadata);
    }

    @Override
    public boolean supports() {
        return super.supports() && sourceFile.type() == FileType.TXT_DELIMITER;
    }


}
