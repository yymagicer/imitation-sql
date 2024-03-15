package com.imitationsql.web.config;

import cn.hutool.core.util.StrUtil;
import com.imitationsql.web.api.BaseApi;
import com.imitationsql.web.domain.BaseEntity;
import com.imitationsql.web.service.JdbcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.Serializable;
import java.lang.reflect.Method;

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
            BaseApi<BaseEntity> baseApi = new BaseApi<>(jdbcService, (Class<BaseEntity>) bean.getClass());
            Class<?> entityClass = bean.getClass();
            log.info("开始注册 {} api 接口", entityClass.getSimpleName());
            //注册详情方法
            registerDetailMethod(entityClass, baseApi);
            //注册分页查询方法
//            registerPageQueryMethod(entityClass);
            //注册列表查询方法
//            registerListQueryMethod(entityClass);
            //注册新增方法
//            registerInsertMethod(entityClass);
            //注册更新方法
//            registerUpdateMethod(entityClass);
            //注册删除方法
//            registerDeleteMethod(entityClass);
            //注册批量删除方法
//            registerBatchDeleteMethod(entityClass);
        }
    }

    /**
     * 注册详情方法
     *
     * @param entityClass
     */
    private void registerDetailMethod(Class<?> entityClass, BaseApi<BaseEntity> baseApi) {
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
    private void registerPageQueryMethod(Class<?> entityClass) {

    }

    /**
     * 注册列表查询方法
     *
     * @param entityClass
     */
    private void registerListQueryMethod(Class<?> entityClass) {

    }

    /**
     * 注册新增方法
     *
     * @param entityClass
     */
    private void registerInsertMethod(Class<?> entityClass) {

    }

    /**
     * 注册更新方法
     *
     * @param entityClass
     */
    private void registerUpdateMethod(Class<?> entityClass) {

    }


    /**
     * 注册删除方法
     *
     * @param entityClass
     */
    private void registerDeleteMethod(Class<?> entityClass) {

    }

    /**
     * 注册批量删除方法
     *
     * @param entityClass
     */
    private void registerBatchDeleteMethod(Class<?> entityClass) {

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
