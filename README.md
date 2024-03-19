# imitation-sql

#### 介绍
java代码中像写编写SQL语句一样，编写代码

#### 软件架构
软件架构说明

#### 使用说明

##### 1.基本用法

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

##### 2.自动生成的api接口



```java
使用方法
在实体类上，继承BaseEntity，添加@EnableAutoApi注解即可

示例
@Setter
@Getter
@TableName("t_user")
@EnableAutoApi
public class UserEntity extends BaseEntity {

    private String userName;

    private String password;
}
```



自动生成的接口

#{entityName} 代表具体的实体类名称



| 接口名称     | 接口地址                           | 接口方法 | Content-Type     |
| ------------ | ---------------------------------- | -------- | ---------------- |
| 详情         | /#{entityName} /detail/{id}        | GET      |                  |
| 分页查询     | /#{entityName}/page                | POST     | application/json |
| 列表查询     | /#{entityName}/list                | POST     | application/json |
| 新增         | /#{entityName}                     | POST     | application/json |
| 修改         | /#{entityName}                     | POST     | application/json |
| 删除         | /#{entityName}                     | DELETE   |                  |
| 批量删除     | /#{entityName}/batchDelete         | DELETE   |                  |
| 物理删除     | /#{entityName}/physicalDelete      | DELETE   |                  |
| 批量物理删除 | /#{entityName}/physicalBatchDelete | DELETE   |                  |

接口返回值格式

```json
{
  <!-- 返回码 0-成功；-1失败 -->
  "code": "0", 
  "msg": "成功",
  "data": {} //具体返回值
}
```



###### 详情

入参

```
路径中填入id
```

出参

```json
{
  "code": "0", 		//返回码 0-成功；-1失败
  "msg": "成功",
  "data": {
      "id":1,
       ...			//其他的字段数据
  } 
}
```

###### 分页查询

入参

```json
{
    "pageSize": 10,			//每页显示条数，默认 10
    "pageNum": 1			//当前的页数
    ... 					//查询的参数
}
```

出参

```json
{
    "code": null,
    "msg": null,
    "data": {
        "records": [		//具体数据
            {
                "id": 1,
				 ...		//其他的字段数据
            }
        ],
        "total": 4,			//总条数
        "size": 10,			//每页显示条数，默认 10
        "current": 1,		//当前的页数
        "pages": 1			//总页数
    }
}
```



###### 列表查询

入参

```json
{
    ... 					//查询的参数
}
```

出参

```json
{
    "code": null,
    "msg": null,
    "data": [				//具体数据
        {
            "id": 1,
             ...			//其他的字段数据
        }
    ]
}
```

###### 新增

入参

```json
{
    ... 					//新增字段
}
```

出参

```json
{
    "code": null,
    "msg": null,
    "data": {
        "id": 1,
         ...			//其他的字段数据
    }
}
```

###### 修改

入参

```json
{
    "id":1,					//主键id
    ... 					//新增字段
}
```

出参

```json
{
    "code": null,
    "msg": null,
    "data": true			//修改成功返回true，失败返回false
}
```

###### 删除

入参

```json
id=11
```

出参

```json
{
    "code": null,
    "msg": null,
    "data": true			//删除成功返回true，失败返回false
}
```

###### 批量删除

入参

```json
ids=1,2,3,4
```

出参

```json
{
    "code": null,
    "msg": null,
    "data": true			//删除成功返回true，失败返回false
}
```

###### 物理删除

入参

```json
id=11
```

出参

```json
{
    "code": null,
    "msg": null,
    "data": true			//删除成功返回true，失败返回false
}
```

###### 批量物理删除

入参

```json
ids=1,2,3,4
```

出参

```json
{
    "code": null,
    "msg": null,
    "data": true			//删除成功返回true，失败返回false
}
```

###### 
