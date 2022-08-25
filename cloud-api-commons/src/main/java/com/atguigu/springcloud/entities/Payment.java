package  com.atguigu.springcloud.entities;;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private Long  id;   //由于我们在数据库中的id字段类型的bigint，所以在这里写成Long
    private String serial;
}
