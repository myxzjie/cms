package com.xzjie.cms.dict.repository;

import com.xzjie.cms.core.repository.BaseRepository;
import com.xzjie.cms.dict.model.KeyData;

public interface KeyDataRepository extends BaseRepository<KeyData, Long> {

    KeyData findByKey(String key);

    boolean existsByKey(String key);
}
