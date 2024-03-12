package com.imitationsql;

import com.imitationsql.filter.SqlFilter;
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

}
