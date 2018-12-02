CREATE TABLE IF NOT EXISTS blog.t_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  avatar varchar(200) NOT NULL DEFAULT '' COMMENT '用户头像',
  email varchar(50) DEFAULT '' COMMENT '用户邮箱',
  password varchar(50) NOT NULL COMMENT '密码',
  username varchar(100) NOT NULL COMMENT '用户名',
  phone varchar(11) DEFAULT '' COMMENT '用户手机号',
  instruction varchar(255) DEFAULT '' COMMENT '一句话自我介绍',
  create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE INDEX phone (phone),
  UNIQUE INDEX UK_ob8kqyqqgmefl0aco34akdtpe (email),
  UNIQUE INDEX UK_sb8bbouer5wak8vyiiy4pf2bx (username)
)
ENGINE = MYISAM
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 52
CHARACTER SET utf8
COLLATE utf8_general_ci
COMMENT = '用户表';

CREATE TABLE IF NOT EXISTS blog.t_blog (
  id int(11) NOT NULL AUTO_INCREMENT,
  userId int(11) NOT NULL COMMENT '用户ID',
  content blob NOT NULL COMMENT '博客内容',
  hit int(11) NOT NULL DEFAULT 0 COMMENT '点赞数',
  view int(11) NOT NULL DEFAULT 0 COMMENT '观看数',
  sticky tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否置顶',
  highlight tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否加精',
  createtime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  INDEX userId (userId)
)
  ENGINE = MYISAM
  AUTO_INCREMENT = 3
  AVG_ROW_LENGTH = 52
  CHARACTER SET utf8
  COLLATE utf8_general_ci
  COMMENT = '博客表';