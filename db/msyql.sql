-- 菜单
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) COMMENT '菜单名称',
  `url` varchar(200) COMMENT '菜单URL',
  `perms` varchar(500) COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) COMMENT '菜单图标',
  `order_num` int COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='菜单管理';

-- 系统用户
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) COMMENT '密码',
  `salt` varchar(20) COMMENT '盐',
  `email` varchar(100) COMMENT '邮箱',
  `mobile` varchar(100) COMMENT '手机号',
  `status` tinyint COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) COMMENT '创建者ID',
  `create_time` datetime COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='系统用户';

-- 系统用户Token
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(512) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='系统用户Token';

-- 角色
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) COMMENT '角色名称',
  `remark` varchar(100) COMMENT '备注',
  `create_user_id` bigint(20) COMMENT '创建者ID',
  `create_time` datetime COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='角色';

-- 用户与角色对应关系
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint COMMENT '用户ID',
  `role_id` bigint COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='用户与角色对应关系';

-- 角色与菜单对应关系
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint COMMENT '角色ID',
  `menu_id` bigint COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='角色与菜单对应关系';

-- 初始化数据
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`) VALUES (1, 'admin', 'f345e11cea21b5c28ce30baeaf6188c8', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`) VALUES (2, 'jack', 'f345e11cea21b5c28ce30baeaf6188c8', 'YzcmCZNvbXocrsz9dm8e', 'jack@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11');

INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (2, 1, '策略管理', 'strategy/manager', NULL, 1, 'strategy', 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (3, 2, '查看', NULL, 'sys:strategy:list,sys:strategy:info', 2, NULL, 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (4, 2, '新增', NULL, 'sys:strategy:save', 2, NULL, 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (5, 2, '修改', NULL, 'sys:strategy:update', 2, NULL, 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (6, 2, '删除', NULL, 'sys:strategy:delete', 2, NULL, 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (7, 2, '暂停', NULL, 'sys:strategy:pause', 2, NULL, 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (8, 2, '恢复', NULL, 'sys:strategy:resume', 2, NULL, 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (9, 2, '立即执行', NULL, 'sys:strategy:run', 2, NULL, 0);

INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`) VALUES (1, 'admin', 'admin', NULL, '2021-08-07 16:28:09');
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`) VALUES (2, 'role1', 'role1', NULL, '2021-08-07 16:28:24');
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_user_id`, `create_time`) VALUES (3, 'role2', 'role2', NULL, '2021-08-07 16:28:34');

INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (1, 1, 1);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES (2, 2, 3);

INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (1, 1, 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (2, 1, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (3, 1, 3);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (4, 1, 4);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (5, 1, 5);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (6, 1, 6);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (7, 1, 7);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (8, 1, 8);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (9, 1, 9);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (10, 2, 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (11, 2, 2);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (12, 2, 3);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (13, 3, 4);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (14, 3, 5);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (15, 3, 6);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (16, 3, 7);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (17, 3, 8);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES (18, 3, 9);
