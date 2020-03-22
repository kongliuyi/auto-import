package net.riking.auto.commmon.inject;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import net.riking.auto.commmon.annotation.ExcelField;
import net.riking.auto.commmon.annotation.FixedField;
import net.riking.auto.commmon.annotation.Ignore;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.ReflectionUtils;

import javax.persistence.Column;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Modifier;
import java.util.*;

@Slf4j
public class FieldAnnotationMetadata implements InitializingBean {

    @Setter
    private List<Class> classes;


    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(4);


    private final Map<String, InjectionMetadata> fieldMetadataMap = new HashMap<>();


    private void registerInjectionMetadata(InjectionMetadata metadata) {
        fieldMetadataMap.put(metadata.getSourceFile().value(), metadata);
    }


    public InjectionMetadata getMetadata(String fileName) {
        if (fieldMetadataMap.containsKey(fileName)) {
            return fieldMetadataMap.get(fileName);
        }
        int index = fileName.lastIndexOf("_");
        if (index == -1) {
            return null;
        }
        return getMetadata(fileName.substring(0, index));
    }

    public FieldAnnotationMetadata() {
        //将处理@Field注解
        this.autowiredAnnotationTypes.add(FixedField.class);
        this.autowiredAnnotationTypes.add(ExcelField.class);
        this.autowiredAnnotationTypes.add(Column.class);
        this.autowiredAnnotationTypes.add(Ignore.class);
/*        try {
            //后置处理器将处理javax.inject.Inject JSR-330注解
            this.autowiredAnnotationTypes.add((Class<? extends Annotation>)
                    ClassUtils.forName("javax.inject.Inject", AutowiredAnnotationBeanPostProcessor.class.getClassLoader()));
            log.trace("JSR-330 'javax.inject.Inject' annotation found and supported for autowiring");
        }
        catch (ClassNotFoundException ex) {
            // JSR-330 API not available - simply skip.
        }*/
    }


    private InjectionMetadata buildAutowiringMetadata(final Class<?> clazz) {
        List<InjectedElement> elements = new ArrayList<>();
        Class<?> targetClass = clazz;
        do {
            final List<InjectedElement> currElements = new ArrayList<>();
            ReflectionUtils.doWithLocalFields(targetClass, field -> {
                // 获取字段属性含有 Autowired,（Value,JSR-330这两个不确定，标记一下）注解的字段
                AnnotationAttributes ann = findAutowiredAnnotation(field);
                // 如果该字段是 static 修饰 或者是 ID 的直接过滤跳过
                if (Modifier.isStatic(field.getModifiers()) || field.getName().equals("id")) {
                    if (log.isInfoEnabled()) {
                        log.info("Autowired annotation is not supported on static fields or id: " + field);
                    }
                    return;
                }
                if (ann.size() != 0) {
                    AnnotationAttributes attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(field, Ignore.class);
                    if (attributes != null && attributes.getBoolean("value")) {
                        if (log.isInfoEnabled()) {
                            log.info("Autowired annotation is not supported on  @Ignore(true): " + field);
                        }
                        return;
                    }
                }
                // 将 field 封装 进AutowiredFieldElement 类中，并放入进currElements集合中
                currElements.add(new InjectedElement(field, ann));
            });
            elements.addAll(0, currElements);
            targetClass = targetClass.getSuperclass();
        }
        while (targetClass != null && targetClass != Object.class);

        // 匿名内部类形式创建
/*        SourceFile ann = clazz.getAnnotation(SourceFile.class);
        if(ann.type() != FileType.TXT_FIXED){
            Comparator<InjectedElement> comElements = (o1, o2) ->
                    (Integer) ((Integer) o1.getAnn().get("order"))
                            .compareTo((Integer) o2.getAnn().get("order"));
            Collections.sort(elements,comElements);
        }*/
        //elements = dealElements(clazz,elements);
        //将 elements  封装 进 InjectionMetadata类中
        return new InjectionMetadata(clazz, elements);
    }

    private List<InjectedElement> dealElements(List<InjectedElement> elements) {
       /* Map<Integer,InjectedElement> elementMap = new HashMap<>();
        SourceFile ann = clazz.getAnnotation(SourceFile.class);
        if(ann.type() == FileType.TXT_DELIMITER ){
            for (InjectedElement element : elements) {
                Integer orderBy = (Integer) element.getAnn().get("order");
                elementMap.put(orderBy,element);
            }
        }
        if(ann.type() == FileType.TXT_FIXED){
            Set<Integer> integers = elementMap.keySet();
            int  start ;
            int  end = -1;
            for (Integer integer : integers) {
                InjectedElement element = elementMap.get(integer);
                AnnotationAttributes anno =  element.getAnn();
                start = (int) anno.get("start");
                if(start == 0 && end != -1){
                    start =  end+1;
                }
                anno.put("start", start);
                end = (int) anno.get("end");
            }
        }*/
        return elements;
    }


    /**
     * 获取 含有注解的字段或方法元素
     *
     * @param ao :
     * @return : 返回所有注解不同属性名的值
     */
    private AnnotationAttributes findAutowiredAnnotation(AccessibleObject ao) {
        AnnotationAttributes attributes = new AnnotationAttributes();
        if (ao.getAnnotations().length > 0) {  // autowiring annotations have to be local
            // autowiredAnnotationTypes 在初始化中初始化，可在构造函数中查看
            for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
                AnnotationAttributes attribute = AnnotatedElementUtils.getMergedAnnotationAttributes(ao, type);
                if (attribute != null) {
                    attributes.putAll(attribute);
                }
            }
        }
        return attributes;
    }

    @Override
    public void afterPropertiesSet() {
        buildFieldMetadataMap();
    }

    private void buildFieldMetadataMap() {
        for (Class aClass : this.classes) {
            InjectionMetadata metadata = buildAutowiringMetadata(aClass);
            registerInjectionMetadata(metadata);
        }

    }
}
