package com.imitationsql.web.entity;

import com.imitationsql.core.annotation.Query;
import com.imitationsql.core.annotation.TableName;
import com.imitationsql.core.enums.OperateEnum;
import com.imitationsql.web.config.EnableAutoApi;
import com.imitationsql.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 16:53
 */
@Setter
@Getter
@TableName("t_user")
@EnableAutoApi
public class UserEntity extends BaseEntity {

    @Query(operate = OperateEnum.LIKE)
    private String userName;

    private String password;
}
