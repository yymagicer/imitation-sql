package com.imitationsql.core.expression;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.imitationsql.core.enums.SqlKeyword;
import com.imitationsql.core.filter.Property;
import com.imitationsql.core.util.LambdaUtil;
import com.imitationsql.core.util.StringUtil;
import com.imitationsql.core.filter.GroupByFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: group by 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 14:24
 */
public class GroupByExpression<T> implements SqlExpression<T> {

    private List<GroupByFilter<T>> groupByFilterList;

    /**
     * 分组
     *
     * @param column 排序的列名
     * @param <P>
     * @return
     */
    public <P> GroupByExpression<T> groupBy(Property<T, P> column) {
        if (null == groupByFilterList) {
            groupByFilterList = new ArrayList<>();
        }
        GroupByFilter<T> groupByFilter = new GroupByFilter<>(StrUtil.toUnderlineCase(LambdaUtil.getPropertyName(column)));
        groupByFilterList.add(groupByFilter);
        return this;
    }

    @Override
    public String sql() {
        if (CollUtil.isEmpty(this.groupByFilterList)) {
            return "";
        }
        StringBuilder builder = new StringBuilder(StringUtil.wrapBlank(SqlKeyword.GROUP_BY.getKeyword()));
        groupByFilterList.forEach(item -> {
            builder.append(item.getColumnName()).append(",");
        });
        return builder.substring(0, builder.lastIndexOf(","));
    }
}
