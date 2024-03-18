package com.imitationsql.core.enums;

import lombok.Getter;

/**
 * <p>Description: 主键类型 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/18 16:13
 */
@Getter
public enum IdType {

    /**
     * 数据库ID自增
     */
    AUTO(0),

    /**
     * 分配雪花算法id,
     */
    ASSIGN_SNOWFLAKE_ID(1),
    /**
     * 分配UUID
     */
    ASSIGN_UUID(2),
    ;

    /**
     *
     */
    private Integer type;

    IdType(Integer type) {
        this.type = type;
    }
}
