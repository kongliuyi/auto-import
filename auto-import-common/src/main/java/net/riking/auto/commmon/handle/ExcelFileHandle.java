package net.riking.auto.commmon.handle;

import lombok.extern.slf4j.Slf4j;
import net.riking.auto.commmon.inject.FieldAnnotationMetadata;
import net.riking.auto.commmon.job.EtlApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ExcelFileHandle extends FileHandle {


    public ExcelFileHandle(FieldAnnotationMetadata fieldAnnotationMetadata, EtlApplication etlApplication) {
        super(fieldAnnotationMetadata, etlApplication);
    }

    @Override
    public boolean supports() {
        return "xlsx".equalsIgnoreCase(this.fileTypes) || "xls".equalsIgnoreCase(this.fileTypes);
    }

    @Override
    public void parse(File file) throws IOException {


    }


    @Override
    public List<Object> handles() throws Throwable {

        return null;
    }




}
