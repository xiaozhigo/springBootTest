/**
    监管对象SQL
 */

 SET NAMES utf8;
 # 取消外键约束
 SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tb_objectinfo`;
CREATE TABLE `tb_objectinfo` (
/**

 */
`id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
/**

 */
`name` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '对象名称',
/**

 */
`province` varchar(10) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '省',

/**

 */
`city` varchar(20) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '市',

/**

 */
`district` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '区/县',

/**

 */
`street` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '街道',

/**

 */
`address` varchar(200) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '地址',

/**

 */
`addressType` varchar(20) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '地址类型',

/**

 */
`responsibleName` varchar(30) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '法定代表人/经营者/负责人姓名',

/**

 */
`createUserId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '创建人',

/**

 */
`createUserDepId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '创建人所在部门Id',


/**

 */
`createTime` date  NOT NULL  COMMENT '创建时间',


PRIMARY KEY (`id`) USING BTREE ,
INDEX `index_name` (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '监管对象表' ROW_FORMAT = Dynamic;


# 开启外键检查
SET FOREIGN_KEY_CHECKS = 1;




/**
    监管对象相关图片SQL
 */

 SET NAMES utf8;
 # 取消外键约束
 SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tb_objectimage`;
CREATE TABLE `tb_objectimage` (
/**

 */
`id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
/**

 */
`name` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '图片名称',
/**

 */
`type` varchar(10) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '照片类型',

/**

 */
`attachmentId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '附件Id',

/**

 */
`createUserId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '上传人userId',

/**

 */
`createTime` date  NOT NULL  COMMENT '创建时间',


PRIMARY KEY (`id`) USING BTREE ,
INDEX `index_name` (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '监管对象相关图片表' ROW_FORMAT = Dynamic;


# 开启外键检查
SET FOREIGN_KEY_CHECKS = 1;



/**
    属性设置信息SQL
 */

 SET NAMES utf8;
 # 取消外键约束
 SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tb_attributesettinginfo`;
CREATE TABLE `tb_attributesettinginfo` (
/**

 */
`id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
/**

 */
`name` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '属性名称',
/**

 */
`extTableName` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '属性扩展表名称',

/**

 */
`extTableStructure` varchar(3000) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '扩展表表结构',

/**

 */
`sort` int(5) NOT NULL  COMMENT '顺序',

/**

 */
`createTime` date  NOT NULL  COMMENT '创建时间',


PRIMARY KEY (`id`) USING BTREE ,
INDEX `index_name` (`name`) USING BTREE,
INDEX `index_extTableName` (`extTableName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '属性设置信息表' ROW_FORMAT = Dynamic;


# 开启外键检查
SET FOREIGN_KEY_CHECKS = 1;



/**
    对象属性表SQL
 */

 SET NAMES utf8;
 # 取消外键约束
 SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tb_objectattribute`;
CREATE TABLE `tb_objectattribute` (
/**

 */
`id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
/**

 */
`objectId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '对象Id',
/**

 */
`attributeId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '属性id',


PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '对象属性表' ROW_FORMAT = Dynamic;


# 开启外键检查
SET FOREIGN_KEY_CHECKS = 1;

/**
    对象信息变更过日志SQL
 */

 SET NAMES utf8;
 # 取消外键约束
 SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tb_objectinfolog`;
CREATE TABLE `tb_objectinfolog` (
/**

 */
`id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
/**

 */
`objectId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '对象Id',

/**

 */
`field` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '变更字段多个使用逗号隔开',

/**

 */
`createUserId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '创建人userId',
/**

 */
`content` varchar(3000) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '日志内容，多个变更字段使用\n隔开',
/**

 */
`createTime` date  NOT NULL  COMMENT '创建时间',

PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '对象信息变更过日志表' ROW_FORMAT = Dynamic;


# 开启外键检查
SET FOREIGN_KEY_CHECKS = 1;

/**
2020.7.21
删除监管对象多余字段
 */
alter table tb_objectinfo drop column province;
alter table tb_objectinfo drop column city;
alter table tb_objectinfo drop column district;
alter table tb_objectinfo drop column street;
alter table tb_objectinfo drop column address;
alter table tb_objectinfo drop column addressType;
alter table tb_objectinfo drop column responsibleName;
alter table tb_objectinfo add  `parentObjectId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '上级对象Id';
alter table tb_objectinfo add  `topObjectId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '一级对象Id';
alter table tb_objectinfo add  `siteInfoId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '场地信息id';


/**
    场地信息
 */

 SET NAMES utf8;
 # 取消外键约束
 SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tb_siteinfo`;
CREATE TABLE `tb_siteinfo` (
/**

 */
`id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',

/**

 */
`province` varchar(10) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '省',

/**

 */
`city` varchar(20) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '市',

/**

 */
`district` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '区/县',

/**

 */
`street` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci COMMENT '街道',

/**
弄
 */
`lane` int(5) NOT NULL  COMMENT '弄',

/**
号
 */
`number` int(5) NOT NULL  COMMENT '号',

/**
地址
 */
`address` varchar(200) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '地址',


PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '场地信息' ROW_FORMAT = Dynamic;

/**
    个人信息
 */

 SET NAMES utf8;
 # 取消外键约束
 SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `tb_personal`;
CREATE TABLE `tb_personal` (
/**

 */
`id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',

/**

 */
`name` varchar(20) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '姓名',

/**

 */
`idCard` varchar(18) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '身份证',

/**

 */
`Tel` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '手机',

/**

 */
`address` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci COMMENT '地址',

PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '个人信息' ROW_FORMAT = Dynamic;



alter table tb_objectimage add  `objectId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '对象信息id';

alter table tb_attributesettinginfo add  `status` varchar(20) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '状态';

alter table tb_attributesettinginfo add  `parentId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '上级属性id';

alter table tb_attributesettinginfo add  `dataSourceType` varchar(20) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '数据源类型';

/**sort
2020.7.29 11:56
王栋
 */
alter table tb_objectinfo add  `depId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '部门Id';
ALTER TABLE tb_objectinfo ADD INDEX index_siteInfoId ( siteInfoId ) ;
ALTER TABLE tb_objectattribute ADD INDEX index_objectId ( objectId ) ;
ALTER TABLE tb_objectattribute ADD INDEX index_attributeId ( attributeId );

alter table tb_siteInfo add  `latitude` double   COMMENT '纬度';
alter table tb_siteInfo add  `longitude` double   COMMENT '经度';

rename table adsurveys to tb_ext_adsurveys;
rename table community to tb_ext_community;
rename table historic_building to tb_ext_historic_building;
rename table ltinfo to tb_ext_ltinfo;
rename table propertycompany to tb_ext_propertycompany;
rename table realestateappraisalenterpriseinfo to tb_ext_realestateappraisalenterpriseinfo;
rename table shopmark to tb_ext_shopmark;
rename table sitebusiness to tb_ext_sitebusiness;
rename table sitebusinessunitinfo to tb_ext_sitebusinessunitinfo;


UPDATE  tb_attributesettinginfo set dataSourceType = 'FirstClass' WHERE dataSourceType = 'Group';
UPDATE  tb_attributesettinginfo set dataSourceType = 'SecondClass' WHERE dataSourceType = 'Data';


/**
2020.08.05
范亚莹
添加监管对象查询日志表
 */
 SET NAMES utf8;
 # 取消外键约束
 SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `tb_objectinfoviewlog`;
CREATE TABLE `tb_objectinfoviewlog` (
`id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
`objectId` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '监管对象id',
`ip` varchar(30) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT 'ip',
`browser` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '浏览器信息',
`createUserId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '创建人',
`createTime` date  NOT NULL  COMMENT '创建时间',
PRIMARY KEY (`id`) USING BTREE ,
INDEX `index_objectId` (`objectId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '监管对象查询日志表' ROW_FORMAT = Dynamic;
# 开启外键检查
SET FOREIGN_KEY_CHECKS = 1;
alter TABLE tb_attributesettinginfo MODIFY COLUMN extTableName varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '属性扩展表名称',
alter table tb_attributesettinginfo add  `isDeletable` int   COMMENT '是否可删除';
alter TABLE tb_siteinfo MODIFY COLUMN `lane` int(5)    COMMENT '弄',
alter TABLE tb_siteinfo MODIFY COLUMN `number` int(5)   COMMENT '号',
alter TABLE tb_objectinfolog MODIFY  `field` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '变更字段多个使用逗号隔开';



/**
2020.08.18
王栋
添加检查类别表
 */
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkclass` (
                                 `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                 `name` varchar(200) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL  COMMENT '名称',
                                 `createTime` date  NOT NULL  COMMENT '创建时间',
                                 `sort` int(5) NOT NULL  COMMENT '顺序',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查类别表' ROW_FORMAT = Dynamic;

/**
2020.08.18
王栋
添加检查内容表
 */
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkcontent` (
                                   `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                   `checkItemId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查事项ID',
                                   `content` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL  COMMENT '内容',
                                   `sort` int(5) NOT NULL  COMMENT '顺序',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查内容表' ROW_FORMAT = Dynamic;

/**
2020.08.18
王栋
添加检查事项表
 */
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkitem` (
                                `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                `checkClassId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查类别ID',
                                `name` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL  COMMENT '名称',
                                `checkBasis` varchar(1000) CHARACTER SET utf8 COLLATE  utf8_general_ci  COMMENT '检查依据',
                                `sort` int(5) NOT NULL  COMMENT '顺序',
                                `attributeSettingInfoId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '属性设置信息ID',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查事项表' ROW_FORMAT = Dynamic;

/**
2020.08.18
王栋
添加检查计划表
 */
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkplan` (
                                `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                `name` varchar(300) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL  COMMENT '名称',
                                `checkClassId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查类别ID',
                                `createTime` date  NOT NULL  COMMENT '创建时间',
                                `startTime` date  NOT NULL  COMMENT '开始时间',
                                `endTime` date  NOT NULL  COMMENT '结束时间',
                                `depId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查单位',
                                `range` varchar(50) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查范围',
                                `memberGroupCount` int(5) NOT NULL  COMMENT '人员组数',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查计划表' ROW_FORMAT = Dynamic;

/**
2020.08.18
王栋
检查计划分配表
 */
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkplanassign` (
                                      `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                      `checkPlanId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划ID',
                                      `checkMember` varchar(1000) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查人员(,分割)',
                                      `checkObjectAndItem` varchar(2000) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查对象事项(,和|分割)',
                                      `groupNo` int(5) NOT NULL  COMMENT '组号',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查计划分配表' ROW_FORMAT = Dynamic;

/**
2020.08.18
王栋
检查计划事项表
 */
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkplanitem` (
                                    `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                    `name` varchar(300) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '名称',
                                    `checkPlanId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划ID',
                                    `checkItemId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查事项ID',
                                    `checkItemName` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查事项名称',
                                    `coverRate` int(5) NOT NULL  COMMENT '覆盖比例',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查计划事项表' ROW_FORMAT = Dynamic;

/**
2020.08.18
王栋
检查计划对象事项表
 */
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkplanobjectitem` (
                                          `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                          `checkPlanId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划ID',
                                          `checkObjectId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查对象ID',
                                          `checkObjectName` varchar(200) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查对象名称',
                                          `checkObjectAttributeId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查对象属性Id',
                                          `checkItemId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查事项ID',
                                          `checkItemName` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查事项名称',
                                          `sort` int(5) NOT NULL  COMMENT '排序',
                                          `checkPlanObjectItemStatus` varchar(20) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '状态',
                                          `checkPlanTaskId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划任务ID',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查计划对象事项表' ROW_FORMAT = Dynamic;

/**
2020.08.18
王栋
检查计划任务表
 */
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkplantask` (
                                    `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                    `checkPlanId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划ID',
                                    `depId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '部门ID',
                                    `groupNo` int(5) NOT NULL  COMMENT '组号' ,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查计划任务表' ROW_FORMAT = Dynamic;

/**
2020.08.18
王栋
检查计划任务成员表
 */
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkplanmember` (
                                      `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                      `checkPlanId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划ID',
                                      `checkPlanTaskId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划任务ID',
                                      `userId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '成员',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查计划任务成员表' ROW_FORMAT = Dynamic;

/**
2020.08.19
王栋
attributeSettingInfoId从检查事项表移动到检查分类表
 */
alter table  tb_checkitem drop column `attributeSettingInfoId`;
alter table  tb_checkclass add  `attributeSettingInfoId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci  NOT NULL COMMENT '属性设置信息ID';

/**
2020.08.20
王栋
attributeSettingInfoId从检查分类表移动到检查事项表
 */
alter table  tb_checkclass drop column `attributeSettingInfoId`;
alter table  tb_checkitem add  `attributeSettingInfoId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci  NOT NULL COMMENT '属性设置信息ID';
/**
2020.08.20
王栋
tb_checkplan增加发布状态
 */
alter table  tb_checkplan add  `status` varchar(20) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '状态';

/**
2020.08.20
王栋
检查计划事项去掉name
 */
alter table  tb_checkplanitem drop column `name`;


/**
2020.08.20
王栋
检查计划range改为checkRange
 */
alter table  tb_checkplan drop column `range`;
alter table  tb_checkplan add `checkRange` varchar(50) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查范围';

/**
2020.8.26
范亚莹
 */
alter table tb_checkplanobjectitem add  checkTime date   COMMENT '检查时间';
alter table tb_checkplanobjectitem add  checkSituation varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci   COMMENT '检查情况';

/**
2020.8.26
范亚莹
创建检查问题表
 */
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkplantasksituationproblem` (
                                      `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                      `checkPlanId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划ID',
                                      `checkPlanTaskId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划任务ID',
                                      `checkPlanObjectItemId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划对象事项表id',
                                      `checkItemId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查事项ID',
                                      `checkItemName` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查事项名称',
                                      `checkContentId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查内容id',
                                      `content` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查内容',
                                      `scenePhoto` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '现场照片id，多个以","隔开',
                                      `isProblem` int  NOT NULL COMMENT '是否存在问题',
                                      `handleModel` varchar(50) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL  NOT NULL COMMENT '处理方式',
                                      `problemDescription` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL  NOT NULL COMMENT '问题描述',
                                      `createUserId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '创建人userId',
                                       `createTime` date  NOT NULL  COMMENT '创建时间',
                                      PRIMARY KEY (`id`) USING BTREE,
                                       INDEX `index_checkPlanTaskId` (`checkPlanTaskId`) USING BTREE,
                                      INDEX `index_checkPlanId` (`checkPlanId`) USING BTREE,
                                      INDEX `index_checkPlanObjectItemId` (`checkPlanObjectItemId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查任务情况表' ROW_FORMAT = Dynamic;


SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `tb_checkplantasksituationnoproblem` (
                                      `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL  COMMENT '主键',
                                      `checkPlanId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划ID',
                                      `checkPlanTaskId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划任务ID',
                                      `checkPlanObjectItemId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查计划对象事项表id',
                                      `checkItemId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查事项ID',
                                      `checkItemName` varchar(100) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查事项名称',
                                      `checkContentId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查内容id',
                                      `content` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '检查内容',
                                      `scenePhoto` varchar(500) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '现场照片id，多个以","隔开',
                                      `isProblem` int  NOT NULL COMMENT '是否存在问题',
                                      `createUserId` varchar(24) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL COMMENT '创建人userId',
                                       `createTime` date  NOT NULL  COMMENT '创建时间',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `index_checkPlanTaskId` (`checkPlanTaskId`) USING BTREE,
                                      INDEX `index_checkPlanId` (`checkPlanId`) USING BTREE,
                                      INDEX `index_checkPlanObjectItemId` (`checkPlanObjectItemId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查任务情况表' ROW_FORMAT = Dynamic;

/**
2020.9.02
吴磊
 */
ALTER TABLE tb_checkplantasksituationproblem ADD caseNo varchar(24) COMMENT '关联案号';
ALTER TABLE tb_checkplantasksituationproblem ADD summary varchar(24) COMMENT '备注';

/**
2020.9.7
吴磊
创建场地信息标签表
创建场地信息关联标签表
 */
 CREATE TABLE `tb_siteinfotag` (
  `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `code` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '排序编码',
  `sort` INT NOT NULL COMMENT '排序顺序',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT='场地信息标签表';

 CREATE TABLE `tb_siteinfotagrelation` (
  `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `siteinfoId` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '场地信息表ID',
  `tagCode` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '场地信息标签表CODE',
  INDEX `index_siteinfo_id` (`siteinfo_id`) USING BTREE,
  INDEX `index_tag_code` (`tag_code`) USING BTREE,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT='场地信息关联标签表';


/**
2020.9.9
吴磊
添加tb_objectInfo表字段
 */
ALTER TABLE tb_objectInfo ADD investigateId varchar(24) NOT NULL COMMENT '排摸人Id';
ALTER TABLE tb_objectInfo ADD investigateTime date NOT NULL COMMENT '排摸时间';
ALTER TABLE tb_objectInfo ADD contacts  varchar(50) NOT NULL COMMENT '联系人';
ALTER TABLE tb_objectInfo ADD contactNum varchar(50) NOT NULL COMMENT '联系电话';
ALTER TABLE tb_objectInfo ADD unitType varchar(24) NOT NULL COMMENT '单位性质';
ALTER TABLE tb_objectInfo ADD no varchar(24) NOT NULL COMMENT '证照号码';
ALTER TABLE tb_objectInfo ADD noType varchar(24) NOT NULL COMMENT '证照类型';
ALTER TABLE tb_objectInfo ADD storePhoto varchar(24) NOT NULL COMMENT '门头照';


/**
2020.9.9
吴磊
创建tb_objectInfo_investigate表
 */
CREATE TABLE `tb_objectInfo_investigate` (
  `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(200) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店名',
  `contacts` varchar(50) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `contactNum` varchar(50) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `unitType` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位性质',
  `no` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证照号码',
  `noType` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证照类型',
  `storePhoto` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '门头照',
  `isExact` int(11) COMMENT '是否准确',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT='监管对象排摸表';


/**
2020.9.9
吴磊
创建tb_siteinfo_investigate表
 */
CREATE TABLE `tb_siteinfo_investigate` (
  `id` varchar(24) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `province` varchar(10) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省',
  `city` varchar(20) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市',
  `district` varchar(100) CHARACTER SET  utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区/县',
  `street` varchar(100) CHARACTER SET  utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '街道',
  `lane` int(5) DEFAULT NULL COMMENT '弄',
  `number` int(5) DEFAULT NULL COMMENT '号',
  `address` varchar(200) CHARACTER SET  utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `isInvestigate` int(11) NOT NULL COMMENT '是否已排摸',
  `objectCount` int(11) NOT NULL COMMENT '对象数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场地信息排摸表';

ALTER TABLE tb_siteinfo_investigate ADD siteinfoId varchar(24) NOT NULL COMMENT '场地信息表ID';
ALTER TABLE tb_objectInfo_investigate ADD attributeIds varchar(200)  COMMENT '属性ID';
ALTER TABLE tb_objectInfo_investigate ADD investigateId varchar(24) NOT NULL COMMENT '排摸人Id';
ALTER TABLE tb_objectInfo_investigate ADD investigateTime date NOT NULL COMMENT '排摸时间';

/**
2020.9.10
吴磊
编辑tb_objectinfo_investigate表
 */
ALTER TABLE tb_objectInfo_investigate ADD siteinfoId varchar(24) NOT NULL COMMENT '场地信息表ID';

ALTER TABLE tb_siteinfo_investigate ADD location POINT  COMMENT '空间索引';
ALTER TABLE tb_siteinfo_investigate ADD INDEX index_location ( location ) USING BTREE ;

/**
2020.9.17
吴磊
编辑tb_siteinfo表
编辑tb_siteinfo_investigate表
 */
ALTER TABLE tb_siteinfo ADD road varchar(100) DEFAULT NULL COMMENT '道路';
ALTER TABLE tb_siteinfo_investigate ADD road varchar(100) DEFAULT NULL COMMENT '道路';




