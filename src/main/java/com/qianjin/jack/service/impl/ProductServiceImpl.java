package com.qianjin.jack.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianjin.jack.domain.DTO.Search;
import com.qianjin.jack.domain.PageResult;
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
        Page<JackBatch> page = new Page<>(search.getPage(),search.getPageSize());
        IPage<JackBatch> batches = batchMapper.selectPage(page,wrapper);
        return PageResult.of(batches);
    }

    @Override
    public PageResult productList(Search search) {
        QueryWrapper<JackProduct> wrapper = SqlUtil.getWrapper(search,JackProduct.class);
        Page<JackProduct> page = new Page<>(search.getPage(),search.getPageSize());
        IPage<JackProduct> batches = productMapper.selectPage(page,wrapper);
        return PageResult.of(batches);
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
            QRCodeUtil.encode(batch.getUrl()+"/product/detail/product?unid="+productUnid,"",imagePath+"/dimensionCode"+imageName+".jpg",false);
            product.setQrCode(imageName);
            productMapper.insert(product);
        }
        return "ok";
    }

    @Override
    public String sellBatch(String unid) {
        LocalDateTime localDateTime = LocalDateTime.now();
        UpdateWrapper<JackProduct> wrapper = new UpdateWrapper<>();
        wrapper.eq("batch_unid",unid);
        JackProduct product = new JackProduct();
        product.setCreateTime(localDateTime);
        product.setArriveTime(localDateTime);
        productMapper.update(product,wrapper);
        return "ok";
    }

    @Override
    public JackProduct detailProduct(String unid) {
        QueryWrapper<JackProduct> wrapper = new QueryWrapper<>();
        wrapper.eq("product_code",unid);
        JackProduct product = productMapper.selectOne(wrapper);
        return product;
    }
}
