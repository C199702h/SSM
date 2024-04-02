package com.example.javacrm.service.impl;

import com.example.mycnblog.mapper.MyIntroduceMapper;
import com.example.mycnblog.mapper.UserMapper;
import com.example.mycnblog.model.MyIntroduceInfo;
import com.example.mycnblog.service.MyIntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户简介的服务层
 */
@Service
public class MyIntroduceServiceImpl implements MyIntroduceService {

    @Autowired
    private MyIntroduceMapper myIntroduceMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户 id 查询用户简介
     *
     * @param uid
     * @return
     */
    @Override
    public MyIntroduceInfo myIntroByUid(Integer uid) {
        return myIntroduceMapper.myIntroByUid(uid);
    }

    /**
     * 增加用户简介
     *
     * @param sex
     * @param briefinfo
     * @param address
     * @param birthday
     * @param csdnsite
     * @param githubsite
     * @param uid
     * @return
     */
    @Override
    public int add(String sex, String briefinfo, String address, String birthday,
                   String csdnsite, String githubsite, Integer uid) {
        return myIntroduceMapper.add(sex, briefinfo, address, birthday, csdnsite, githubsite, uid);
    }

    /**
     * 修改用户简介
     *
     * @param username
     * @param sex
     * @param briefinfo
     * @param address
     * @param birthday
     * @param csdnsite
     * @param githubsite
     * @param uid
     * @return
     */
    @Override
    public int update(String username, String sex, String briefinfo, String address, String birthday,
                      String csdnsite, String githubsite, Integer uid) {
        int result = userMapper.update(uid, username);
        return myIntroduceMapper.update(sex, briefinfo, address, birthday, csdnsite, githubsite, uid);
    }
}
