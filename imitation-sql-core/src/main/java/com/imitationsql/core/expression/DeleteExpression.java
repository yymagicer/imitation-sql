package com.imitationsql.core.expression;

import com.imitationsql.core.enums.SqlKeyword;
import com.imitationsql.core.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: delete 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 13:46
 */
@Setter
@Getter
public class DeleteExpression<T> extends AbstractSqlExpression<T> {



    @Override
    public String sql() {
        String tableName = getTableName();
        StringBuilder builder = new StringBuilder(StringUtil.wrapBlank(SqlKeyword.DELETE.getKeyword())).append(StringUtil.wrapBlank(SqlKeyword.FROM.getKeyword())).append(tableName);
        return builder.toString();
    }
}
