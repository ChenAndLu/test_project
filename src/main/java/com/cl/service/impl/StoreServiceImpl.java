package com.cl.service.impl;

import com.cl.entity.PageResult;
import com.cl.entity.QueryPageBean;
import com.cl.mapper.StoreMapper;
import com.cl.pojo.Product;
import com.cl.pojo.Store;
import com.cl.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public void add(Store store) {
        storeMapper.add(store);
    }

    @Override
    public List<Store> findAll() {
        return storeMapper.findAll();
    }

    @Override
    public void edit(Store store) {
        storeMapper.edit(store);
    }

    @Override
    public void delete(Integer id) {
        storeMapper.delete(id);
    }

    @Override
    public Store findById(Integer id) {
        return storeMapper.findById(id);
    }

    @Override
    public Map<String, Object> inputStore(Store store, Product product, Integer nums, double price,String remark, Date date) {
        //金额计算
        BigDecimal bigDecimalPrice = new BigDecimal(price);
        BigDecimal bigDecimalNums = new BigDecimal(nums);
        BigDecimal bigDecimalMonney = bigDecimalPrice.multiply(bigDecimalNums);
        double money = bigDecimalMonney.setScale(2, RoundingMode.HALF_UP).doubleValue();
        storeMapper.inputStore(
                store.getId(),store.getStoreCode(),store.getStoreName(),
                product.getId(),product.getProductCode(),product.getProductName(),
                nums,price,money,remark,date
        );
        Map<String, Object> storcks = storcks(store, product);
        return storcks;
    }

    @Override
    public Map<String, Object>  outputStore(Store store, Product product, Integer nums, double price, String remark, Date date) {
        //金额计算
        BigDecimal bigDecimalPrice = new BigDecimal(price);
        BigDecimal bigDecimalNums = new BigDecimal(nums);
        BigDecimal bigDecimalMonney = bigDecimalPrice.multiply(bigDecimalNums);
        double money = bigDecimalMonney.setScale(2, RoundingMode.HALF_UP).doubleValue();
        storeMapper.outputStore(
                store.getId(),store.getStoreCode(),store.getStoreName(),
                product.getId(),product.getProductCode(),product.getProductName(),
                nums,price,money,remark,date
        );
        Map<String, Object> storcks = storcks(store, product);
        return storcks;
    }

    public Map<String,Object> storcks(Store store, Product product){
        Integer inputNums = storeMapper.innputNumsByStoreIDAndProductID(store.getId(),product.getId());
        Integer outputNums = storeMapper.outputNumsByStoreIDAndProductID(store.getId(),product.getId());
        HashMap<String, Object> result = new HashMap<>();
        Integer storcks = inputNums- outputNums;
        result.put("store",store);
        result.put("product",product);
        result.put("storcks",storcks);
        return result;
    }


    public PageResult pageQuery(QueryPageBean queryPageBean) {
        long counts = storeMapper.findProductCount(queryPageBean.getQueryString());
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        if (currentPage == null || currentPage==0){
            currentPage = 1;
        }
        if (pageSize == null || pageSize ==0){
            pageSize = 10;
        }
        int maxPageNum = (int) (counts % pageSize > 0?(counts / pageSize)+1:counts / pageSize);
        int start = (currentPage-1)*pageSize;
        List<Map<String,Object>> page = storeMapper.selectByCondition(start,pageSize,queryPageBean.getQueryString());
        return new PageResult(counts,pageSize,currentPage,maxPageNum,page);
    }

    @Override
    public Map<String, Object> checkStorck(Integer productId) {
        return storeMapper.checkStorck(productId);
    }
}
