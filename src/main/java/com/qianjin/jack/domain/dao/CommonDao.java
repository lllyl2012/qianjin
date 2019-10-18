package com.qianjin.jack.domain.dao;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author volume
 * @date 2019/10/18 11:12
 */
public class CommonDao {
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updateTime;
}
