/*
 Navicat Premium Data Transfer

 Source Server         : localhost 5.7
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mall

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 06/09/2020 00:45:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_activity
-- ----------------------------
DROP TABLE IF EXISTS `mall_activity`;
CREATE TABLE `mall_activity`  (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新管理员id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `pic` int(11) NULL DEFAULT NULL COMMENT '活动图，为文件id',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '结束时间',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态，0启用，1禁用',
  `sn` int(11) NOT NULL COMMENT '排序号',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_activity_classify
-- ----------------------------
DROP TABLE IF EXISTS `mall_activity_classify`;
CREATE TABLE `mall_activity_classify`  (
  `classify_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动分类id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新管理员id',
  `classify_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名',
  `sn` int(11) NOT NULL COMMENT '排序号',
  `activity_id` int(11) NULL DEFAULT NULL COMMENT '活动id',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`classify_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_activity_classify_product
-- ----------------------------
DROP TABLE IF EXISTS `mall_activity_classify_product`;
CREATE TABLE `mall_activity_classify_product`  (
  `id` int(11) NOT NULL COMMENT '活动分类与商品关联id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `classify_id` int(11) NOT NULL COMMENT '活动分类id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动分类与商品关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_admin
-- ----------------------------
DROP TABLE IF EXISTS `mall_admin`;
CREATE TABLE `mall_admin`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新管理员id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '可用标识，0不可用，1可用',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_brand
-- ----------------------------
DROP TABLE IF EXISTS `mall_brand`;
CREATE TABLE `mall_brand`  (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新管理员id',
  `brand_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌名称',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`brand_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '品牌表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_cart
-- ----------------------------
DROP TABLE IF EXISTS `mall_cart`;
CREATE TABLE `mall_cart`  (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `quantity` int(11) NOT NULL DEFAULT 1 COMMENT '数量',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`cart_id`) USING BTREE,
  INDEX `cart_index_user_id_deleted_product_id_quantity`(`user_id`, `deleted`, `product_id`, `quantity`) USING BTREE COMMENT '购物车表用户id，删除标识，商品id和数量索引'
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_classify
-- ----------------------------
DROP TABLE IF EXISTS `mall_classify`;
CREATE TABLE `mall_classify`  (
  `classify_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品分类id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新管理员id',
  `pic` int(11) NULL DEFAULT NULL COMMENT '分类图片，为文件id',
  `classify_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名',
  `pid` int(11) NOT NULL COMMENT '父分类id',
  `sn` int(11) NOT NULL COMMENT '排序号',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`classify_id`) USING BTREE,
  INDEX `product_classify_index_pid_deleted`(`pid`, `deleted`) USING BTREE COMMENT '商品分类表pid和删除标识索引'
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_coupon
-- ----------------------------
DROP TABLE IF EXISTS `mall_coupon`;
CREATE TABLE `mall_coupon`  (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新管理员id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `min_price` decimal(10, 2) NOT NULL COMMENT '最低消费',
  `discount` decimal(10, 2) NOT NULL COMMENT '优惠金额',
  `limit` int(11) NOT NULL DEFAULT 1 COMMENT '限领数，默认1',
  `total` int(11) NOT NULL DEFAULT -1 COMMENT '数量，-1无限制',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态，0禁用，1启用',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '类型，1通用券，2新人礼券',
  `use_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '使用类型，1全场，2指定分类，3指定商品',
  `time_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '生效时间类型，1天数，2时间段',
  `days` int(11) NOT NULL DEFAULT 0 COMMENT '有效期限，自领取后几天内有效，0当天',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '有效期限，开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '有效期限，结束时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`coupon_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_coupon_classify
-- ----------------------------
DROP TABLE IF EXISTS `mall_coupon_classify`;
CREATE TABLE `mall_coupon_classify`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券与商品分类关联id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `coupon_id` int(11) NOT NULL COMMENT '优惠券id',
  `classify_id` int(11) NOT NULL COMMENT '分类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券与商品分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_coupon_product
-- ----------------------------
DROP TABLE IF EXISTS `mall_coupon_product`;
CREATE TABLE `mall_coupon_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券与商品关联id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `coupon_id` int(11) NULL DEFAULT NULL COMMENT '优惠券id',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '分类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券与商品关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_file_server
-- ----------------------------
DROP TABLE IF EXISTS `mall_file_server`;
CREATE TABLE `mall_file_server`  (
  `file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `file` longblob NULL COMMENT '文件',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件服务器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_flash
-- ----------------------------
DROP TABLE IF EXISTS `mall_flash`;
CREATE TABLE `mall_flash`  (
  `flash_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '限时抢购id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新管理员id',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '限时抢购开始时间，如2020-07-27 09:00:00',
  `year` int(4) NOT NULL COMMENT '年',
  `month` int(4) NOT NULL COMMENT '月',
  `day` int(4) NOT NULL COMMENT '日',
  `hour` int(4) NOT NULL COMMENT '时',
  `minute` int(4) NOT NULL COMMENT '分',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`flash_id`) USING BTREE,
  INDEX `mall_flash_index_year_month_day_hour_deleted_minute`(`year`, `month`, `day`, `hour`, `deleted`, `minute`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '限时抢购表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_flash_item
-- ----------------------------
DROP TABLE IF EXISTS `mall_flash_item`;
CREATE TABLE `mall_flash_item`  (
  `flash_item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '限时抢购商品id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新管理员id',
  `flash_id` int(11) NOT NULL COMMENT '限时抢购id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
  `sales` int(11) NOT NULL DEFAULT 0 COMMENT '销量',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `limit` int(11) NOT NULL DEFAULT 0 COMMENT '限购数量，0不限制',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`flash_item_id`) USING BTREE,
  INDEX `mall_flash_item_flash_id_product_id_stock_price_limit`(`flash_id`, `deleted`, `product_id`, `stock`, `price`, `limit`) USING BTREE COMMENT '限时抢购商品表抢购id、删除标识、商品id、库存、价格和限购索引'
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '限时抢购商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_order
-- ----------------------------
DROP TABLE IF EXISTS `mall_order`;
CREATE TABLE `mall_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `order_time` timestamp(0) NULL DEFAULT NULL COMMENT '下单时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '订单关闭时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `coupon_id` int(11) NULL DEFAULT NULL COMMENT '优惠券id',
  `address_id` int(11) NOT NULL COMMENT '收货地址id',
  `receive_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `receive_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货电话',
  `receive_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址',
  `product_price` decimal(10, 2) NOT NULL COMMENT '商品费用',
  `delivery_price` decimal(10, 2) NOT NULL COMMENT '配送费用',
  `coupon_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠券抵扣金额',
  `total_price` decimal(10, 2) NOT NULL COMMENT '订单金额',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '订单状态，-1已关闭；0待支付；1已支付；2待收货；3待评价；4已完成',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `expect_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望送达时间，如尽快送达，X月X日 14:00~15:00',
  `pay_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '支付方式，0未支付，1支付宝，2微信',
  `pay_time` timestamp(0) NULL DEFAULT NULL COMMENT '支付时间',
  `delivery_man` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送员',
  `delivery_time` timestamp(0) NULL DEFAULT NULL COMMENT '发货时间',
  `receive_time` timestamp(0) NULL DEFAULT NULL COMMENT '收货时间',
  `score` tinyint(1) NULL DEFAULT 0 COMMENT '评分，1~5分',
  `comment` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价',
  `comment_time` timestamp(0) NULL DEFAULT NULL COMMENT '评价时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `order_index_user_id_deleted`(`user_id`, `deleted`) USING BTREE COMMENT '订单表用户id和删除标识索引'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_order_item
-- ----------------------------
DROP TABLE IF EXISTS `mall_order_item`;
CREATE TABLE `mall_order_item`  (
  `order_item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单商品id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `order_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `pic` int(11) NULL DEFAULT NULL COMMENT '商品图片，为文件id',
  `price` decimal(10, 2) NOT NULL COMMENT '商品单价',
  `quantity` int(11) NOT NULL DEFAULT 1 COMMENT '数量',
  `flash_item_id` int(11) NOT NULL COMMENT '限时抢购商品id',
  PRIMARY KEY (`order_item_id`) USING BTREE,
  INDEX `order_item_index_order_id_deleted`(`order_id`) USING BTREE COMMENT '订单商品表order_id和删除标识索引'
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_product
-- ----------------------------
DROP TABLE IF EXISTS `mall_product`;
CREATE TABLE `mall_product`  (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新管理员id',
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品描述',
  `brand_id` int(11) NULL DEFAULT NULL COMMENT '品牌id',
  `pic` int(11) NULL DEFAULT NULL COMMENT '商品图片，为文件id',
  `original_price` decimal(10, 2) NOT NULL COMMENT '商品原价',
  `current_price` decimal(10, 2) NOT NULL COMMENT '商品现价',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
  `limit` int(11) NOT NULL DEFAULT 10 COMMENT '限购数量，默认10',
  `sales` int(11) NOT NULL DEFAULT 0 COMMENT '销量',
  `new_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '新品状态，0否，1是',
  `publish_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '上架状态，0否，1是',
  `label` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `format` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格',
  `storage` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '存储条件',
  `origin` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产地',
  `detail` int(11) NULL DEFAULT NULL COMMENT '商品详情，为文件id',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_product_classify
-- ----------------------------
DROP TABLE IF EXISTS `mall_product_classify`;
CREATE TABLE `mall_product_classify`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品与商品分类关联id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员id',
  `classify_id` int(11) NOT NULL COMMENT '分类id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_classify_associate_index_classify_id_product_id`(`classify_id`, `product_id`) USING BTREE COMMENT '商品与商品分类关联表分类id和商品id索引',
  INDEX `product_classify_associate_index_product_id_classify_id`(`product_id`, `classify_id`) USING BTREE COMMENT '商品与商品分类关联表商品id和分类id索引'
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品与商品分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_user
-- ----------------------------
DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `avatar` int(11) NULL DEFAULT NULL COMMENT '头像，为文件id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '可用标识，0不可用，1可用',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_index_username`(`username`) USING BTREE COMMENT '用户表用户名索引'
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_user_address
-- ----------------------------
DROP TABLE IF EXISTS `mall_user_address`;
CREATE TABLE `mall_user_address`  (
  `address_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别，0无，1先生，2女士',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货电话',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '地址类型，0未选择，1家，2父母家，3朋友家，4公司，5学校',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`address_id`) USING BTREE,
  INDEX `user_address_index_user_id`(`user_id`) USING BTREE COMMENT '用户地址表用户id索引'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `mall_user_coupon`;
CREATE TABLE `mall_user_coupon`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券与用户关联id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '领取时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `coupon_id` int(11) NOT NULL COMMENT '优惠券id',
  `get_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '获取类型，1主动领取，2后台赠送',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '使用状态，1未使用，2已使用，3已过期',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '有效期限，开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '有效期限，结束时间',
  `use_time` timestamp(0) NULL DEFAULT NULL COMMENT '使用时间',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和优惠券关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_user_favorite
-- ----------------------------
DROP TABLE IF EXISTS `mall_user_favorite`;
CREATE TABLE `mall_user_favorite`  (
  `favorite_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `favorite_time` timestamp(0) NULL DEFAULT NULL COMMENT '收藏时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`favorite_id`) USING BTREE,
  INDEX `user_favorite_user_id`(`user_id`) USING BTREE COMMENT '用户收藏表用户id索引'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_user_footprint
-- ----------------------------
DROP TABLE IF EXISTS `mall_user_footprint`;
CREATE TABLE `mall_user_footprint`  (
  `footprint_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '足迹id',
  `view_time` timestamp(0) NULL DEFAULT NULL COMMENT '访问时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`footprint_id`) USING BTREE,
  INDEX `user_footprint_index_user_id`(`user_id`) USING BTREE COMMENT '用户足迹表用户id索引'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户足迹表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_user_search_history
-- ----------------------------
DROP TABLE IF EXISTS `mall_user_search_history`;
CREATE TABLE `mall_user_search_history`  (
  `history_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '搜索历史id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `keyword` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '搜索词',
  `sn` int(11) NOT NULL COMMENT '排序号',
  `type` tinyint(1) NOT NULL COMMENT '类型，1商品，2订单',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识，0未删除，1删除',
  PRIMARY KEY (`history_id`) USING BTREE,
  INDEX `mall_user_search_history_user_id_keword_type_deleted`(`user_id`, `keyword`, `type`, `deleted`) USING BTREE COMMENT '用户搜索历史表用户id、关键词、类型和删除标识索引'
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户搜索历史表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
