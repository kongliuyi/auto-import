package net.riking.auto.commmon.config;

import net.riking.auto.commmon.annotation.ComponentScan;
import net.riking.auto.commmon.annotation.SourceFile;
import net.riking.auto.commmon.inject.FieldAnnotationMetadata;
import net.riking.auto.commmon.scan.PkgScanner;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.io.IOException;
import java.util.List;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

public class FileScannerRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(annotationMetadata.getAnnotationAttributes(ComponentScan.class.getName()));
        assert mapperScanAttrs != null;
        String[] values = (String[]) mapperScanAttrs.get("value");
        PkgScanner scanner = new PkgScanner(values, SourceFile.class);
        try {

            List<Class> classes = scanner.scan();
            BeanDefinitionBuilder builder = genericBeanDefinition(FieldAnnotationMetadata.class);
            GenericBeanDefinition bd = (GenericBeanDefinition) builder.getBeanDefinition();
            MutablePropertyValues mpv = new MutablePropertyValues();
            // 类似byType或者byName注入，需要set方法
            mpv.add("classes", classes);
            bd.setPropertyValues(mpv);
            beanDefinitionRegistry.registerBeanDefinition("fieldAnnotationMetadata", bd);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
