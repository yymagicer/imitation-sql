package com.imitationsql.enums;

import lombok.Getter;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 15:51
 */
@Getter
public enum OperateEnum {

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

    IS_NOT_NULL("IS NOT NULL");

    private final String type;

    OperateEnum(String type) {
        this.type = type;
    }
}
