package net.riking.auto.commmon.inject;


import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

import static org.springframework.util.Assert.notNull;

public class FieldFactoryBean implements FactoryBean , InitializingBean {

    @Setter
    private List<Class>  classes;

    private  FieldFactory fieldFactory;



    @Override
    public Object getObject() throws Exception {
        if(this.fieldFactory == null)afterPropertiesSet();
        return this.fieldFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return FieldFactory.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        notNull(classes, "classes 'classes' is required");


        this.fieldFactory = buildFieldFactory();
    }

    protected FieldFactory buildFieldFactory()  {
        FieldFactory fieldFactory = new FieldFactory();
        FieldAnnotationScan fieldAnnotationScan= new FieldAnnotationScan();
        for (Class aClass : this.classes) {
            InjectionMetadata metadata = fieldAnnotationScan.buildAutowiringMetadata(aClass);
            fieldFactory.registerInjectionMetadata(metadata);
        }
        return fieldFactory;
    }
}
