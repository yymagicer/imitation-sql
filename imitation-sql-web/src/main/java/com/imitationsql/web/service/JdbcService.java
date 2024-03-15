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
     * @param entity
     * @param <T>
     * @return
     */
    <T extends BaseEntity> List<T> list(T entity);

    /**
     * 分页查询
     *
     * @param queryPage
     * @param <T>
     * @return
     */
    <T extends BaseEntity> Page<T> pageQuery(QueryPage queryPage);

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
     * @param entity
     * @param id
     * @param <T>
     * @return
     */
    <T extends BaseEntity> T update(T entity, Serializable id);

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

}
