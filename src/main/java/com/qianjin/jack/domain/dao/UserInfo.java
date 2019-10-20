package com.qianjin.jack.domain.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author volume
 * @date 2019/10/18 11:09
 */
@Data
@TableName("user_info")
public class UserInfo extends CommonDao{
    @TableId(type = IdType.AUTO)
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

    public UserInfo(Integer id, String username, String phone, String password) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
    }
}
