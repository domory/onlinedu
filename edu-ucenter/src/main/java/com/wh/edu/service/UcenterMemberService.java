package com.wh.edu.service;

import com.wh.edu.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author wh
 * @since 2020-04-28
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    int getMemberCountByDay(String day);
}
