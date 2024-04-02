package com.example.javacrm.service;

import com.example.mycnblog.model.MyIntroduceInfo;

public interface MyIntroduceService {
    /**
     * 通过用户 id 查询用户简介
     *
     * @param uid
     * @return
     */
    public MyIntroduceInfo myIntroByUid(Integer uid);

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
    public int add(String sex, String briefinfo, String address, String birthday,
                   String csdnsite, String githubsite, Integer uid);

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
    public int update(String username, String sex, String briefinfo, String address, String birthday,
                      String csdnsite, String githubsite, Integer uid);
}
