package com.wh.edu.eduservice.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class courseInfo {
    private  String id;
    private String title;
    private String cover;
    private BigDecimal price;
    private String description;
    private String teacherName ;//讲师名称
    private String levelone;//一级分类
    private String leveltwo;//二级分类
}
