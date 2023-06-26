package com.cl.service.impl;

import com.cl.entity.PageResult;
import com.cl.entity.QueryPageBean;
import com.cl.mapper.ProductMapper;
import com.cl.pojo.Product;
import com.cl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void add(Product product) {
        productMapper.add(product);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        long counts = productMapper.findProductCount(queryPageBean.getQueryString());
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
        List<Product> page = productMapper.selectByCondition(start,pageSize,queryPageBean.getQueryString());
        return new PageResult(counts,pageSize,currentPage,maxPageNum,page);
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }

    @Override
    public void edit(Product product) {
        productMapper.edit(product);
    }

    @Override
    public void deleteById(Integer id) {
        productMapper.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = productMapper.findAll();
        return productList;
    }

    @Override
    public long findProductCount(String queryString) {
        return productMapper.findProductCount(queryString);
    }
}
