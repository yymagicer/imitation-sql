import com.imitationsql.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/13 10:43
 */
@Setter
@Getter
@TableName("t_role")
public class RoleEntity {

    private String id;

    private String roleName;
}
