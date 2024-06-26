package com.demo.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.springsecurity.domain.entity.UmsSysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-06 22:08:41
 */
@Mapper
public interface UmsSysUserMapper extends BaseMapper<UmsSysUser> {
    UmsSysUser selectUserByUsername(@Param("username") String username);
}
