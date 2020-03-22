package net.riking.auto.commmon.handle;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import net.riking.auto.commmon.annotation.FieldFormat;
import net.riking.auto.commmon.annotation.SourceFile;
import net.riking.auto.commmon.enums.FieldType;
import net.riking.auto.commmon.inject.FieldAnnotationMetadata;
import net.riking.auto.commmon.inject.InjectionMetadata;
import net.riking.auto.commmon.utils.EncodingUtil;
import net.riking.auto.commmon.validator.ValidationResult;
import net.riking.auto.commmon.validator.ValidationUtils;


import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
public abstract class FileHandle<T> implements Handle<T>, Parse<File>, Adapter<File> {

    @Getter
    Map<Class, List<Object>> baseData = new HashMap<>();

    List<String> data = new ArrayList<>();

    InjectionMetadata metadata;

    SourceFile sourceFile;

    String fileTypes;

    FieldAnnotationMetadata fieldAnnotationMetadata;


    public FileHandle(FieldAnnotationMetadata fieldAnnotationMetadata) {
        this.fieldAnnotationMetadata = fieldAnnotationMetadata;
    }

    /**
     * @param file
     * @return
     */
    @Override
    public boolean supports(File file) {
        String fileName = file.getName();
        this.fileTypes = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        metadata = fieldAnnotationMetadata.getMetadata(fileName);
        sourceFile = metadata.getSourceFile();
        return supports();
    }


    /**
     * 支持文件类型
     *
     * @return
     */
    abstract boolean supports();


    Object parseValue(Field field, String value) throws Exception {
        return FieldType.fromTypeName(field.getType()).parseValue(field, value);
    }


    void validationResult(Object target) {
        ValidationResult result = ValidationUtils.validateEntity(target);
        if (result.isHasErrors()) {
            log.error(result.getErrorsMsg());
        }
    }
}
