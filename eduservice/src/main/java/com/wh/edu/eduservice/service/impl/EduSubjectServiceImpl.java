package com.wh.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wh.edu.eduservice.eduConfig.EduException;
import com.wh.edu.eduservice.entity.EduSubject;
import com.wh.edu.eduservice.entity.dto.SubjectNestedVo;
import com.wh.edu.eduservice.entity.dto.SubjectVo;
import com.wh.edu.eduservice.mapper.EduSubjectMapper;
import com.wh.edu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author wh
 * @since 2020-04-17
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


//excel导入
    @Override
    public List<String> uploadFile(MultipartFile file) {

        try {
            List<String> msg=new ArrayList<>();


            InputStream inputStream = file.getInputStream();

            //得到workbook
            Workbook workbook = new XSSFWorkbook(inputStream);

            //获得sheet
            Sheet sheet = workbook.getSheetAt(0);

            //获得文件一共多少行
            int lastRowNum = sheet.getLastRowNum();

            //遍历每一行
            for (int i=1;i<=lastRowNum;i++){
                Row row = sheet.getRow(i);
                //判断某一行是否为空
                if(row == null){
                    msg.add("文件有空行");
                    continue;
                }
                //行里面有数据，获得第一列
                Cell cellone = row.getCell(0);
                //如果第一列为空
                if(cellone == null){
                    msg.add("第"+i+"行为空");
                    continue;
                }

                //第一列的值不为空，则插入数据库
                //获得第一列的值  判断数据库中是否已经存在一级分类
                 String CellValue = cellone.getStringCellValue();
                //调用函数
                String parentId="";
                EduSubject eduSubjectExit = cellOneExit(CellValue);
                if(eduSubjectExit==null){
                    //数据库没有，插入数据库
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(CellValue);
                    eduSubject.setParentId("0");
                    eduSubject.setSort(0);
                    baseMapper.insert(eduSubject);
                    parentId=eduSubject.getId();
                }else {//如果有就拿到id
                    parentId=eduSubjectExit.getId();
                }

                Cell celltwo = row.getCell(1);
                if(celltwo ==null){
                    msg.add("第"+i+"行为空");
                    continue;
                }
                String celltwoValue = celltwo.getStringCellValue();
                //查看二级分类在数据库是否存在
                EduSubject eduSubject = celltwoExit(celltwoValue, parentId);
                if(eduSubject==null){
                    //没有就插入
                    EduSubject eduSubject1 = new EduSubject();
                    eduSubject1.setParentId(parentId);
                    eduSubject1.setTitle(celltwoValue);
                    eduSubject1.setSort(0);
                    baseMapper.insert(eduSubject1);
                }

            }
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EduException(20001,"导入失败");
        }



    }


//树形结构
    @Override
    public List<SubjectNestedVo> nestedList() {
        //最终要的到的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();
        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);
        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);

        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = subjects.get(i);
            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject, subjectNestedVo);
            subjectNestedVoArrayList.add(subjectNestedVo);

            //填充二级分类vo数据
            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {
                EduSubject subSubject = subSubjects.get(j);
                if(subject.getId().equals(subSubject.getParentId())){
                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    //将 subSubject里的值赋值给 subjectVo
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    subjectVoArrayList.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);
        }
        return subjectNestedVoArrayList;
    }


    //删除分类
    @Override
    public boolean deleteNode(String id) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(queryWrapper);
        //如果该分类下有二级分类
        if(count>0){
            return false;
        }else {
            int i = baseMapper.deleteById(id);
            return i>0;
        }
    }

    //添加一级分类
    @Override
    public boolean addOneNode(EduSubject eduSubject) {
        EduSubject subject = cellOneExit(eduSubject.getTitle());
        if(subject ==null){//没有重复
            eduSubject.setParentId("0");
            int i = baseMapper.insert(eduSubject);
            return i>0;
        }else {
           return false;
        }

    }

    //添加二级分类
    @Override
    public boolean addTwoNode(EduSubject eduSubject) {
        EduSubject subject = celltwoExit(eduSubject.getTitle(), eduSubject.getParentId());
        if(subject ==null){//没有重复
           // eduSubject.setParentId("0");
            int i = baseMapper.insert(eduSubject);
            return i>0;
        }else {
            return false;
        }
    }


    //查看一级分类是否存在
    public EduSubject  cellOneExit(String CellValue){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",CellValue);
        queryWrapper.eq("parent_id","0");
        EduSubject eduSubject = baseMapper.selectOne(queryWrapper);
        return eduSubject;
    }

    //查看二级分类是否存在
    public EduSubject celltwoExit(String CellValue,String parentid){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",CellValue);
        queryWrapper.eq("parent_id",parentid);
        EduSubject eduSubject = baseMapper.selectOne(queryWrapper);
        return eduSubject;
    }
}
