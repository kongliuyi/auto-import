package net.riking.auto.commmon.handle;


import java.io.IOException;

public interface Parse<T> {

    void parse(T t) throws IOException;
}
