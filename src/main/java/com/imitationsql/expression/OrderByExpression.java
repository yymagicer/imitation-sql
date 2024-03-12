package com.imitationsql.expression;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: order by 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 14:25
 */
@Setter
@Getter
public class OrderByExpression<T> implements SqlExpression<T> {
    @Override
    public String sql() {
        return null;
    }
}
