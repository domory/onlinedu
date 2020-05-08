package com.wh.edu.eduservice.entity.dto;


import com.wh.edu.eduservice.entity.EduVideo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class chapterDto {

    private String id;
    private String title;
    private List<EduVideo> children=new ArrayList<>();
}
