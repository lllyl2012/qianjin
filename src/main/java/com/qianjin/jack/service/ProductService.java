package com.qianjin.jack.service;

import com.qianjin.jack.domain.DTO.Search;
import com.qianjin.jack.domain.PageResult;
import com.qianjin.jack.domain.Result;
import com.qianjin.jack.domain.dao.JackBatch;
import com.qianjin.jack.domain.dao.JackProduct;

import java.util.List;

public interface ProductService {
    PageResult batchList(Search search);

    Result productList(Search search);

    String insertProduct(JackBatch num) throws Exception;

    JackProduct detailProduct(String id);

    String sellBatch(String unid);

    Result deleteBatch(String batchId);
}
