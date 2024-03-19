package com.imitationsql.web.api;

import cn.hutool.core.bean.BeanUtil;
import com.imitationsql.web.domain.BaseEntity;
import com.imitationsql.web.domain.CommonResult;
import com.imitationsql.web.domain.Page;
import com.imitationsql.web.domain.QueryPage;
import com.imitationsql.web.service.JdbcService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @ResponseBody
    public CommonResult<T> detail(@PathVariable(name = "id") Serializable id) {
        CommonResult<T> result = new CommonResult<>();
        result.setData(jdbcService.detail(this.entityClass, id));
        return result;
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @ResponseBody
    public CommonResult<Page<T>> pageQuery(@RequestBody QueryPage page) {
        CommonResult<Page<T>> result = new CommonResult<>();
        result.setData(jdbcService.pageQuery(this.entityClass, BeanUtil.toBean(page, QueryPage.class)));
        return result;
    }

    /**
     * 列表查询
     *
     * @param entity
     * @return
     */
    @ResponseBody
    public CommonResult<List<T>> list(@RequestBody Object entity) {
        CommonResult<List<T>> result = new CommonResult<>();
        result.setData(jdbcService.list(this.entityClass, BeanUtil.toBean(entity, this.entityClass)));
        return result;
    }

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @ResponseBody
    public CommonResult<T> insert(@RequestBody Object entity) {
        CommonResult<T> result = new CommonResult<>();
        result.setData(jdbcService.insert(BeanUtil.toBean(entity, this.entityClass)));
        return result;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody Object entity) {
        CommonResult<Boolean> result = new CommonResult<>();
        result.setData(jdbcService.update(BeanUtil.toBean(entity, this.entityClass)));
        return result;
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestParam(name = "id") Serializable id) {
        CommonResult<Boolean> result = new CommonResult<>();
        result.setData(jdbcService.delete(entityClass, id));
        return result;
    }

    /**
     * 逻辑批量删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    public CommonResult<Boolean> batchDelete(@RequestParam(name = "ids") String ids) {
        CommonResult<Boolean> result = new CommonResult<>();
        result.setData(jdbcService.batchDelete(entityClass, Arrays.asList(ids.split(","))));
        return result;
    }

    /**
     * 物理删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    public CommonResult<Boolean> physicalDelete(@RequestParam(name = "id") Serializable id) {
        CommonResult<Boolean> result = new CommonResult<>();
        result.setData(jdbcService.physicalDelete(entityClass, id));
        return result;
    }

    /**
     * 物理批量删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    public CommonResult<Boolean> physicalBatchDelete(@RequestParam(name = "ids") List<Serializable> ids) {
        CommonResult<Boolean> result = new CommonResult<>();
        result.setData(jdbcService.physicalBatchDelete(entityClass, ids));
        return result;
    }
}
