package com.cl.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */

@Data
public class Product implements Serializable {
    private Integer id;
    private String productCode;
    private String productName;
    private double productPrice;
    private String productRemark;
    private Date createTime;

}
