package com.imitationsql.core;

import com.imitationsql.core.enums.OperateEnum;
import com.imitationsql.core.filter.SqlFilter;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: 过滤节点 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 15:25
 */
@Setter
@Getter
public class FilterNode<F extends SqlFilter<T>, T> {

    /**
     * 下一个节点
     */
    private FilterNode<F, T> next;
    /**
     * 过滤器
     */
    private F filter;

    /**
     * 操作
     */
    private OperateEnum operateEnum;

    public FilterNode() {
    }

    public FilterNode(FilterNode<F, T> next, F filter, OperateEnum operateEnum) {
        this.next = next;
        this.filter = filter;
        this.operateEnum = operateEnum;
    }
}
