package com.wh.edu.eduservice.controller;


import com.aliyun.oss.OSSClient;
import com.wh.edu.common.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

//通过阿里云os上传图片
@RestController
@RequestMapping("/eduservice/oss")
@CrossOrigin
public class upLoadFile {


    @PostMapping("/fileLoad")
    public R uploadFile(@RequestParam("file") MultipartFile file,
                         @RequestParam(value = "host",required = false) String host){
        String endPoint ="oss-cn-beijing.aliyuncs.com";

        String accessKeyId = "LTAI4Ff45wKLpz9d9ZjLhhPM";

        String accessKeySecret = "T4IxASQMAsLlpZfb49OIK32hIpaKX0";
        String bucketName ="domory";


        try{
            String filename = file.getOriginalFilename();
            //在文件名之前加上uuid保证文件名不重复
            filename = UUID.randomUUID().toString() +filename;
            //防止数据过多 可以根据时间进行划分文件夹
//            String time = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//            filename =time +"/"+filename;
            //为讲师头像和课程封面设计不同的存储路径
            if(!StringUtils.isEmpty(host)){
                filename=host+"/"+filename;
            }else {
                filename="headPhoto/"+filename;
            }
            InputStream inputStream = file.getInputStream();

            OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);

            ossClient.putObject(bucketName,filename,inputStream);
            ossClient.shutdown();

            String  path="http://"+bucketName+"."+endPoint+"/"+filename;
            return R.ok().data("path",path);
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
