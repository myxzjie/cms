package com.xzjie.cms.dict.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.dict.model.KeyData;

public interface KeyDataService extends BaseService<KeyData> {

    KeyData getKeyDataByKey(String key);

    boolean existsByKey(String key);

}
