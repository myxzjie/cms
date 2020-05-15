package com.xzjie.cms.service.impl;

import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.model.KeyData;
import com.xzjie.cms.repository.KeyDataRepository;
import com.xzjie.cms.service.KeyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class KeyDataServiceImpl extends AbstractService<KeyData, Long> implements KeyDataService {

    @Autowired
    private KeyDataRepository keyDataRepository;

    @Override
    protected JpaRepository getRepository() {
        return keyDataRepository;
    }

    @Override
    public boolean save(KeyData keyData) {
        if (existsByKey(keyData.getKey())) {
            update(keyData);
        } else {
            super.save(keyData);
        }

        return true;
    }

    @Override
    public boolean update(KeyData obj) {
        KeyData model = keyDataRepository.findByKey(obj.getKey());
        model.copy(obj);
        keyDataRepository.save(model);
        return true;
    }

    @Override
    public KeyData getKeyDataByKey(String key) {
        return keyDataRepository.findByKey(key);
    }

    @Override
    public boolean existsByKey(String key) {

        return keyDataRepository.existsByKey(key);
    }
}
