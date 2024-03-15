package com.imitationsql.core;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: sql上下文 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/13 14:26
 */
@Setter
@Getter
public class SqlContext {

    private Map<Class<?>, String> CLASS_ALIAS = new HashMap<>();

    /**
     * 设置实体类对应的别名
     *
     * @param entityClass
     * @param alias
     */
    public void setAlias(Class<?> entityClass, String alias) {
        CLASS_ALIAS.put(entityClass, alias);
    }

    /**
     * 获取实体类对应的别名
     *
     * @param entityClass
     * @return
     */
    public String getAlias(Class<?> entityClass) {
        return CLASS_ALIAS.get(entityClass);
    }
}
