package net.riking.auto.commmon.handle;

import net.riking.auto.commmon.enums.FileType;
import net.riking.auto.commmon.inject.FieldAnnotationMetadata;

import net.riking.auto.commmon.job.EtlApplication;
import org.springframework.stereotype.Component;

@Component
public class TxtDelimiterFileHandleAdaper extends TxtFileHandleAdaper {


    public TxtDelimiterFileHandleAdaper(FieldAnnotationMetadata fieldAnnotationMetadata, EtlApplication etlApplication) {
        super(fieldAnnotationMetadata, etlApplication);
    }

    @Override
    public boolean supports() {
        return super.supports() && sourceFile.type() == FileType.TXT_DELIMITER;
    }


}
