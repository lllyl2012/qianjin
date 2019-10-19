package com.qianjin.jack.domain.DTO;

import lombok.Data;

@Data
public class Search {
    private String search;
    private String keyword;
    private Integer page;
    private Integer pageSize;
}
