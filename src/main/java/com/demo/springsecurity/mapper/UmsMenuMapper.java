package com.demo.springsecurity.mapper;

import com.demo.springsecurity.domain.entity.UmsMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-07 17:43:44
 */
@Mapper
public interface UmsMenuMapper {
    Set<UmsMenu> selectMenuByRoleIds(@Param("roleIds") Set<Long> roleIds);
}
