package com.imitationsql.expression;

import com.imitationsql.enums.SqlKeyword;
import com.imitationsql.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: right join 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/13 10:40
 */
@Setter
@Getter
public class RightJoinExpression<T> extends JoinExpression<T> {
    @Override
    public String sql() {
        StringBuilder builder = new StringBuilder(StringUtil.wrapBlank(SqlKeyword.RIGHT_JOIN.getKeyword()));
        String tableName = getTableName(super.joinEntityClass);
        builder.append(StringUtil.wrapBlank(tableName)).append(StringUtil.wrapBlank(SqlKeyword.ON.getKeyword())).append(StringUtil.wrapBlank(super.columnName)).append(StringUtil.wrapBlank(super.operateEnum.getType())).append(StringUtil.wrapBlank(super.joinColumnName));
        return builder.toString();
    }
}
