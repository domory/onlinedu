package com.wh.edu.edustatic.client;


import com.wh.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("educenter")
@Component

public interface Ucenter {

    @GetMapping("/eduucenter/ucentermember/registcount/{day}")
    public R getRegisterCount(@PathVariable("day") String day);
}
