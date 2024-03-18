package com.imitationsql.core.constants;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 17:31
 */
public class BaseEntityConstant {

    public static final Integer NOT_DELETE = 0;

    public static final Integer DELETED = 1;


    public static final String DELETED_FIELD = "deleted";

    /**
     * 查询最后一次新增id的值
     */
    public static final String QUERY_LASTINSERT_ID = "SELECT LAST_INSERT_ID()";

}
