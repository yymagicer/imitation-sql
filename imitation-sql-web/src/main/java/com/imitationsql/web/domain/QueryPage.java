package com.imitationsql.web.domain;

import com.imitationsql.core.constants.CommonConstant;

import java.util.HashMap;

/**
 * <p>Description: 分页查询参数 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 11:33
 */
public class QueryPage extends HashMap<String, Object> {

    public long getPageSize() {
        Object pageSize = get(CommonConstant.PAGE_SIZE);
        return null == pageSize ? 10 : Long.parseLong(pageSize.toString());
    }

    public long getPageNum() {
        Object pageNum = get(CommonConstant.PAGE_NUM);
        return null == pageNum ? 1 : Long.parseLong(pageNum.toString());
    }

    public long getOffset() {
        return (getPageNum() - 1) * getPageSize();
    }
}
