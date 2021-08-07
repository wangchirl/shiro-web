package com.shadow.shiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

    import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 角色与菜单对应关系
 *
 * @author shadow
 * @email shadow@gmail.com
 * @date 2021-08-07 17:05:36
 */
@Data
@TableName("sys_role_menu")
public class RoleMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */
                @TableId(type = IdType.AUTO)
            private Long id;
            /**
         * 角色ID
         */
            private Long roleId;
            /**
         * 菜单ID
         */
            private Long menuId;
    
}
