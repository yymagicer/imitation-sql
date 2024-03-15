import com.imitationsql.core.SqlBuilder;
import org.junit.Test;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 18:13
 */
public class SqlBuilderTest {


    @Test
    public void testSelect() {
        SqlBuilder<UserEntity> sqlBuilder = new SqlBuilder<>(UserEntity.class);
        String sql = sqlBuilder.select().buildSql();
        System.out.println(sql);
    }

    @Test
    public void testSelectWhere() {
        SqlBuilder<UserEntity> sqlBuilder = new SqlBuilder<>(UserEntity.class);
        String sql = sqlBuilder.select().where(o -> o.and(UserEntity::getUserName, "11").and(UserEntity::getPassword, "1234")).buildSql();
        System.out.println(sql);
    }

    @Test
    public void testSelectWhereGroupByOrderBy() {
        SqlBuilder<UserEntity> sqlBuilder = new SqlBuilder<>(UserEntity.class);
        String sql = sqlBuilder.select().where(o -> o.and(UserEntity::getUserName, "11").and(UserEntity::getPassword, "1234")).groupBy(o -> o.groupBy(UserEntity::getUserName)).orderBy(o -> o.asc(UserEntity::getUserName)).buildSql();
        System.out.println(sql);
    }

    @Test
    public void testSelectLeftJoin() {
        SqlBuilder<UserEntity> sqlBuilder = new SqlBuilder<>(UserEntity.class);
        String sql = sqlBuilder.select().leftJoin(o -> o.eq(UserRoleEntity.class, UserEntity::getId, UserRoleEntity::getUserId))
                .innerJoin(o -> o.eq(UserRoleEntity.class, RoleEntity.class, UserRoleEntity::getRoleId, RoleEntity::getId))
                .where(o -> o.and(UserEntity::getUserName, "11").and(UserRoleEntity.class, UserRoleEntity::getRoleId, "1").or().eq(UserEntity::getUserName, "222").and(UserRoleEntity.class, UserRoleEntity::getRoleId, "2").end()
                ).buildSql();
        System.out.println(sql);
    }

}
