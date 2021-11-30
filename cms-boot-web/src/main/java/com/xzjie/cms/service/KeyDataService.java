package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.KeyData;

public interface KeyDataService extends BaseService<KeyData> {

    KeyData getKeyDataByKey(String key);

    boolean existsByKey(String key);

}
