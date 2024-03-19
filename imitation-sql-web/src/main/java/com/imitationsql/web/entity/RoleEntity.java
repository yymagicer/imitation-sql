package com.imitationsql.web.entity;

import com.imitationsql.core.annotation.PrimaryKey;
import com.imitationsql.core.annotation.Query;
import com.imitationsql.core.annotation.TableName;
import com.imitationsql.core.enums.IdType;
import com.imitationsql.core.enums.OperateEnum;
import com.imitationsql.web.config.EnableAutoApi;
import com.imitationsql.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Description: 角色 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/19 16:49
 */
@Setter
@Getter
@TableName("t_role")
@EnableAutoApi
public class RoleEntity extends BaseEntity {

    /**
     * id
     */
    @PrimaryKey(type = IdType.ASSIGN_UUID)
    private Serializable id;

    @Query(operate = OperateEnum.LIKE)
    private String roleName;
}
