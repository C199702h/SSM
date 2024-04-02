package com.example.mycnblog.mapper;

import com.example.mycnblog.model.ArticleInfo;
import com.example.mycnblog.model.DraftsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 草稿箱表的 mapper
 */
@Mapper
public interface DraftsMapper {
    /**
     * 根据用户id（uid）查询所有草稿
     *
     * @param uid
     * @return
     */
    public List<DraftsInfo> getMyList(@Param("uid") Integer uid);

    /**
     * 根据草稿id（id）获取草稿的详情信息
     *
     * @param aid
     * @return
     */
    public DraftsInfo getDetail(@Param("aid") Integer aid);

    /**
     * 根据草稿id（id）删除草稿
     *
     * @param aid
     * @return
     */
    public int delete(@Param("aid") Integer aid);

    /**
     * 根据草稿id（id）和用户id（uid）修改草稿的标题和内容
     *
     * @param aid
     * @param uid
     * @param title
     * @param content
     * @return
     */
    public int update(@Param("aid") Integer aid,
                      @Param("uid") Integer uid,
                      @Param("title") String title,
                      @Param("content") String content);

    /**
     * 添加草稿（uid，草稿标题，草稿内容）
     *
     * @param uid
     * @param title
     * @param content
     * @return
     */
    public int add(@Param("uid") Integer uid,
                   @Param("title") String title,
                   @Param("content") String content);
}
