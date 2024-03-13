package com.imitationsql.expression;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.imitationsql.enums.SqlKeyword;
import com.imitationsql.filter.OrderByFilter;
import com.imitationsql.filter.Property;
import com.imitationsql.util.LambdaUtil;
import com.imitationsql.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: order by 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 14:25
 */
@Setter
@Getter
public class OrderByExpression<T> implements SqlExpression<T> {

    private List<OrderByFilter<T>> orderByFilterList;


    /**
     * 排序
     *
     * @param column 排序的列名
     * @param asc    是否正序
     * @param <P>
     * @return
     */
    public <P> OrderByExpression<T> sort(Property<T, P> column, boolean asc) {
        if (null == orderByFilterList) {
            orderByFilterList = new ArrayList<>();
        }
        OrderByFilter<T> orderByFilter = new OrderByFilter<>(StrUtil.toUnderlineCase(LambdaUtil.getPropertyName(column)), true);
        orderByFilterList.add(orderByFilter);
        return this;
    }

    /**
     * 正序排序
     *
     * @param column 排序的列名
     * @param <P>
     * @return
     */
    public <P> OrderByExpression<T> asc(Property<T, P> column) {
        return sort(column, true);
    }

    /**
     * 倒序排序
     *
     * @param column 排序的列名
     * @param <P>
     * @return
     */
    public <P> OrderByExpression<T> desc(Property<T, P> column) {
        return sort(column, false);
    }


    @Override
    public String sql() {
        if (CollUtil.isEmpty(this.orderByFilterList)) {
            return "";
        }
        StringBuilder builder = new StringBuilder(StringUtil.wrapBlank(SqlKeyword.ORDER_BY.getKeyword()));
        orderByFilterList.forEach(item -> {
            builder.append(item.getColumnName()).append(item.isAsc() ? StringUtil.wrapBlank(SqlKeyword.ASC.getKeyword()) : StringUtil.wrapBlank(SqlKeyword.DESC.getKeyword())).append(",");
        });
        return builder.substring(0, builder.lastIndexOf(","));
    }
}
