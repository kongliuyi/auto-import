package net.riking.auto.commmon.handle;

import net.riking.auto.commmon.inject.InjectionMetadata;


import java.util.List;

public interface HandleAdapter<T> {
    Object handles(String line, InjectionMetadata metadata) throws Throwable;

    List<Object> handles(T t) throws Throwable;

    boolean supports(T t);
}
