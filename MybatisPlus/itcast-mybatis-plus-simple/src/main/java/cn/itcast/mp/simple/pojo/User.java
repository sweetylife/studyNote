package cn.itcast.mp.simple.pojo;

/**
 * @projectName: MybatisPlus
 * @package: cn.itcast.mp.simple.pojo
 * @className: User
 * @author: tian
 * @description: TODO
 * @date: 2021/12/20 10:55
 * @version: 1.0
 */
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User {
    private Long id;
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private String email;
}
