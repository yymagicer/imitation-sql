package com.imitationsql.core.expression;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.imitationsql.core.annotation.PrimaryKey;
import com.imitationsql.core.enums.IdType;
import com.imitationsql.core.util.TypeConvertUtil;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.*;

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
        Field[] fields = ReflectUtil.getFields(object.getClass());
        Map<String, String> fieldMap = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(object);
                PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
                if (primaryKey != null) {

                    if (!IdType.AUTO.equals(primaryKey.type())) {
                        if (IdType.ASSIGN_SNOWFLAKE_ID.equals(primaryKey.type())) {
                            if (null == fieldValue) {
                                fieldValue = IdUtil.getSnowflake().nextId();
                            }
                        } else if (IdType.ASSIGN_UUID.equals(primaryKey.type())) {
                            fieldValue = IdUtil.fastUUID();
                        }
                        fieldMap.put(field.getName(), TypeConvertUtil.simpleConvert(fieldValue));
                    }
                } else {
                    fieldMap.put(field.getName(), TypeConvertUtil.simpleConvert(fieldValue));
                }

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
        Map<String, String> fieldMap = getFieldMap(object);
        Set<String> keySet = fieldMap.keySet();
        for (String column : columns) {
            String fieldName = StrUtil.toCamelCase(column);
            if (keySet.contains(fieldName)) {
                values.add(getFieldMap(object).get(fieldName));
            }
        }
        return values;
    }
}
