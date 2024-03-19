package com.imitationsql.core.enums;

import lombok.Getter;

/**
 * <p>Description: SQL 保留关键字枚举 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/11 14:18
 */
@Getter
public enum SqlKeyword {

    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    FROM("FROM"),
    WHERE("WHERE"),
    VALUE("VALUE"),
    VALUES("VALUES"),
    INTO("INTO"),
    SET("SET"),
    ON("ON"),
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
    DESC("DESC"),
    LEFT_JOIN("LEFT JOIN"),
    INNER_JOIN("INNER JOIN"),
    RIGHT_JOIN("RIGHT JOIN"),

    LIMIT("LIMIT"),
    ;

    private final String keyword;

    SqlKeyword(String keyword) {
        this.keyword = keyword;
    }

}
