package com.imitationsql.web.domain;

import com.imitationsql.core.annotation.PrimaryKey;
import com.imitationsql.core.constants.BaseEntityConstant;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 13:59
 */
@Setter
@Getter
public class BaseEntity {
    /**
     * id
     */
    @PrimaryKey
    private Serializable id;

    /**
     * 是否删除
     */
    private Integer deleted = BaseEntityConstant.NOT_DELETE;
}
