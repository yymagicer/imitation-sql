package com.imitationsql.core.expression;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: update 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 13:45
 */
@Setter
@Getter
public class UpdateExpression<T> extends AbstractSqlExpression<T> {



    public UpdateExpression<T> updateById(T entity) {
        return this;
    }

    @Override
    public String sql() {
        return null;
    }
}
