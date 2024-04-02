package com.example.mycnblog.mapper;

import com.example.mycnblog.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表的 mapper
 */
@Mapper
public interface UserMapper {

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    public int add(@Param("username") String username,
                   @Param("password") String password);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public UserInfo login(@Param("username") String username,
                          @Param("password") String password);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    public UserInfo getUserByName(@Param("username") String username);

    /**
     * 通过用户 id 查询用户信息
     *
     * @param uid
     * @return
     */
    public UserInfo myInfoByUid(@Param("uid") Integer uid);

    /**
     * 修改用户名
     *
     * @param uid
     * @return
     */
    public int update(@Param("uid") Integer uid,
                      @Param("username") String username);

    /**
     * 上传头像
     *
     * @param uid
     * @param photo
     * @return
     */
    public int updateAvatar(@Param("uid") Integer uid,
                            @Param("photo") String photo);
}
