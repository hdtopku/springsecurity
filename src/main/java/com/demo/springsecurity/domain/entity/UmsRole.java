package com.demo.springsecurity.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-07 16:58:48
 */
@Data
@TableName("ums_role")
public class UmsRole implements java.io.Serializable {
    @TableId
    private Long roleId;
    private String roleLabel;
    private String roleName;
    private Integer sort;
    private Integer status;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
