package com.imitationsql.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.imitationsql.core.SqlBuilder;
import com.imitationsql.core.annotation.PrimaryKey;
import com.imitationsql.core.constants.BaseEntityConstant;
import com.imitationsql.core.expression.WhereExpression;
import com.imitationsql.core.util.StringUtil;
import com.imitationsql.core.util.TypeConvertUtil;
import com.imitationsql.web.domain.BaseEntity;
import com.imitationsql.web.domain.Page;
import com.imitationsql.web.domain.QueryPage;
import com.imitationsql.web.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.Field;
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
    public <T extends BaseEntity> Page<T> pageQuery(Class<T> entityClass, QueryPage queryPage) {
        SqlBuilder<T> sqlBuilder = new SqlBuilder<>(entityClass);
        Page<T> page = new Page<>();
        T queryEntity = BeanUtil.toBean(queryPage, entityClass);
        Field[] fields = ReflectUtil.getFields(entityClass);

        String countSql = sqlBuilder.count().where(o -> getWhereExpression(o, fields, queryEntity)).buildSql();
        Long count = jdbcTemplate.queryForObject(countSql, Long.class);
        page.setTotal(count);
        page.setCurrent(queryPage.getPageNum());
        page.setSize(queryPage.getSize());
        if (queryPage.getPageNum() > page.getPages()) {
            return page;
        }
        sqlBuilder.clear();
        String sql = sqlBuilder.select().where(o -> getWhereExpression(o, fields, queryEntity)).limit(o -> o.limit(queryPage.getOffset(), queryPage.getSize())).buildSql();
        List<T> records = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(entityClass));
        page.setRecords(records);
        return page;
    }

    private <T extends BaseEntity> WhereExpression<T> getWhereExpression(WhereExpression<T> whereExpression, Field[] fields, T entity) {
        whereExpression.and(BaseEntityConstant.DELETED_FIELD, BaseEntityConstant.NOT_DELETE);
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(entity);
                if (null != fieldValue && StrUtil.isNotBlank(fieldValue.toString())) {
                    whereExpression.and(StringUtil.wrapBlank(StrUtil.toUnderlineCase(field.getName())), TypeConvertUtil.simpleConvert(fieldValue));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return whereExpression;
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
