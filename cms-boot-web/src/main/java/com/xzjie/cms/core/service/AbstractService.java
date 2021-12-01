package com.xzjie.cms.core.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<T extends Object, R extends JpaRepository<T, Long>> implements BaseService<T> {
    @Autowired
    protected R baseRepository;

    public R getRepository() {
        return baseRepository;
    }

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
