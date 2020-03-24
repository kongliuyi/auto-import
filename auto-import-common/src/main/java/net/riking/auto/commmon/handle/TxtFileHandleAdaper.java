package net.riking.auto.commmon.handle;

import lombok.extern.slf4j.Slf4j;
import net.riking.auto.commmon.annotation.Order;
import net.riking.auto.commmon.inject.FieldAnnotationMetadata;
import net.riking.auto.commmon.inject.InjectedElement;
import net.riking.auto.commmon.inject.InjectionMetadata;

import net.riking.auto.commmon.job.EtlApplication;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TxtFileHandleAdaper extends FileHandleAdaper {


    public TxtFileHandleAdaper(FieldAnnotationMetadata fieldAnnotationMetadata, EtlApplication etlApplication) {
        super(fieldAnnotationMetadata, etlApplication);
    }

    @Override
    public boolean supports() {
        return "txt".equalsIgnoreCase(this.fileTypes);
    }

    @Override
    public void parse(File file) throws IOException {

        try {
            data = FileUtils.readLines(file, "Utf-8");
            if (metadata == null || data.isEmpty()) {
                log.warn("文件【" + file.getName() + "】无匹配的实体类或该文件为空，文件导入失败");
            }
        } catch (IOException e) {
            log.error("文件解析错误,请核查...");
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public List<Object> handles() throws Throwable {
        Object target;
        String value = "";
        List<Object> resultList = new ArrayList<>();
        int j = 0;
        List<InjectedElement> elements = metadata.getInjectedElements();
        for (int i = this.metadata.getSourceFile().head() - 1; i < this.data.size(); i++) {
            String line = this.data.get(i);
            try {
                target = handles(line, metadata);
                resultList.add(target);
            } catch (Throwable e) {
                log.error("实体类" + metadata.getTargetClass() + "总长度" + elements.size() + ":数据文件第" + i + 1 + "行" + j + 1 + "列值【" + value + "】出错.", e);
                log.error("数据行值：" + line);
                throw e;
            }
        }
        this.etlApplication.getBaseData().put(metadata.getTargetClass(), resultList);
        return null;
    }


    @Override
    public Object handles(String line, InjectionMetadata metadata) throws Throwable {
        List<InjectedElement> elements = metadata.getInjectedElements();
        line = new String(line.getBytes("gbk"), "ISO8859-1");
        Object target = metadata.getTargetClass().newInstance();
        for (int j = 0; j < elements.size(); j++) {
            InjectedElement element = elements.get(j);
            Field field = (Field) element.getMember();
            String value = parseLine(line, field, j);
            // 赋值
            element.inject(target, parseValue(field, value));
        }
        // 校验对象
        validationResult(target);
        // 赋值默认值
        defaultValue(target);
        return target;
    }


    /**
     * 默认采用方式为分割符
     *
     * @param line 数据行
     * @param field 字段属性
     * @param j  数组下表
     * @return 返回指定的数据值
     * @throws Exception
     */
    String parseLine(String line, Field field, int j) throws Exception {
        while (line.contains(sourceFile.delimiter() + sourceFile.delimiter())) {
            line = line.replace(sourceFile.delimiter() + sourceFile.delimiter(), sourceFile.delimiter() + " " + sourceFile.delimiter());
        }
        String[] split = line.split(String.format("\\%s", sourceFile.delimiter()));
        Order order = field.getAnnotation(Order.class);
        if (order != null) {
            j = order.value() - 1;
        }
        if (split.length <= j) {
            throw new Exception("数组越界异常:数组总长度" + split.length);
        }
        return split[j];

    }

    void defaultValue(Object target) throws Exception {
        Field dataDate = target.getClass().getDeclaredField("dataDate");
        dataDate.setAccessible(true);
        // dataDate.set(target, this.fileParse.getDate());
    }


}
