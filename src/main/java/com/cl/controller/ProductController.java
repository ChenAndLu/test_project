package com.cl.controller;

import com.cl.constant.MessageConstant;
import com.cl.entity.PageResult;
import com.cl.entity.QueryPageBean;
import com.cl.entity.Result;
import com.cl.pojo.Product;
import com.cl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @RequestMapping("/add")
    public Result add(@RequestBody Product product){
        try{
            product.setCreateTime(new Date());
            productService.add(product);
            return  Result.builder().flag(true).message(MessageConstant.PRODUCT_ADD_SUCCESS).build();
        }catch (Exception e){
            e.printStackTrace();
            return  Result.builder().flag(false).message(MessageConstant.PRODUCT_ADD_FAIL).build();
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<Product> productList = productService.findAll();
            return new Result(true, MessageConstant.PRODUCT_FINDALL_SUCCESS,productList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.builder().flag(false).message(MessageConstant.PRODUCT_FINDALL_FAIL).build();
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Product product){
        try{
            productService.edit(product);
            return Result.builder().flag(true).message(MessageConstant.PRODUCT_EDIT_SUCCESS).build();
        }catch (Exception e){
            e.printStackTrace();
            return Result.builder().flag(false).message(MessageConstant.PRODUCT_EDIT_FAIL).build();
        }
    }

    @RequestMapping("/delete")
    public Result deleteById(Integer id){
        try{
            productService.deleteById(id);
            return Result.builder().flag(true).message(MessageConstant.PRODUCT_DELETE_SUCCESS).build();
        }catch (Exception e){
            e.printStackTrace();
            return Result.builder().flag(false).message(MessageConstant.PRODUCT_DELETE_FAIL).build();
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = productService.pageQuery(queryPageBean);
        return pageResult;
    }
}
