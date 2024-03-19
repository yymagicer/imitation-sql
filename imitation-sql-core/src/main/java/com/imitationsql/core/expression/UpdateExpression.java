package com.imitationsql.core.expression;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.imitationsql.core.enums.OperateEnum;
import com.imitationsql.core.enums.SqlKeyword;
import com.imitationsql.core.filter.Property;
import com.imitationsql.core.filter.UpdateFilter;
import com.imitationsql.core.util.LambdaUtil;
import com.imitationsql.core.util.StringUtil;
import com.imitationsql.core.util.TypeConvertUtil;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: update 表达式 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 13:45
 */
@Setter
@Getter
public class UpdateExpression<T> extends AbstractSqlExpression<T> {

    private List<UpdateFilter<T>> updateFilterList;


    public UpdateExpression<T> updateById(T entity) {

        return this;
    }

    public UpdateExpression<T> update(T entity) {
        if (this.updateFilterList == null) {
            updateFilterList = new ArrayList<>();
        }
        Field[] fields = ReflectUtil.getFields(entity.getClass());
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = TypeConvertUtil.simpleConvert(field.get(entity));
                if (null != fieldValue) {
                    updateFilterList.add(new UpdateFilter<>(LambdaUtil.getFullPropertyName(entity.getClass(), field.getName()), fieldValue));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    /**
     * set操作
     *
     * @param column lambad表达式
     * @param val    对象
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> UpdateExpression<T> set(Property<T, P> column, V val) {
        return set(null, column, val);
    }

    /**
     * set操作
     *
     * @param column lambad表达式
     * @param val    对象
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> UpdateExpression<T> set(String tableName, Property<T, P> column, V val) {
        if (this.updateFilterList == null) {
            updateFilterList = new ArrayList<>();
        }
        String propertyName;
        if (StrUtil.isBlank(tableName)) {
            propertyName = LambdaUtil.getFullPropertyName(column);
        } else {
            propertyName = tableName + "." + LambdaUtil.getPropertyName(column);
        }
        UpdateFilter<T> updateFilter = new UpdateFilter<>(propertyName, val);
        updateFilterList.add(updateFilter);
        return this;
    }

    /**
     * set操作
     *
     * @param columnName 表列名称
     * @param val        对象
     * @param <P>
     * @param <V>
     * @return
     */
    public <P, V> UpdateExpression<T> set(String columnName, V val) {
        if (this.updateFilterList == null) {
            updateFilterList = new ArrayList<>();
        }
        UpdateFilter<T> updateFilter = new UpdateFilter<>(LambdaUtil.getFullPropertyName(this.getEntityClass(), columnName), val);
        updateFilterList.add(updateFilter);
        return this;
    }

    @Override
    public String sql() {
        String tableName = getTableName();
        StringBuilder builder = new StringBuilder(StringUtil.wrapBlank(SqlKeyword.UPDATE.getKeyword())).append(tableName).append(StringUtil.wrapBlank(SqlKeyword.SET.getKeyword()));
        if (CollUtil.isNotEmpty(updateFilterList)) {
            updateFilterList.forEach(item -> {
                builder.append(StringUtil.wrapBlank(item.getPropertyName())).append(OperateEnum.EQ.getType()).append(StringUtil.wrapBlank(item.getVal().toString())).append(",");
            });
        }
        return builder.substring(0, builder.lastIndexOf(","));
    }
}
