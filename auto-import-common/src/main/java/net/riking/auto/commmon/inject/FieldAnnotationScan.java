package net.riking.auto.commmon.inject;

import lombok.extern.slf4j.Slf4j;

import net.riking.auto.commmon.annotation.FieldFormat;
import net.riking.auto.commmon.annotation.Order;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Modifier;
import java.util.*;

@Slf4j
public class FieldAnnotationScan {

    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(4);


    public FieldAnnotationScan(){
        //后置处理器将处理@Autowire注解
        this.autowiredAnnotationTypes.add(Order.class);
        this.autowiredAnnotationTypes.add(FieldFormat.class);
        try {
            //后置处理器将处理javax.inject.Inject JSR-330注解
            this.autowiredAnnotationTypes.add((Class<? extends Annotation>)
                    ClassUtils.forName("javax.inject.Inject", AutowiredAnnotationBeanPostProcessor.class.getClassLoader()));
            log.trace("JSR-330 'javax.inject.Inject' annotation found and supported for autowiring");
        }
        catch (ClassNotFoundException ex) {
            // JSR-330 API not available - simply skip.
        }
    }


    public  InjectionMetadata buildAutowiringMetadata(final Class<?> clazz) {
        List<InjectedElement> elements = new ArrayList<>();
        Class<?> targetClass = clazz;
        do {
            final Map<Integer,InjectedElement> currElementsMap = new HashMap<>();
            ReflectionUtils.doWithLocalFields(targetClass, field -> {
                //获取字段属性含有 Autowired,（Value,JSR-330这两个不确定，标记一下）注解的字段
                AnnotationAttributes ann = findAutowiredAnnotation(field);
                if (ann != null) {
                    //如果该字段是static修饰的直接过滤跳过
                    if (Modifier.isStatic(field.getModifiers())) {
                        if (log.isInfoEnabled()) {
                            log.info("Autowired annotation is not supported on static fields: " + field);
                        }
                        return;
                    }
                    if(ann.annotationType()== Order.class){
                        Object order = ann.get("order");
                    //    currElementsMap.put((Integer)order,new  InjectedElement(field));
                    }
                    //将 field 封装 进AutowiredFieldElement 类中，并放入进currElements集合中
                }
            });
            elements.addAll(0, currElementsMap.values());
            targetClass = targetClass.getSuperclass();
        }
        while (targetClass != null && targetClass != Object.class);
        //将 elements  封装 进 InjectionMetadata类中
        return new InjectionMetadata(clazz, elements);
    }

    // 获取 含有注解的字段或方法元素
    @Nullable
    private AnnotationAttributes findAutowiredAnnotation(AccessibleObject ao) {
        if (ao.getAnnotations().length > 0) {
            //autowiredAnnotationTypes 在初始化中初始化，可在构造函数中查看
            for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
                AnnotationAttributes attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(ao, type);
                if (attributes != null) {
                    return attributes;
                }
            }
        }
        return null;
    }
}
