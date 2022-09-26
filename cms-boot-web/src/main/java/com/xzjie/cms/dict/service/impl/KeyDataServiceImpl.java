package com.xzjie.cms.dict.service.impl;

import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.dict.repository.KeyDataRepository;
import com.xzjie.cms.dict.service.KeyDataService;
import com.xzjie.cms.dict.model.KeyData;
import org.springframework.stereotype.Service;

@Service
public class KeyDataServiceImpl extends AbstractService<KeyData, KeyDataRepository> implements KeyDataService {

    @Override
    public KeyData save(KeyData keyData) {
        if (existsByKey(keyData.getKey())) {
            update(keyData);
            return keyData;
        } else {
           return super.save(keyData);
        }
    }

//    @Override
//    public boolean update(KeyData obj) {
//        KeyData model = baseRepository.findByKey(obj.getKey());
//        model.copy(obj);
//        baseRepository.save(model);
//        return true;
//    }

    @Override
    public KeyData getKeyDataByKey(String key) {
        return baseRepository.findByKey(key);
    }

    @Override
    public boolean existsByKey(String key) {

        return baseRepository.existsByKey(key);
    }
}
