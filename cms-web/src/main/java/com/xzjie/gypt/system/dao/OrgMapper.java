package com.xzjie.gypt.system.dao;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.system.model.Org;

public interface OrgMapper extends BaseMapper<Org, Long>{
    /*int deleteByPrimaryKey(Long orgId);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);*/
	
	boolean checkClientSecret(String clientSecret);
	
	boolean checkOrgId(Long orgId);
}