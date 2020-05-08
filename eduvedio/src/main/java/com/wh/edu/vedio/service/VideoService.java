package com.wh.edu.vedio.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    String uploadvedio(MultipartFile file);

    boolean deleteVedioById(String vedioId);

    boolean deleteMoreVedio(List<String> vediolist);
}
