package com.example.javacrm.service;

import com.example.mycnblog.model.UserInfo;

public interface UserService {
    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    public int add(String username, String password);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public UserInfo login(String username, String password);

    /**
     * 用户登录——根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    public UserInfo getUserByName(String username);

    /**
     * 获取作者信息
     *
     * @param uid
     * @return
     */
    public UserInfo myInfoByUid(Integer uid);

    /**
     * 上传头像
     *
     * @param uid
     * @param photo
     * @return
     */
    public int updateAvatar(Integer uid, String photo);
}
