package com.imitationsql.core.expression;

import cn.hutool.core.util.StrUtil;
import com.imitationsql.core.annotation.TableName;

/**
 * <p>Description: sql表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/11 11:30
 */

public interface SqlExpression<T> {

    /**
     * sql
     *
     * @return
     */
    String sql();

    /**
     * 获取表名
     *
     * @param entityClass
     * @return
     */
    default String getTableName(Class<?> entityClass) {
        TableName annotation = entityClass.getAnnotation(TableName.class);
        String tableName;
        if (null == annotation) {
            tableName = StrUtil.toUnderlineCase(entityClass.getSimpleName());
        } else {
            tableName = annotation.value();
        }
        return tableName;
    }
}
