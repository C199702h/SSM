package com.example.mycnblog.mapper;

import com.example.mycnblog.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章表的 mapper
 */
@Mapper
public interface ArticleMapper {

    /**
     * 根据用户id（uid）查询所有文章
     *
     * @param uid
     * @return
     */
    public List<ArticleInfo> getMyList(@Param("uid") Integer uid);

    /**
     * 分页——查询所有文章
     *
     * @param Offset
     * @param PageSize
     * @return
     */
    public List<ArticleInfo> getList(@Param("Offset") Integer Offset,
                                     @Param("PageSize") Integer PageSize);

    /**
     * 跳转文章列表尾页——查询文章总数
     *
     * @return
     */
    public int getTotalCount();

    /**
     * 根据文章id（id）获取文章的详情信息
     *
     * @param aid
     * @return
     */
    public ArticleInfo getDetail(@Param("aid") Integer aid);

    /**
     * 根据文章id（id）和用户id（uid）修改文章的标题和内容
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
     * 根据文章id（id）删除文章
     *
     * @param aid
     * @return
     */
    public int delete(@Param("aid") Integer aid);

    /**
     * 添加文章（uid，文章标题，文章内容）
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
