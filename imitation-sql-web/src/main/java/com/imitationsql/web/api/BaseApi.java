package com.imitationsql.web.api;

import cn.hutool.core.bean.BeanUtil;
import com.imitationsql.web.domain.BaseEntity;
import com.imitationsql.web.domain.CommonResult;
import com.imitationsql.web.domain.Page;
import com.imitationsql.web.domain.QueryPage;
import com.imitationsql.web.service.JdbcService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 14:09
 */
public class BaseApi<T extends BaseEntity> {

    private final JdbcService jdbcService;

    private final Class<T> entityClass;

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

    @ResponseBody
    public CommonResult<Page<T>> pageQuery(@RequestBody QueryPage page) {
        CommonResult<Page<T>> result = new CommonResult<>();
        result.setData(jdbcService.pageQuery(this.entityClass, page));
        return result;
    }

    @ResponseBody
    public CommonResult<T> insert(@RequestBody Object entity) {
        CommonResult<T> result = new CommonResult<>();
        result.setData(jdbcService.insert(BeanUtil.toBean(entity, this.entityClass)));
        return result;
    }
}
