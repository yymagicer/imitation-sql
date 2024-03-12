import com.imitationsql.SqlBuilder;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 16:51
 */
public class Test {

    public static void main(String[] args) {
        SqlBuilder<UserEntity> sqlBuilder = new SqlBuilder<>(UserEntity.class);
        String sql = sqlBuilder.select().where(o -> o.and(UserEntity::getUserName, "11").and(UserEntity::getPassword, "1234")).buildSql();
        System.out.println(sql);
    }
}
