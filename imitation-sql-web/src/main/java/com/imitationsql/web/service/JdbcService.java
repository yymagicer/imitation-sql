package com.imitationsql.web.service;

import com.imitationsql.web.domain.BaseEntity;
import com.imitationsql.web.domain.Page;
import com.imitationsql.web.domain.QueryPage;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 13:42
 */
public interface JdbcService {

    /**
     * 详情
     *
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */
    <T extends BaseEntity> T detail(Class<T> entityClass, Serializable id);

    /**
     * 查询列表
     *
     * @param entityClass
     * @param entity
     * @param <T>
     * @return
     */
    <T extends BaseEntity> List<T> list(Class<T> entityClass, T entity);

    /**
     * 分页查询
     *
     * @param entityClass
     * @param queryPage
     * @param <T>
     * @return
     */
    <T extends BaseEntity> Page<T> pageQuery(Class<T> entityClass, QueryPage queryPage);

    /**
     * 新增
     *
     * @param entity
     * @param <T>
     * @return
     */
    <T extends BaseEntity> T insert(T entity);

    /**
     * 更新
     *
     * @param entityClass
     * @param entity
     * @param <T>
     * @return
     */
    <T extends BaseEntity> boolean update(T entity);

    /**
     * 删除
     *
     * @param entityClass
     * @param id
     * @return
     */
    <T extends BaseEntity> boolean delete(Class<T> entityClass, Serializable id);

    /**
     * 批量删除
     *
     * @param entityClass
     * @param ids
     * @param <T>
     * @return
     */
    <T extends BaseEntity, I extends Serializable> boolean batchDelete(Class<T> entityClass, List<I> ids);

    /**
     * 物理删除
     *
     * @param entityClass
     * @param id
     * @return
     */
    <T extends BaseEntity> boolean physicalDelete(Class<T> entityClass, Serializable id);

    /**
     * 物理批量删除
     *
     * @param entityClass
     * @param ids
     * @param <T>
     * @return
     */
    <T extends BaseEntity, I extends Serializable> boolean physicalBatchDelete(Class<T> entityClass, List<I> ids);


}
