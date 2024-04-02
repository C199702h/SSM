package com.example.mycnblog.service.impl;

import com.example.mycnblog.mapper.UserMapper;
import com.example.mycnblog.model.UserInfo;
import com.example.mycnblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户表的服务层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public int add(String username, String password) {
        return userMapper.add(username, password);
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public UserInfo login(String username, String password) {
        return userMapper.login(username, password);
    }

    /**
     * 用户登录——根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    @Override
    public UserInfo getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    /**
     * 获取作者信息
     *
     * @param uid
     * @return
     */
    @Override
    public UserInfo myInfoByUid(Integer uid) {
        return userMapper.myInfoByUid(uid);
    }

    /**
     * 上传头像
     *
     * @param uid
     * @param photo
     * @return
     */
    @Override
    public int updateAvatar(Integer uid, String photo) {
        return userMapper.updateAvatar(uid, photo);
    }

}
