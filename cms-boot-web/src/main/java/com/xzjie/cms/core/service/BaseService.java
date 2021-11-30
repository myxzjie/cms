package com.xzjie.cms.core.service;

public interface BaseService<T> {

    boolean save(T obj);

    boolean update(T obj);

    boolean delete(Long id);
}
