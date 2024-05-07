package com.demo.springsecurity.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-07 17:01:20
 */
@Data
@TableName("ums_menu")
public class UmsMenu implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;

    private Long parentId;
    private String menuName;
    private Integer sort;
    private String perms;
    private Integer menuType;
    private String icon;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
