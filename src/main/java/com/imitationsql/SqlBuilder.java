package com.imitationsql;

import com.imitationsql.expression.*;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: sql生成器 </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/11 11:37
 */
@Setter
@Getter
public class SqlBuilder<T> {

    private SelectExpression<T> selectExpression;

    private WhereExpression<T> whereExpression;

    private Class<T> entityClass;
    /**
     * sql节点
     */
    private SqlNode sqlNode;

    private SqlNode last;

    public SqlBuilder() {
    }

    public SqlBuilder(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public SqlBuilder<T> select() {
        if (null == this.selectExpression) {
            this.selectExpression = new SelectExpression<T>();
            this.selectExpression.setEntityClass(entityClass);
            next(this.selectExpression);
        }
        return this;
    }

    /**
     * select
     *
     * @param expression
     * @return
     */
    public SqlBuilder<T> select(Expression<SelectExpression<T>> expression) {
        if (null == this.selectExpression) {
            this.selectExpression = new SelectExpression<>();
            this.selectExpression.setEntityClass(entityClass);
            next(this.selectExpression);
        }
        expression.apply(this.selectExpression);
        return this;
    }


    /**
     * where
     *
     * @param expression
     * @return
     */
    public SqlBuilder<T> where(Expression<WhereExpression<T>> expression) {
        if (null == this.whereExpression) {
            this.whereExpression = new WhereExpression<>();
            next(whereExpression);
        }
        expression.apply(this.whereExpression);
        return this;
    }


    /**
     * group by
     *
     * @param groupByExpression
     * @return
     */
    public SqlBuilder<T> groupBy(GroupByExpression<T> groupByExpression) {
        return next(groupByExpression);
    }

    /**
     * order by
     *
     * @param orderByExpression
     * @return
     */
    public SqlBuilder<T> orderBy(OrderByExpression<T> orderByExpression) {
        return next(orderByExpression);
    }

    /**
     * having
     *
     * @param havingExpression
     * @return
     */
    public SqlBuilder<T> having(HavingExpression<T> havingExpression) {
        return next(havingExpression);
    }

    /**
     * exists
     *
     * @param existsExpression
     * @return
     */
    public SqlBuilder<T> exists(ExistsExpression<T> existsExpression) {
        return next(existsExpression);
    }

    /**
     * not exists
     *
     * @param notExistsExpression
     * @return
     */
    public SqlBuilder<T> notExists(NotExistsExpression<T> notExistsExpression) {
        return next(notExistsExpression);
    }


    public SqlBuilder<T> next(SqlExpression<T> sqlExpression) {
        //根节点
        if (this.sqlNode == null) {
            SqlNode node = new SqlNode();
            node.setExpression(sqlExpression);
            this.sqlNode = node;
            this.last = node;
        } else {
            SqlNode node = new SqlNode(sqlExpression);
            SqlNode l = this.last;
            l.setNextNode(node);
            this.last = node;
        }
        return this;
    }

    /**
     * 生成sql语句
     *
     * @return
     */
    public String buildSql() {
        if (this.sqlNode == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder(this.sqlNode.getExpression().sql());
        SqlNode nextNode = this.sqlNode.getNextNode();
        while (nextNode != null) {
            builder.append(nextNode.getExpression().sql());
            nextNode = nextNode.getNextNode();
        }
        return builder.toString();
    }
}
