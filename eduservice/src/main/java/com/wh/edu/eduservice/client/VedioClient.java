package com.wh.edu.eduservice.client;


import com.wh.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient("vedioservice")
@Component
public interface VedioClient {

    @DeleteMapping("/vedioservice/deleteVedio/{vedioId}")
    public R deleteAliYun(@PathVariable("vedioId") String vedioId);

    @DeleteMapping("/vedioservice/deleteMoreVedio")
    public R deleteMoreVedio(@RequestParam("vedioList")List<String> vedioList);
}
