package com.xzjie.cms.core.service;

import java.util.List;

public interface BaseService<T> {

    <S extends T> S save(S entity);

    <S extends T> S update(S entity);

    boolean delete(Long id);

    List<T> getAll();

    T getById(Long id);
}
