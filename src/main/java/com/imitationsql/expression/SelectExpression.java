package com.imitationsql.expression;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.imitationsql.exception.ImitationSqlException;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: select 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/11 11:30
 */
@Setter
@Getter
public class SelectExpression<T> extends AbstractSqlExpression<T> {

    /**
     * 列集合
     */
    private List<String> columns;

    /**
     * 实体类
     */
    private Class<T> entityClass;

    @Override
    public String sql() {
        if (null == entityClass) {
            throw new ImitationSqlException("entityClass can not null");
        }
        String tableName = getTableName(entityClass);
        StringBuilder builder = new StringBuilder("select");
        List<String> columnList;
        if (CollUtil.isEmpty(columns)) {
            columnList = Arrays.stream(ReflectUtil.getFields(entityClass)).map(item -> StrUtil.toUnderlineCase(item.getName())).collect(Collectors.toList());
        } else {
            columnList = columns;
        }
        builder.append(" ").append(String.join(",", columnList));
        builder.append(" from ").append(tableName);
        return builder.toString();
    }
}
