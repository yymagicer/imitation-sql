package com.imitationsql.expression;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.imitationsql.enums.SqlKeyword;
import com.imitationsql.filter.GroupByFilter;
import com.imitationsql.filter.Property;
import com.imitationsql.util.LambdaUtil;
import com.imitationsql.util.StringUtil;

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
