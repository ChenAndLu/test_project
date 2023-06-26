package com.cl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ChenLu
 * @date: Created in 2023/6/26
 * @description:
 * @version:1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> implements Serializable {
    private Long total;//总记录数
    private int pageSize;   //每页显示数量
    private int currentPage;  //当前页数
    private int maxPageNum;     //最大页数
    private List<T> rows;//当前页结果
}
