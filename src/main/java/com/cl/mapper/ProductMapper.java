package com.cl.mapper;


import com.cl.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */

@Mapper
public interface ProductMapper {
    /**
     * 添加
     * @param product
     */
    void add(Product product);


    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @param queryString
     * @return
     */

    List<Product> selectByCondition(@Param("start") int start, @Param("pageSize") int pageSize, @Param("queryString") String queryString);
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Product findById(@Param("id") Integer id);

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
    long findProductCount(@Param("queryString") String queryString);
}
