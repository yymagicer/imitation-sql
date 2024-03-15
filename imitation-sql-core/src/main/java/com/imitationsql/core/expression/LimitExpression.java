package com.imitationsql.core.expression;

import com.imitationsql.core.enums.SqlKeyword;
import com.imitationsql.core.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: limit 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 17:47
 */
@Setter
@Getter
public class LimitExpression<T> implements SqlExpression<T> {

    /**
     * 开始位置跳过的行数,省略offset，则默认为0
     */
    private long offset;

    /**
     * 获取的行数
     */
    private long count;

    /**
     * limit
     *
     * @param offset
     * @param count
     * @return
     */
    public LimitExpression<T> limit(long offset, long count) {
        this.offset = offset;
        this.count = count;
        return this;
    }

    @Override
    public String sql() {
        StringBuilder builder = new StringBuilder(StringUtil.wrapBlank(SqlKeyword.LIMIT.getKeyword()));
        builder.append(this.offset).append(" ,  ").append(count);
        return builder.toString();
    }
}
