package net.riking.auto.commmon.inject;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FieldFactory {

    private  final  Map<String,InjectionMetadata> fieldMetadataMap=new HashMap<>();


    protected void registerInjectionMetadata(InjectionMetadata metadata){
        fieldMetadataMap.put("1", metadata);
    }

    public  InjectionMetadata getMetadata(String file){
        InjectionMetadata metadata = fieldMetadataMap.get(file);
        if(metadata==null) log.error("------");

        return metadata;
    }



}
