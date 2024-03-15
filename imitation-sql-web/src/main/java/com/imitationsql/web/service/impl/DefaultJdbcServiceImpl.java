package com.imitationsql.web.service.impl;

import com.imitationsql.core.SqlBuilder;
import com.imitationsql.core.annotation.PrimaryKey;
import com.imitationsql.web.domain.BaseEntity;
import com.imitationsql.web.domain.Page;
import com.imitationsql.web.domain.QueryPage;
import com.imitationsql.web.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 13:48
 */
@Service
public class DefaultJdbcServiceImpl implements JdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public <T extends BaseEntity> T detail(Class<T> entityClass, Serializable id) {
        SqlBuilder<T> sqlBuilder = new SqlBuilder<>(entityClass);
        String sql = sqlBuilder.select().where(o -> o.and(getPrimaryKeyColumnName(entityClass), id)).buildSql();
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(entityClass));
    }

    /**
     * 获取主键列名
     *
     * @param entityClass
     * @param <T>
     * @return
     */
    private <T> String getPrimaryKeyColumnName(Class<T> entityClass) {
        PrimaryKey annotation = entityClass.getAnnotation(PrimaryKey.class);
        if (null == annotation) {
            return "id";
        } else {
            return annotation.value();
        }
    }

    @Override
    public <T extends BaseEntity> List<T> list(T entity) {
        return null;
    }

    @Override
    public <T extends BaseEntity> Page<T> pageQuery(QueryPage queryPage) {
        return null;
    }

    @Override
    public <T extends BaseEntity> T insert(T entity) {
        return null;
    }

    @Override
    public <T extends BaseEntity> T update(T entity, Serializable id) {
        return null;
    }

    @Override
    public <T extends BaseEntity> boolean delete(Class<T> entityClass, Serializable id) {
        return false;
    }

    @Override
    public <T extends BaseEntity, I extends Serializable> boolean batchDelete(Class<T> entityClass, List<I> ids) {
        return false;
    }
}
