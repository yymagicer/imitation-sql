package com.imitationsql.core.expression;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.imitationsql.core.annotation.PrimaryKey;
import com.imitationsql.core.enums.IdType;
import com.imitationsql.core.enums.SqlKeyword;
import com.imitationsql.core.util.StringUtil;
import com.imitationsql.core.exception.ImitationSqlException;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * <p>Description: insert 表示 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 10:52
 */
@Setter
@Getter
public class InsertExpression<T> extends AbstractSqlExpression<T> {

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
        StringBuilder builder = new StringBuilder(StringUtil.wrapBlank(SqlKeyword.INSERT.getKeyword())).append(StringUtil.wrapBlank(SqlKeyword.INTO.getKeyword())).append(tableName).append(" ( ");
        List<String> columnList;
        if (CollUtil.isEmpty(columns)) {
            columnList = Arrays.stream(ReflectUtil.getFields(entityClass)).filter(item -> {
                PrimaryKey annotation = item.getAnnotation(PrimaryKey.class);
                if (null == annotation) {
                    return true;
                }
                if (IdType.AUTO.equals(annotation.type())) {
                    return false;
                } else {
                    return true;
                }
            }).map(item -> StrUtil.toUnderlineCase(item.getName())).collect(Collectors.toList());
        } else {
            columnList = columns;
        }
        builder.append(String.join(",", columnList)).append(" ) ");
        if (batch) {
            builder.append(StringUtil.wrapBlank(SqlKeyword.VALUES.getKeyword()));
            List<Object> entitys = (List<Object>) entity;
            for (Object o : entitys) {
                builder.append(" (").append(String.join(",", getFieldValues(columnList, o))).append(" )").append(",");
            }
            return builder.substring(0, builder.lastIndexOf(","));
        } else {
            return builder.append(StringUtil.wrapBlank(SqlKeyword.VALUE.getKeyword())).append("( ").append(String.join(",", getFieldValues(columnList, entity))).append(" )").toString();
        }
    }
}
