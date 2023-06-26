package com.cl.controller;

import com.cl.constant.MessageConstant;
import com.cl.entity.PageResult;
import com.cl.entity.QueryPageBean;
import com.cl.entity.Result;
import com.cl.pojo.Product;
import com.cl.pojo.Store;
import com.cl.service.ProductService;
import com.cl.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */

@RestController()
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<Store> storeList = storeService.findAll();
            return new Result(true, MessageConstant.STORE_FINDALL_SUCCESS,storeList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.builder().flag(false).message(MessageConstant.STORE_FINDALL_FAIL).build();
        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Store store){
        try {
            storeService.add(store);
            return Result.builder().flag(true).message(MessageConstant.STORE_ADD_SUCCESS).build();
        }catch (Exception e){
            e.printStackTrace();
            return Result.builder().flag(false).message(MessageConstant.STORE_ADD_FAIL).build();
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Store store){
        try {
            storeService.edit(store);
            return Result.builder().flag(true).message(MessageConstant.STORE_EDIT_SUCCESS).build();
        }catch (Exception e){
            e.printStackTrace();
            return Result.builder().flag(false).message(MessageConstant.STORE_EDIT_FAIL).build();
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            storeService.delete(id);
            return Result.builder().flag(true).message(MessageConstant.STORE_DELETE_SUCCESS).build();
        }catch (Exception e){
            e.printStackTrace();
            return Result.builder().flag(false).message(MessageConstant.STORE_DELETE_FAIL).build();
        }
    }

    @RequestMapping("/findById")
    public Result add(Integer id){
        try {
            Store store = storeService.findById(id);
            return Result.builder().flag(true).message(MessageConstant.STORE_FINDBYID_SUCCESS).data(store).build();
        }catch (Exception e){
            e.printStackTrace();
            return Result.builder().flag(false).message(MessageConstant.STORE_FINDBYID_FAIL).build();
        }
    }

    @RequestMapping("inputStore")
    public Result inputStore(Integer storeId,Integer productId, Integer nums,double price,String remark){
        try{
            Store store = storeService.findById(storeId);
            Product product = productService.findById(productId);
            //入库
            Map<String, Object> stocks = storeService.inputStore(store, product, nums, price, remark, new Date());
            return Result.builder().flag(true).message(MessageConstant.STORE_INPUT_SUCCESS).data(stocks).build();
        }catch (Exception e){
            e.printStackTrace();
            return Result.builder().flag(true).message(MessageConstant.STORE_INPUT_FAIL).build();
        }
    }

    @RequestMapping("outputStore")
    public Result outputStore(Integer storeId,Integer productId, Integer nums,double price,String remark){
        try{
            Store store = storeService.findById(storeId);
            Product product = productService.findById(productId);
            //出库
            Map<String, Object> storcks = storeService.outputStore(store, product, nums, price, remark, new Date());
            return Result.builder().flag(true).message(MessageConstant.STORE_INPUT_SUCCESS).data(storcks).build();
        }catch (Exception e){
            e.printStackTrace();
            return Result.builder().flag(true).message(MessageConstant.STORE_INPUT_FAIL).build();
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = storeService.pageQuery(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/checkStorck")
    public Result checkStorck(Integer productId) {
        try {
            Map<String, Object> stocks = storeService.checkStorck(productId);
            return Result.builder().flag(true).message(MessageConstant.STORE_INPUT_SUCCESS).data(stocks).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.builder().flag(true).message(MessageConstant.STORE_INPUT_FAIL).build();
        }
    }
}
