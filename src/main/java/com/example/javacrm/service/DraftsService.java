package com.example.javacrm.service;

import com.example.mycnblog.model.DraftsInfo;

import java.util.List;

public interface DraftsService {
    /**
     * 用户草稿列表，查询用户的所有草稿
     *
     * @param uid
     * @return
     */
    public List<DraftsInfo> getMyList(Integer uid);

    /**
     * 用户草稿箱列表，获取草稿的详情信息
     *
     * @param aid
     * @return
     */
    public DraftsInfo getDetil(Integer aid);

    /**
     * 用户草稿列表，删除草稿
     *
     * @param aid
     * @return
     */
    public int delete(Integer aid);

    /**
     * 草稿编辑页面，保存草稿
     *
     * @param aid
     * @param uid
     * @param title
     * @param content
     * @return
     */
    public int update(Integer aid, Integer uid, String title, String content);

    /**
     * 文章编辑页面，保存草稿
     *
     * @param uid
     * @param title
     * @param content
     * @return
     */
    public int add(Integer uid, String title, String content);
}
