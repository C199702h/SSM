package com.example.javacrm.model;

import lombok.Data;

/**
 * 用户简介表
 */
@Data
public class MyIntroduceInfo {
    /**
     * 简介id
     */
    private Integer id;
    /**
     * 性别
     */
    private String sex;
    /**
     * 个人简介
     */
    private String briefinfo;
    /**
     * 所在地区
     */
    private String address;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * CSDN 地址
     */
    private String csdnsite;
    /**
     * GitHub 地址
     */
    private String githubsite;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 状态
     */
    private Integer state;
}
