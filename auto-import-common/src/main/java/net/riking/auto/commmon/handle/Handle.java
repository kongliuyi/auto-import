package net.riking.auto.commmon.handle;

import java.util.List;

public interface Handle<T> {

    List<Object> handles(T t) throws Throwable;

    boolean supports(T t);
}
