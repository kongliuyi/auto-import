package net.riking.auto.commmon.handle;

import net.riking.auto.commmon.inject.InjectionMetadata;


import java.util.List;

public interface Handle<T> {
    Object handles(T line, InjectionMetadata metadata) throws Throwable;

    List<Object> handles() throws Throwable;
}
