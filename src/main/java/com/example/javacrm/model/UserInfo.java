package com.example.javacrm.model;

import lombok.Data;

/**
 * 用户表
 */
@Data
public class UserInfo {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话号码
     */
    private String photo;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 状态
     */
    private Integer state;
}
