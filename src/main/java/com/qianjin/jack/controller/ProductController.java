package com.qianjin.jack.controller;

import com.qianjin.jack.domain.DTO.Search;
import com.qianjin.jack.domain.PageResult;
import com.qianjin.jack.domain.Result;
import com.qianjin.jack.domain.dao.JackBatch;
import com.qianjin.jack.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 批次列表
     */
    @PostMapping("/batch/list")
    public PageResult batchList(Search search){
        return productService.batchList(search);
    }

    /**
     * 产品列表,根据批次
     */
    @PostMapping("/product/list")
    public PageResult productList(Search search){
        return productService.productList(search);
    }

    /**
     * 1、生成二维码数量，每个对应一个产品
     * 2、插入数据库，定义为原始状态
     * @return
     */
    @PostMapping("/insert/product")
    public Result insertProduct(JackBatch jackBatch) throws Exception {
        return new Result(productService.insertProduct(jackBatch));
    }

    /**
     * 1、触发函数，批量触发，加上到货时间
     */
    @PostMapping("/sell/batch")
    public Result sellBatch(String unid){
        return new Result(productService.sellBatch(unid));
    }

    /**
     * 删除商品,删除批次，或者删除其中一个
     */
    @PostMapping("/delete/batch")
    public Result deleteProduct(String unid,Integer id){
        return null;
    }

    /**
     * 扫码查看，指定产品信息    或者在指定页面输入序列号查看
     * 返回出售日期
     */
    @GetMapping("/detail/product")
    public Result detailProduct(String unid){
        return new Result(productService.detailProduct(unid));
    }
}
