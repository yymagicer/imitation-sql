package com.imitationsql.core.util;

import cn.hutool.core.util.StrUtil;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/13 9:34
 */
public class StringUtil {


    /**
     * 对字符串进行空格包裹
     *
     * @param str
     * @return
     */
    public static String wrapBlank(String str) {
        if (StrUtil.isBlank(str)) {
            return "";
        }
        return " " + str + " ";
    }
}
