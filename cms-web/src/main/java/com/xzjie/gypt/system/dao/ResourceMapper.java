package com.xzjie.gypt.system.dao;

import java.util.List;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.system.model.Resource;

public interface ResourceMapper extends BaseMapper<Resource, Long>{
   /* int deleteByPrimaryKey(Long resourceId);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long resourceId);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);*/
    
    List<Resource> findResourceTree(Long resourceId);
    
    /**
     *  tree
     * @param userId
     * @return
     */
    List<Resource> findResourceUser(Long userId);
    
    /**
     * item
     * @param userId
     * @return
     */
    List<Resource> findResourceUserByUserId(Long userId);
}