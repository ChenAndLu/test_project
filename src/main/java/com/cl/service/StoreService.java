package com.cl.service;

import com.cl.entity.PageResult;
import com.cl.entity.QueryPageBean;
import com.cl.pojo.Product;
import com.cl.pojo.Store;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */
public interface StoreService {
    public void add(Store store);
    public List<Store> findAll();
    public void edit(Store store);
    public void delete(Integer id);
    public Store findById(Integer id);

    Map<String, Object>  inputStore(Store store, Product product, Integer nums, double price ,String remake, Date date);

    Map<String, Object>  outputStore(Store store, Product product, Integer nums, double price, String remark, Date date);

    Map<String,Object> storcks(Store store, Product product);

    PageResult pageQuery(QueryPageBean queryPageBean);

    Map<String, Object> checkStorck(Integer productId);
}
