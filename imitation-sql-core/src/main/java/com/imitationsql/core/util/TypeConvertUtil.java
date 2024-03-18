package com.imitationsql.core.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Date;

/**
 * <p>Description: 类型转换工具类 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 11:17
 */
public class TypeConvertUtil {

    /**
     * 简单类型转换
     *
     * @param data
     * @return
     */
    public static String simpleConvert(Object data) {
        if (null == data) {
            return null;
        }
        //转换布尔类型
        if (data instanceof Boolean) {
            return String.valueOf((boolean) data ? 1 : 0);
        }
        //转换时间类型
        if (data instanceof Date) {
            return StrUtil.wrap(DateUtil.formatDateTime((Date) data), "'");
        }
        return StrUtil.wrap(data.toString(), "'");
    }
}
