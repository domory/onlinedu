package com.wh.edu.mapper;

import com.wh.edu.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author wh
 * @since 2020-04-28
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    int getMemberCountByDay(@Param("day") String day);
}
