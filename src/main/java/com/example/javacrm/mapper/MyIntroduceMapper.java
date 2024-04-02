package com.example.javacrm.mapper;

import com.example.mycnblog.model.MyIntroduceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户简介表的 mapper
 */
@Mapper
public interface MyIntroduceMapper {

    /**
     * 通过用户 id 查询用户简介
     *
     * @param uid
     * @return
     */
    public MyIntroduceInfo myIntroByUid(@Param("uid") Integer uid);

    /**
     * 添加用户简介
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
    public int add(@Param("sex") String sex,
                   @Param("briefinfo") String briefinfo,
                   @Param("address") String address,
                   @Param("birthday") String birthday,
                   @Param("csdnsite") String csdnsite,
                   @Param("githubsite") String githubsite,
                   @Param("uid") Integer uid);

    /**
     * 修改用户简介
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
    public int update(@Param("sex") String sex,
                      @Param("briefinfo") String briefinfo,
                      @Param("address") String address,
                      @Param("birthday") String birthday,
                      @Param("csdnsite") String csdnsite,
                      @Param("githubsite") String githubsite,
                      @Param("uid") Integer uid);
}
