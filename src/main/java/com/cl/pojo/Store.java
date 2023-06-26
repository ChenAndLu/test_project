package com.cl.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store implements Serializable {
    private Integer id;
    private String storeCode;
    private String storeName;
    private String remake;
}
