package net.riking.auto.commmon.handle;

public interface Adapter<T> {

    boolean supports(T t);
}
