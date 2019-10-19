package com.qianjin.jack.domain.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class JackBatch {

  private long id;
  private String unid;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;
  private String ifDelete;

  private String clientName;
  private String clientPhone;
  private String clientCompany;
  private String qualityLength;


  @TableField(exist = false)
  private Integer productNum;
  @TableField(exist = false)
  private String url;
}
