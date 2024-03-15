package com.imitationsql.web.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * <p>Description: 分页查询 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 11:30
 */
@Setter
@Getter
public class Page<T> {

    /**
     * 数据
     */
    protected List<T> records = Collections.emptyList();
    /**
     * 总数
     */
    protected long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    protected long size = 10;

    /**
     * 当前页
     */
    protected long current = 1;

}
