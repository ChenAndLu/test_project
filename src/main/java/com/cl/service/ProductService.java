package com.cl.service;

import com.cl.entity.PageResult;
import com.cl.entity.QueryPageBean;
import com.cl.pojo.Product;

import java.util.List;

/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */
public interface ProductService {

    /**
     * 添加
     * @param product
     */
    void add(Product product);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult pageQuery(QueryPageBean queryPageBean);


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Product findById(Integer id);

    /**
     * 编辑
     * @param product
     */
    void edit(Product product);

    /**
     * 根据主键删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Product> findAll();


    /**
     * 查询数量
     * @return
     */
    long findProductCount(String queryString);
}
