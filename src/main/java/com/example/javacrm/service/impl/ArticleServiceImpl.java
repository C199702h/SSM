package com.example.javacrm.service.impl;

import com.example.mycnblog.mapper.ArticleMapper;
import com.example.mycnblog.model.ArticleInfo;
import com.example.mycnblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章表的服务层
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 用户博客列表，查询用户的所有文章
     *
     * @param uid
     * @return
     */
    @Override
    public List<ArticleInfo> getMyList(Integer uid) {
        return articleMapper.getMyList(uid);
    }

    /**
     * 分页——查询所有文章
     *
     * @param Offset
     * @param PageSize
     * @return
     */
    @Override
    public List<ArticleInfo> getList(Integer Offset, Integer PageSize) {
        return articleMapper.getList(Offset, PageSize);
    }

    /**
     * 跳转文章列表尾页——查询文章总数
     *
     * @return
     */
    @Override
    public int getTotalCount() {
        return articleMapper.getTotalCount();
    }

    /**
     * 用户博客列表，获取文章的详情信息
     *
     * @param aid
     * @return
     */
    @Override
    public ArticleInfo getDetil(Integer aid) {
        return articleMapper.getDetail(aid);
    }

    /**
     * 修改页面，修改文章
     *
     * @param aid
     * @param uid
     * @param title
     * @param content
     * @return
     */
    @Override
    public int update(Integer aid, Integer uid, String title, String content) {
        return articleMapper.update(aid, uid, title, content);
    }

    /**
     * 用户博客列表，删除文章
     *
     * @param aid
     * @return
     */
    @Override
    public int delete(Integer aid) {
        return articleMapper.delete(aid);
    }

    /**
     * 博客编辑，发布文章
     *
     * @param uid
     * @param title
     * @param content
     * @return
     */
    @Override
    public int add(Integer uid, String title, String content) {
        return articleMapper.add(uid, title, content);
    }
}
