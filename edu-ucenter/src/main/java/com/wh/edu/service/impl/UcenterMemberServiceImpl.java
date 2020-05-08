package com.wh.edu.service.impl;

import com.wh.edu.mapper.UcenterMemberMapper;
import com.wh.edu.service.UcenterMemberService;
import com.wh.edu.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author wh
 * @since 2020-04-28
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public int getMemberCountByDay(String day) {

        return  baseMapper.getMemberCountByDay(day);
    }
}
