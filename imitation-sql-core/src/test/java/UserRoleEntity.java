import com.imitationsql.core.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/13 10:42
 */
@Setter
@Getter
@TableName("t_user_role")
public class UserRoleEntity {

    private String id;

    private String roleId;

    private String userId;
}
