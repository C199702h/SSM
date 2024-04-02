package com.example.javacrm.service;

import com.example.mycnblog.model.ArticleInfo;

import java.util.List;

public interface ArticleService {
    /**
     * 用户博客列表，查询用户的所有文章
     *
     * @param uid
     * @return
     */
    public List<ArticleInfo> getMyList(Integer uid);

    /**
     * 分页——查询所有文章
     *
     * @param Offset
     * @param PageSize
     * @return
     */
    public List<ArticleInfo> getList(Integer Offset, Integer PageSize);

    /**
     * 跳转文章列表尾页——查询文章总数
     *
     * @return
     */
    public int getTotalCount();

    /**
     * 用户博客列表，获取文章的详情信息
     *
     * @param aid
     * @return
     */
    public ArticleInfo getDetil(Integer aid);

    /**
     * 修改页面，修改文章
     *
     * @param aid
     * @param uid
     * @param title
     * @param content
     * @return
     */
    public int update(Integer aid, Integer uid, String title, String content);

    /**
     * 用户博客列表，删除文章
     *
     * @param aid
     * @return
     */
    public int delete(Integer aid);

    /**
     * 博客编辑，发布文章
     *
     * @param uid
     * @param title
     * @param content
     * @return
     */
    public int add(Integer uid, String title, String content);
}
