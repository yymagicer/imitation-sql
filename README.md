# imitation-sql

#### 介绍
java代码中像写编写SQL语句一样，编写代码

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明



```java
//1.select
SqlBuilder<UserEntity> sqlBuilder = new SqlBuilder<>(UserEntity.class);
String sql = sqlBuilder.select().buildSql();

//2.select where
SqlBuilder<UserEntity> sqlBuilder = new SqlBuilder<>(UserEntity.class);
String sql = sqlBuilder.select().where(o -> o.and(UserEntity::getUserName, "11").and(UserEntity::getPassword, "1234")).buildSql();

//3.select Where group by order by
SqlBuilder<UserEntity> sqlBuilder = new SqlBuilder<>(UserEntity.class);
String sql = sqlBuilder.select().where(o -> o.and(UserEntity::getUserName, "11").and(UserEntity::getPassword, "1234")).groupBy(o -> o.groupBy(UserEntity::getUserName)).orderBy(o -> o.asc(UserEntity::getUserName)).buildSql();

//4.select  join where
SqlBuilder<UserEntity> sqlBuilder = new SqlBuilder<>(UserEntity.class);
String sql = sqlBuilder.select().leftJoin(o -> o.eq(UserRoleEntity.class, UserEntity::getId, UserRoleEntity::getUserId))
        .innerJoin(o -> o.eq(UserRoleEntity.class, RoleEntity.class, UserRoleEntity::getRoleId, RoleEntity::getId))
        .where(o -> o.and(UserEntity::getUserName, "11").and(UserRoleEntity.class, UserRoleEntity::getRoleId, "1")).buildSql();
        
```



#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
