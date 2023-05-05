package com.xzjie.cms.core.service;


import com.xzjie.cms.core.ObjectConvertUtils;
import com.xzjie.cms.core.repository.BaseRepository;
import com.xzjie.cms.core.entity.BaseEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractService<T extends BaseEntity<T>, R extends BaseRepository<T, Long>> implements BaseService<T> {
    @Getter
    @Autowired
    protected R baseRepository;

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public <S extends T> S save(S entity) {
        return baseRepository.save(entity);
    }

    @Override
    public <S extends T> S update(S entity) {
        T model = this.getById(entity.getId());
        ObjectConvertUtils.copy(entity, model);
        return (S) baseRepository.save(model);
    }

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
    public T getById(Long id) {
        return baseRepository.findById(id).orElseThrow();
    }


//    public <R> Page<R> getPage(Class<R> tClass, String sqlOrSqlId, Map<String, Object> params, Pageable pageable) {
//        Page<R> pageModel = null;
//        Page<Map<String, Object>> pagedListMap = baseRepository.findPageMap(pageable);
//        List<R> resultList = Convert.toList(tClass, pagedListMap.getContent());
////        this.mergeLostFields(resultList, pagedListMap.getContent());
//        if (Validator.isNotNull(pageable) && pageable.getPageNumber() > 0) {
//            pageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
//        }
//        pageModel = new PageImpl<>(resultList, pageable, pagedListMap.getTotalElements());
//        return pageModel;
//    }

    /**
     * 合并丢失的字段，有一些字段在查询Map的时候自动转换为小驼峰，但是实体还是下划线分割这种的
     * @param resultEntity
     * @param resultMap
     */
//    private <R> void mergeLostFields(R resultEntity, Map<String, Object> resultMap){
//        if(Validator.isNotNull(resultEntity) && Validator.isNotNull(resultMap)){
//            /**
//             * 获取所有字段看是否有属性包含 "_"
//             */
//            IEnumerable<Field> fields = Linq.asEnumerable(ReflectUtil.getFields(resultEntity.getClass()));
//            if(fields.any(a -> a.getName().contains(FIELD_UNDERSCORE_NAME))){
//                List<Field> supFields = fields.where(a -> a.getName().contains(FIELD_UNDERSCORE_NAME)).toList();
//                supFields.forEach(f -> {
//                    String fieldKey = UnderlineToCamelUtil.underlineToCamel(f.getName());
//                    ReflectUtil.setFieldValue(resultEntity, f.getName(), resultMap.get(fieldKey));
//                });
//            }
//        }
//    }


    /**
     * 合并丢失的字段，有一些字段在查询Map的时候自动转换为小驼峰，但是实体还是下划线分割这种的
     * @param resultList
     * @param resultListMap
     */
//    private <R> void mergeLostFields(List<R> resultList, List<Map<String, Object>> resultListMap) {
//        if(resultList.size() == resultListMap.size()){
//            IEnumerable<R> linqList = Linq.asEnumerable(resultList);
//            if(linqList.any() && Validator.isNotNull(linqList.first())){
//                R firstEntity = linqList.first();
//                IEnumerable<Field> fields = Linq.asEnumerable(ReflectUtil.getFields(firstEntity.getClass()));
//                if(fields.any(a -> a.getName().contains(FIELD_UNDERSCORE_NAME))){
//                    List<Field> supFields = fields.where(a -> a.getName().contains(FIELD_UNDERSCORE_NAME)).toList();
//                    /**
//                     * 遍历转换后的结果集
//                     */
//                    for (int i = 0; i < resultList.size(); i++) {
//                        R tEntity = resultList.get(i);
//                        Map<String, Object> rMap = resultListMap.get(i);
//                        supFields.forEach(f -> {
//                            String fieldKey = UnderlineToCamelUtil.underlineToCamel(f.getName());
//                            ReflectUtil.setFieldValue(tEntity, f.getName(), rMap.get(fieldKey));
//                        });
//                    }
//                }
//            }
//        }
//    }


}
