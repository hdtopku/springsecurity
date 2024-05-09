package com.demo.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.springsecurity.domain.entity.PersistentLogins;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-09 11:07:11
 */
@Mapper
public interface PersistentLoginsMapper extends BaseMapper<PersistentLogins> {
}
