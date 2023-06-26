package com.cl.mapper;

import com.cl.pojo.Product;
import com.cl.pojo.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */
@Mapper
public interface StoreMapper {
    void add(Store store);

    List<Store> findAll();

    void edit(Store store);

    void delete(@Param("id") Integer id);

    Store findById(@Param("id") Integer id);

    void inputStore(@Param("storeId") Integer storeId, @Param("storeCode") String storeCode, @Param("storeName") String storeName, @Param("productId") Integer productId, @Param("productCode") String productCode, @Param("productName") String productName, @Param("nums") Integer nums, @Param("price") double price,@Param("money") Double money, @Param("remark") String remark, @Param("date") Date date);

    void outputStore(@Param("storeId") Integer storeId, @Param("storeCode") String storeCode, @Param("storeName") String storeName, @Param("productId") Integer productId, @Param("productCode") String productCode, @Param("productName") String productName, @Param("nums") Integer nums, @Param("price") double price, @Param("money") double money, @Param("remark") String remark, @Param("date") Date date);

    Integer innputNumsByStoreIDAndProductID(@Param("storeId") Integer storeId, @Param("productId") Integer productId);

    Integer outputNumsByStoreIDAndProductID(@Param("storeId") Integer storeId, @Param("productId") Integer productId);


    List<Map<String,Object>> selectByCondition(@Param("start") int start, @Param("pageSize") int pageSize, @Param("queryString") String queryString);

    long findProductCount(@Param("queryString") String queryString);

    Map<String, Object> checkStorck(@Param("productId") Integer productId);
}
