package com.wh.edu.eduservice.eduConfig;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduException extends RuntimeException {

    private Integer code ;
    private String message;
}
