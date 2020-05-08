package com.wh.edu.eduservice.service;

import com.wh.edu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wh.edu.eduservice.entity.dto.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author wh
 * @since 2020-04-17
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<String> uploadFile(MultipartFile file);


    List<SubjectNestedVo> nestedList();

    boolean deleteNode(String id);

    boolean addOneNode(EduSubject eduSubject);

    boolean addTwoNode(EduSubject eduSubject);
}
