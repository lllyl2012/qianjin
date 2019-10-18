package com.qianjin.jack.domain.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author volume
 * @date 2019/10/18 11:09
 */
@Data
@TableName("user_info")
public class UserInfo extends CommonDao{
    private Integer id;
    private String username;
    private String phone;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
