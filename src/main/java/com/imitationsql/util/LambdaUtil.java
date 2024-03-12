package com.imitationsql.util;

import cn.hutool.core.util.StrUtil;
import com.imitationsql.filter.Property;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 15:56
 */
public class LambdaUtil {

    /**
     * 获取对象字段属性名称
     *
     * @param property
     * @param <T>
     * @return
     */
    public static <T> String getPropertyName(Property<T, ?> property) {
        if (property == null) {
            return null;
        }
        try {
            Method declaredMethod = property.getClass().getDeclaredMethod("writeReplace");
            declaredMethod.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) declaredMethod.invoke(property);
            //Class::method
            return getPropertyNameByInvokeVirtual(serializedLambda);

        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getPropertyNameByInvokeVirtual(SerializedLambda serializedLambda) {
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
