package com.xzjie.cms.core.service;


import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public abstract class AbstractService<T, O extends Serializable> implements BaseService<T, O> {

    protected abstract JpaRepository getRepository();

    @Override
    public boolean save(T obj) {
        getRepository().save(obj);
        return true;
    }


    @Override
    public boolean delete(Long id) {
        getRepository().deleteById(id);
        return true;
    }
}
