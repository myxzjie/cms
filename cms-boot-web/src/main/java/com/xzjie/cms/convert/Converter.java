package com.xzjie.cms.convert;

import java.util.List;

public interface Converter<S, T> {
    String componentModel = "spring";

    T target(S source);

    S source(T target);

    List<T> target(List<S> source);

    List<S> source(List<T> target);
}
