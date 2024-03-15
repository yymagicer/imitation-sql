package com.imitationsql.web.api;

import com.imitationsql.web.domain.BaseEntity;
import com.imitationsql.web.domain.CommonResult;
import com.imitationsql.web.service.JdbcService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 14:09
 */
public class BaseApi<T extends BaseEntity> {

    private JdbcService jdbcService;

    private Class<T> entityClass;

    public BaseApi(JdbcService jdbcService, Class<T> entityClass) {
        this.jdbcService = jdbcService;
        this.entityClass = entityClass;
    }

    @ResponseBody
    public CommonResult<T> detail(@PathVariable(name = "id") Serializable id) {
        CommonResult<T> result = new CommonResult<>();
        result.setData(jdbcService.detail(this.entityClass, id));
        return result;
    }
}
