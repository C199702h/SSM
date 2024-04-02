package com.example.mycnblog.service.impl;

import com.example.mycnblog.mapper.DraftsMapper;
import com.example.mycnblog.model.DraftsInfo;
import com.example.mycnblog.service.DraftsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 草稿箱的服务层
 */
@Service
public class DraftsServiceImpl implements DraftsService {

    @Autowired
    private DraftsMapper draftsMapper;

    /**
     * 用户草稿列表，查询用户的所有草稿
     *
     * @param uid
     * @return
     */
    @Override
    public List<DraftsInfo> getMyList(Integer uid) {
        return draftsMapper.getMyList(uid);
    }

    /**
     * 用户草稿箱列表，获取草稿的详情信息
     *
     * @param aid
     * @return
     */
    @Override
    public DraftsInfo getDetil(Integer aid) {
        return draftsMapper.getDetail(aid);
    }

    /**
     * 用户草稿列表，删除草稿
     *
     * @param aid
     * @return
     */
    @Override
    public int delete(Integer aid) {
        return draftsMapper.delete(aid);
    }

    /**
     * 草稿编辑页面，保存草稿
     *
     * @param aid
     * @param uid
     * @param title
     * @param content
     * @return
     */
    @Override
    public int update(Integer aid, Integer uid, String title, String content) {
        return draftsMapper.update(aid, uid, title, content);
    }

    /**
     * 文章编辑页面，保存草稿
     *
     * @param uid
     * @param title
     * @param content
     * @return
     */
    @Override
    public int add(Integer uid, String title, String content) {
        return draftsMapper.add(uid, title, content);
    }
}
