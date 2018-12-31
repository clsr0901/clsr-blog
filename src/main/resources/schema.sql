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

CREATE TABLE blog.t_message (
  id int(11) NOT NULL AUTO_INCREMENT,
  destUserId int(11) NOT NULL COMMENT '目标人id',
  sourceUserId int(11) NOT NULL COMMENT '留言人id',
  message text NOT NULL COMMENT '留言信息',
  createTime datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 27
  AVG_ROW_LENGTH = 655
  CHARACTER SET utf8
  COLLATE utf8_general_ci
  COMMENT = '留言表';

CREATE TABLE blog.t_comment (
  id int(11) NOT NULL AUTO_INCREMENT,
  blogId int(11) NOT NULL COMMENT '博客id',
  content text NOT NULL COMMENT '评论内容',
  action int(2) NOT NULL DEFAULT 0 COMMENT '0 评论， 1 回复',
  sourceUserId int(11) NOT NULL COMMENT '评论人id',
  destUserId int(11) DEFAULT NULL COMMENT '目标人id，回复才有',
  createtime datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 1
  CHARACTER SET utf8
  COLLATE utf8_general_ci
  COMMENT = '博客评论表';

CREATE TABLE blog.t_source (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL DEFAULT '' COMMENT '文件名',
  uid varchar(32) NOT NULL DEFAULT '' COMMENT '文件md5值',
  url varchar(100) NOT NULL DEFAULT '' COMMENT '文件路径',
  createtime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  userid int(11) NOT NULL COMMENT '创建人id',
  length int(11) NOT NULL COMMENT '文件大小',
  mime varchar(20) NOT NULL COMMENT '文件mime',
  type int(2) NOT NULL DEFAULT 1 COMMENT '文件类型： 0 文本txt， 1 图片audio, 2 音频audio，3 视频video, 4 其他文件zip',
  PRIMARY KEY (id),
  UNIQUE INDEX md5 (uid)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 40
  AVG_ROW_LENGTH = 3276
  CHARACTER SET utf8
  COLLATE utf8_general_ci
  COMMENT = '资源文件表';