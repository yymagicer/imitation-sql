import com.imitationsql.core.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/12 16:53
 */
@Setter
@Getter
@TableName("t_user")
public class UserEntity {

    private String id;

    private String userName;

    private String password;
}
