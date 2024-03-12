package com.imitationsql;

import com.imitationsql.expression.SqlExpression;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: sql节点
 * <p>
 * 使用链表结构处理数据
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 10:39
 */
@Setter
@Getter
public class SqlNode {


    /**
     * 下一个节点
     */
    private SqlNode nextNode;

    /**
     * sql 表达式
     */
    private SqlExpression<?> expression;


    public SqlNode() {
    }

    public SqlNode(SqlNode nextNode, SqlExpression<?> expression) {
        this.nextNode = nextNode;
        this.expression = expression;
    }

    public SqlNode(SqlExpression<?> expression) {
        this.expression = expression;
    }
}
