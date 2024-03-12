package com.imitationsql.enums;

import lombok.Getter;

/**
 * <p>Description: SQL 保留关键字枚举 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/11 14:18
 */
@Getter
public enum SqlKeyword {

    AND("AND"),
    OR("OR"),
    NOT("NOT"),
    IN("IN"),
    NOT_IN("NOT IN"),
    LIKE("LIKE"),
    NOT_LIKE("NOT LIKE"),
    EQ("="),
    NE("<>"),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    GROUP_BY("GROUP BY"),
    HAVING("HAVING"),
    ORDER_BY("ORDER BY"),
    EXISTS("EXISTS"),
    NOT_EXISTS("NOT EXISTS"),
    BETWEEN("BETWEEN"),
    NOT_BETWEEN("NOT BETWEEN"),
    ASC("ASC"),
    DESC("DESC");

    private final String keyword;

    SqlKeyword(String keyword) {
        this.keyword = keyword;
    }

}
