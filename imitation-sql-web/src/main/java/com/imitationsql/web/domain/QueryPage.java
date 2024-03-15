package com.imitationsql.web.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * <p>Description: 分页查询参数 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 11:33
 */
@Setter
@Getter
public class QueryPage extends HashMap<String, Object> {

    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前的页数
     */
    private long pageNum;
}
