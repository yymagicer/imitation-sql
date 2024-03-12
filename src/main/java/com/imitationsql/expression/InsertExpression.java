package com.imitationsql.expression;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.imitationsql.exception.ImitationSqlException;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: insert 表示 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 10:52
 */
@Setter
@Getter
public class InsertExpression<T> extends AbstractSqlExpression {

    /**
     * 列集合
     */
    private List<String> columns;

    @Override
    public String sql() {
        if (null == entity) {
            throw new ImitationSqlException("entity can not null");
        }
        Class<?> entityClass = getEntityClass();
        String tableName = getTableName();
        StringBuilder builder = new StringBuilder("insert into ").append(tableName).append(" ( ");
        List<String> columnList;
        if (CollUtil.isEmpty(columns)) {
            columnList = Arrays.stream(ReflectUtil.getFields(entityClass)).map(Field::getName).collect(Collectors.toList());
        } else {
            columnList = columns;
        }
        builder.append(String.join(",", columnList)).append(" ) ");
        if (batch) {
            builder.append(" values ");
            List<Object> entitys = (List<Object>) entity;
            for (Object o : entitys) {
                builder.append(" (").append(String.join(",", getFieldValues(columnList, o))).append(" )").append(",");
            }
            return builder.substring(0, builder.lastIndexOf(","));
        } else {
            return builder.append("value ( ").append(String.join(",", getFieldValues(columnList, entity))).append(" )").toString();
        }
    }
}
