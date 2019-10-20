package com.qianjin.jack.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qianjin.jack.domain.dao.JackBatch;
import lombok.Data;

@Data
public class PageResult<R> {
    private Object data;
    private Long page;
    private Long pageSize;
    private Long total;
    private Long a;
    private String result;

    public static PageResult of(IPage batches) {
        PageResult pageResult = new PageResult();
        if(batches != null && batches.getSize() != 0){
            pageResult.data = batches.getRecords();
            pageResult.page = batches.getCurrent();
            pageResult.pageSize = batches.getSize();
            pageResult.total = batches.getTotal();
            pageResult.a = batches.getPages();
            pageResult.result = "1";
        }else{
            pageResult.result = "-1";
        }
        return pageResult;
    }
}
