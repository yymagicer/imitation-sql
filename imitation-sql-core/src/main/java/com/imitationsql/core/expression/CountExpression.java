package com.imitationsql.core.expression;

import com.imitationsql.core.enums.SqlKeyword;
import com.imitationsql.core.exception.ImitationSqlException;
import com.imitationsql.core.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: count 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 16:44
 */
@Setter
@Getter
public class CountExpression<T> implements SqlExpression<T> {
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
        StringBuilder builder = new StringBuilder(SqlKeyword.SELECT.getKeyword());
        builder.append(" count(1) ");
        builder.append(StringUtil.wrapBlank(SqlKeyword.FROM.getKeyword())).append(StringUtil.wrapBlank(tableName));
        return builder.toString();
    }
}
