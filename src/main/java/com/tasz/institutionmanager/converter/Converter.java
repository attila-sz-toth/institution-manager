package com.tasz.institutionmanager.converter;

import java.util.List;
import java.util.stream.Collectors;

public interface Converter<F, T> {
    T convert(final F from);

    default List<T> convertAll(final List<F> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
