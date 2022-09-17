package com.xzjie.cms.core.service;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService<T extends Object, R extends JpaRepository<T, Long>> implements BaseService<T> {
    @Getter
    @Autowired
    protected R baseRepository;

    @Override
    public <S extends T> S save(S entity) {
        return baseRepository.save(entity);
    }

//    @Override
//    public boolean update(T entity) {
//        getOne(entity)
//        return false;
//    }

    @Override
    public boolean delete(Long id) {
        baseRepository.deleteById(id);
        return true;
    }

    @Override
    public List<T> getAll() {
        return baseRepository.findAll();
    }

    @Override
    public T getOne(Long id) {
        return baseRepository.findById(id).orElseGet(null);
    }



}
