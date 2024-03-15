package com.imitationsql.core.util;

import cn.hutool.core.util.StrUtil;
import com.imitationsql.core.annotation.TableName;
import com.imitationsql.core.filter.Property;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 15:56
 */
public class LambdaUtil {
    static Pattern RETURN_TYPE_PATTERN = Pattern.compile("\\(.*\\)L(.*);");
    static Pattern PARAMETER_TYPE_PATTERN = Pattern.compile("\\((.*)\\).*");


    /**
     * 获取完整的对象字段属性名称
     *
     * @param property
     * @return
     */
    public static String getFullPropertyName(Property<?, ?> property) {
        SerializedLambda serializedLambda = getSerializedLambda(property);
        if (null == serializedLambda) {
            return null;
        }
        Class<?> entityClass = getParameterTypes(serializedLambda).get(0);
        return getTableName(entityClass) + "." + getPropertyName(serializedLambda);
    }

    /**
     * 获取表名
     *
     * @param entityClass
     * @return
     */
    private static String getTableName(Class<?> entityClass) {
        TableName annotation = entityClass.getAnnotation(TableName.class);
        String tableName;
        if (null == annotation) {
            tableName = StrUtil.toUnderlineCase(entityClass.getSimpleName());
        } else {
            tableName = annotation.value();
        }
        return tableName;
    }

    /**
     * 获取对象字段属性名称
     *
     * @param property
     * @return
     */
    public static String getPropertyName(Property<?, ?> property) {
        SerializedLambda serializedLambda = getSerializedLambda(property);
        if (null == serializedLambda) {
            return null;
        }
        return getPropertyName(serializedLambda);
    }


    /**
     * 获取Lambda表达式的参数类型
     *
     * @param property
     * @return
     */
    public static Class<?> getParameterType(Property<?, ?> property) {
        SerializedLambda serializedLambda = getSerializedLambda(property);
        if (null == serializedLambda) {
            return null;
        }
        return getParameterTypes(serializedLambda).get(0);
    }


    public static Class<?> getReturnType(Property<?, ?> property) {
        SerializedLambda serializedLambda = getSerializedLambda(property);
        if (null == serializedLambda) {
            return null;
        }
        return getReturnType(serializedLambda);
    }


    public static SerializedLambda getSerializedLambda(Property<?, ?> property) {
        if (property == null) {
            return null;
        }
        try {
            Method declaredMethod = property.getClass().getDeclaredMethod("writeReplace");
            declaredMethod.setAccessible(Boolean.TRUE);
            return (SerializedLambda) declaredMethod.invoke(property);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取Lambda表达式的参数类型
     */
    public static List<Class<?>> getParameterTypes(SerializedLambda serializedLambda) {
        String expr = serializedLambda.getInstantiatedMethodType();
        Matcher matcher = PARAMETER_TYPE_PATTERN.matcher(expr);
        if (!matcher.find() || matcher.groupCount() != 1) {
            throw new RuntimeException("获取Lambda信息失败");
        }
        expr = matcher.group(1);
        return Arrays.stream(expr.split(";")).filter(s -> !s.isEmpty()).map(s -> s.replace("L", "").replace("/", ".")).map(s -> {
            try {
                return Class.forName(s);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("无法加载类", e);
            }
        }).collect(Collectors.toList());
    }

    /**
     * 获取Lambda表达式返回类型
     */
    public static Class<?> getReturnType(SerializedLambda serializedLambda) {
        String expr = serializedLambda.getInstantiatedMethodType();
        Matcher matcher = RETURN_TYPE_PATTERN.matcher(expr);
        if (!matcher.find() || matcher.groupCount() != 1) {
            throw new RuntimeException("获取Lambda信息失败");
        }
        String className = matcher.group(1).replace("/", ".");
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("无法加载类", e);
        }
    }

    private static String getPropertyName(SerializedLambda serializedLambda) {
        String method = serializedLambda.getImplMethodName();
        String attr;
        if (method.startsWith("get")) {
            attr = method.substring(3);
        } else {
            attr = method.substring(2);
        }
        return StrUtil.lowerFirst(attr);
    }
}
