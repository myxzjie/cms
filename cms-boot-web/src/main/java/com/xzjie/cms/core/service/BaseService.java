package com.xzjie.cms.core.service;

import java.util.List;

public interface BaseService<T> {

    <S extends T> S save(S entity);

    boolean update(T entity);

    boolean delete(Long id);

    List<T> getAll();

    T getOne(Long id);
}
