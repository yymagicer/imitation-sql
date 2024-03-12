package com.imitationsql.expression;

import cn.hutool.core.util.StrUtil;
import com.imitationsql.annotation.TableName;
import com.imitationsql.util.TypeConvertUtil;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 13:50
 */
@Setter
@Getter
public abstract class AbstractSqlExpression<T> implements SqlExpression<T> {

    /**
     * 实体类对象
     */
    protected Object entity;

    /**
     * 是否批量新增
     */
    protected boolean batch = false;


    /**
     * 获取表名
     *
     * @return
     */
    protected String getTableName() {
        return getTableName(getEntityClass());
    }

    /**
     * 获取表名
     *
     * @param entityClass
     * @return
     */
    protected String getTableName(Class<?> entityClass) {
        TableName annotation = getEntityClass().getAnnotation(TableName.class);
        String tableName;
        if (null == annotation) {
            tableName = StrUtil.toUnderlineCase(entityClass.getSimpleName());
        } else {
            tableName = annotation.value();
        }
        return tableName;
    }

    /**
     * 获取对象class
     *
     * @return
     */
    protected Class<?> getEntityClass() {
        Class<?> entityClass;
        if (batch && entity instanceof List) {
            List<Object> data = (List<Object>) entity;
            entityClass = data.get(0).getClass();
        } else {
            entityClass = entity.getClass();
        }
        return entityClass;
    }

    /**
     * h获取对象属性与值的对应关系
     *
     * @param object
     * @return
     */
    protected Map<String, String> getFieldMap(Object object) {
        Field[] fields = object.getClass().getFields();
        Map<String, String> fieldMap = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(object);
                fieldMap.put(field.getName(), TypeConvertUtil.simpleConvert(fieldValue));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return fieldMap;
    }

    /**
     * 获取对象属性值列表，和字段一一对应
     *
     * @param columns
     * @param object
     * @return
     */
    protected List<String> getFieldValues(List<String> columns, Object object) {
        List<String> values = new ArrayList<>();
        for (String column : columns) {
            values.add(getFieldMap(object).get(column));
        }
        return values;
    }
}
