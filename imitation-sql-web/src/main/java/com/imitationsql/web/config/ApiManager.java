package com.imitationsql.web.config;

import cn.hutool.core.util.StrUtil;
import com.imitationsql.web.api.BaseApi;
import com.imitationsql.web.domain.BaseEntity;
import com.imitationsql.web.domain.QueryPage;
import com.imitationsql.web.service.JdbcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>Description: api管理器 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 10:44
 */
@Component
@Slf4j
public class ApiManager implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Autowired
    private JdbcService jdbcService;
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;


    public boolean register2Spring(BaseApi<?> baseApi, String path, RequestMethod requestMethod, Method method, String... mediaType) {
        RequestMappingInfo.Builder builder = RequestMappingInfo.paths(path).methods(requestMethod).options(new RequestMappingInfo.BuilderConfiguration());
        if (null != mediaType) {
            builder.produces(mediaType);
        }
        RequestMappingInfo requestMappingInfo = builder.build();
        boolean status = true;
        try {
            requestMappingHandlerMapping.registerMapping(requestMappingInfo, baseApi, method);
            log.info("【接口注册成功】{}", path);
        } catch (Exception e) {
            status = false;
            log.error("【注册接口异常】动态映射失败", e);
        }
        return status;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] autoApiNames = applicationContext.getBeanNamesForAnnotation(EnableAutoApi.class);
        for (String autoApiName : autoApiNames) {
            Object bean = applicationContext.getBean(autoApiName);
            BaseApi<?> baseApi = new BaseApi<>(jdbcService, (Class<BaseEntity>) bean.getClass());
            Class<?> entityClass = bean.getClass();
            log.info("开始注册 {} api 接口", entityClass.getSimpleName());
            //注册详情方法
            registerDetailMethod(entityClass, baseApi);
            //注册分页查询方法
            registerPageQueryMethod(entityClass, baseApi);
            //注册列表查询方法
            registerListQueryMethod(entityClass, baseApi);
            //注册新增方法
            registerInsertMethod(entityClass, baseApi);
            //注册更新方法
            registerUpdateMethod(entityClass, baseApi);
            //注册删除方法
            registerDeleteMethod(entityClass, baseApi);
            //注册批量删除方法
            registerBatchDeleteMethod(entityClass, baseApi);
            //注册物理删除方法
            registerPhysicalDeleteMethod(entityClass, baseApi);
            //注册物理批量删除方法
            registerPhysicalBatchDeleteMethod(entityClass, baseApi);
        }
    }

    /**
     * 注册详情方法
     *
     * @param entityClass
     */
    private void registerDetailMethod(Class<?> entityClass, BaseApi<? extends BaseEntity> baseApi) {
        String url = "/" + StrUtil.lowerFirst(entityClass.getSimpleName()).replace("Entity", "") + "/" + "detail/{id}";
        RequestMethod requestMethod = RequestMethod.GET;
        log.info("{},{}", requestMethod.name(), url);
        Method method = ReflectionUtils.findMethod(baseApi.getClass(), "detail", Serializable.class);
        register2Spring(baseApi, url, requestMethod, method, null);
    }


    /**
     * 注册分页查询方法
     *
     * @param entityClass
     */
    private void registerPageQueryMethod(Class<?> entityClass, BaseApi<? extends BaseEntity> baseApi) {
        String url = "/" + StrUtil.lowerFirst(entityClass.getSimpleName()).replace("Entity", "") + "/" + "page";
        RequestMethod requestMethod = RequestMethod.POST;
        log.info("{},{}", requestMethod.name(), url);
        Method method = ReflectionUtils.findMethod(baseApi.getClass(), "pageQuery", QueryPage.class);
        register2Spring(baseApi, url, requestMethod, method, MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 注册列表查询方法
     *
     * @param entityClass
     */
    private void registerListQueryMethod(Class<?> entityClass, BaseApi<?> baseApi) {
        String url = "/" + StrUtil.lowerFirst(entityClass.getSimpleName()).replace("Entity", "") + "/" + "list";
        RequestMethod requestMethod = RequestMethod.POST;
        log.info("{},{}", requestMethod.name(), url);
        Method method = ReflectionUtils.findMethod(baseApi.getClass(), "list", Object.class);
        register2Spring(baseApi, url, requestMethod, method, MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 注册新增方法
     *
     * @param entityClass
     * @param baseApi
     */
    private void registerInsertMethod(Class<?> entityClass, BaseApi<? extends BaseEntity> baseApi) {
        String url = "/" + StrUtil.lowerFirst(entityClass.getSimpleName()).replace("Entity", "");
        RequestMethod requestMethod = RequestMethod.POST;
        log.info("{},{}", requestMethod.name(), url);
        Method method = ReflectionUtils.findMethod(baseApi.getClass(), "insert", Object.class);
        register2Spring(baseApi, url, requestMethod, method, MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 注册更新方法
     *
     * @param entityClass
     * @param baseApi
     */
    private void registerUpdateMethod(Class<?> entityClass, BaseApi<? extends BaseEntity> baseApi) {
        String url = "/" + StrUtil.lowerFirst(entityClass.getSimpleName()).replace("Entity", "");
        RequestMethod requestMethod = RequestMethod.PUT;
        log.info("{},{}", requestMethod.name(), url);
        Method method = ReflectionUtils.findMethod(baseApi.getClass(), "update", Object.class);
        register2Spring(baseApi, url, requestMethod, method, MediaType.APPLICATION_JSON_VALUE);
    }


    /**
     * 注册删除方法
     *
     * @param entityClass
     * @param baseApi
     */
    private void registerDeleteMethod(Class<?> entityClass, BaseApi<? extends BaseEntity> baseApi) {
        String url = "/" + StrUtil.lowerFirst(entityClass.getSimpleName()).replace("Entity", "");
        RequestMethod requestMethod = RequestMethod.DELETE;
        log.info("{},{}", requestMethod.name(), url);
        Method method = ReflectionUtils.findMethod(baseApi.getClass(), "delete", Serializable.class);
        register2Spring(baseApi, url, requestMethod, method, null);
    }


    /**
     * 注册批量删除方法
     *
     * @param entityClass
     * @param baseApi
     */
    private void registerBatchDeleteMethod(Class<?> entityClass, BaseApi<? extends BaseEntity> baseApi) {
        String url = "/" + StrUtil.lowerFirst(entityClass.getSimpleName()).replace("Entity", "/batchDelete");
        RequestMethod requestMethod = RequestMethod.DELETE;
        log.info("{},{}", requestMethod.name(), url);
        Method method = ReflectionUtils.findMethod(baseApi.getClass(), "batchDelete", String.class);
        register2Spring(baseApi, url, requestMethod, method, null);
    }

    /**
     * 注册删除方法
     *
     * @param entityClass
     * @param baseApi
     */
    private void registerPhysicalDeleteMethod(Class<?> entityClass, BaseApi<? extends BaseEntity> baseApi) {
        String url = "/" + StrUtil.lowerFirst(entityClass.getSimpleName()).replace("Entity", "/physicalDelete");
        RequestMethod requestMethod = RequestMethod.DELETE;
        log.info("{},{}", requestMethod.name(), url);
        Method method = ReflectionUtils.findMethod(baseApi.getClass(), "physicalDelete", Serializable.class);
        register2Spring(baseApi, url, requestMethod, method, null);
    }


    /**
     * 注册批量删除方法
     *
     * @param entityClass
     * @param baseApi
     */
    private void registerPhysicalBatchDeleteMethod(Class<?> entityClass, BaseApi<? extends BaseEntity> baseApi) {
        String url = "/" + StrUtil.lowerFirst(entityClass.getSimpleName()).replace("Entity", "/physicalBatchDelete");
        RequestMethod requestMethod = RequestMethod.DELETE;
        log.info("{},{}", requestMethod.name(), url);
        Method method = ReflectionUtils.findMethod(baseApi.getClass(), "physicalBatchDelete", List.class);
        register2Spring(baseApi, url, requestMethod, method, null);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
