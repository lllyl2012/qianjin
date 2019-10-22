package com.qianjin.jack.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianjin.jack.domain.DTO.Search;
import com.qianjin.jack.domain.PageResult;
import com.qianjin.jack.domain.Result;
import com.qianjin.jack.domain.dao.JackBatch;
import com.qianjin.jack.domain.dao.JackProduct;
import com.qianjin.jack.mapper.JackBatchMapper;
import com.qianjin.jack.mapper.JackProductMapper;
import com.qianjin.jack.service.CommonService;
import com.qianjin.jack.service.ProductService;
import com.qianjin.jack.utils.QRCodeUtil;
import com.qianjin.jack.utils.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final JackBatchMapper batchMapper;

    private final JackProductMapper productMapper;

    private final CommonService commonService;

    @Autowired
    public ProductServiceImpl(JackBatchMapper batchMapper, JackProductMapper productMapper, CommonService commonService) {
        this.batchMapper = batchMapper;
        this.productMapper = productMapper;
        this.commonService = commonService;
    }

    @Override
    public PageResult batchList(Search search) {
        QueryWrapper<JackBatch> wrapper = SqlUtil.getWrapper(search,JackBatch.class);
        wrapper.eq("if_delete", "0");
        Page<JackBatch> page = new Page<>(search.getPage(),search.getPageSize());
        IPage<JackBatch> batches = batchMapper.selectPage(page,wrapper);
        return PageResult.of(batches);
    }

    @Override
    public Result productList(Search search) {
        QueryWrapper<JackProduct> wrapper = SqlUtil.getWrapper(search,JackProduct.class);
        List<JackProduct> batches = productMapper.selectList(wrapper);
        return Result.of(batches);
    }

    @Override
    public String insertProduct(JackBatch batch) throws Exception {
        String batchUnid = UUID.randomUUID().toString();
        batch.setCreateTime(LocalDateTime.now());
        batch.setUnid(batchUnid);
        batchMapper.insert(batch);

        for(int i=0;i<batch.getProductNum();i++){
            String productUnid = UUID.randomUUID().toString();
            JackProduct product = new JackProduct();
            product.setCreateTime(LocalDateTime.now());
            product.setBatchUnid(batchUnid);
            product.setProductCode(productUnid);
            product.setProductName("千斤顶");
            String imagePath = commonService.getRootPath() + "/";
            String imageName = commonService.getFileName(productUnid+".jpg");
            QRCodeUtil.encode(batch.getUrl()+"/manage/detail.html?unid="+productUnid,"",imagePath+"/dimensionCode"+imageName,false);
            product.setQrCode(imageName);
            product.setQualityLength(batch.getQualityLength());
            productMapper.insert(product);
        }
        return "ok";
    }

    @Override
    public String sellBatch(String unid) {
        UpdateWrapper<JackBatch> wrapper = new UpdateWrapper<>();
        wrapper.eq("unid",unid);
        JackBatch batch = new JackBatch();
        batch.setArriveTime(LocalDateTime.now());
        batchMapper.update(batch,wrapper);

        UpdateWrapper<JackProduct> productUpdateWrapper = new UpdateWrapper<>();
        wrapper.eq("batch_unid",unid);
        JackProduct product = new JackProduct();
        product.setArriveTime(LocalDateTime.now());
        product.setBatchUnid(unid);
        productMapper.update(product,productUpdateWrapper);
        return "ok";
    }

    @Override
    public Result deleteBatch(String batchId) {
        JackBatch batch = new JackBatch();
        batch.setIfDelete("1");

        QueryWrapper<JackBatch> wrapper = new QueryWrapper<>();
        wrapper.eq("unid",batchId);
        batchMapper.update(batch,wrapper);
        return Result.of("ok");
    }

    @Override
    public JackProduct detailProduct(String unid) {
        QueryWrapper<JackProduct> wrapper = new QueryWrapper<>();
        wrapper.eq("product_code",unid);
        JackProduct product = productMapper.selectOne(wrapper);
        return product;
    }
}
