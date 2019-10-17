package com.xzjie.cms.core.service;

import java.io.Serializable;

public interface BaseService<T, O extends Serializable> {

    boolean save(T obj);

    boolean update(T obj);

    boolean delete(Long id);
}
